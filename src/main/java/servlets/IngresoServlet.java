package servlets;

import dao.*;
import entities.*;
import enums.TipoMovimiento;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

@WebServlet("/IngresoServlet")
public class IngresoServlet extends HttpServlet {

    private ProductoDAO productoDAO = new ProductoDAO();
    private MovimientoDAO movimientoDAO = new MovimientoDAO();
    private DetalleMovimientoDAO detalleMovimientoDAO = new DetalleMovimientoDAO();

    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        ProveedorDAO proveedorDAO = new ProveedorDAO();

        request.setAttribute("listaCategorias", categoriaDAO.getAll());
        request.setAttribute("listaProveedores", proveedorDAO.getAll());

        request.getRequestDispatcher("ingresos.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Datos del formulario
        String nombre = request.getParameter("txtNombreProducto");
        int precioCompra = Integer.parseInt(request.getParameter("txtPrecioCompra"));
        int precioVenta = Integer.parseInt(request.getParameter("txtPrecioVenta"));
        int cantidad = Integer.parseInt(request.getParameter("txtCantidad"));

        int idCategoria = Integer.parseInt(request.getParameter("cmbCategoria"));
        int idProveedor = Integer.parseInt(request.getParameter("cmbProveedor"));

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCategoria);

        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(idProveedor);

        // Buscar si el producto ya existe
        Producto producto = productoDAO.getByNombre(nombre);

        if (producto == null) {

            // Crear producto nuevo
            producto = new Producto();
            producto.setNombre(nombre);
            producto.setPrecioCompra(precioCompra);
            producto.setPrecioVenta(precioVenta);
            producto.setStock(cantidad);
            producto.setCategoriaProducto(categoria);
            producto.setProveedorProducto(proveedor);

            productoDAO.insert(producto);

            // Volver a buscarlo para obtener el id generado
            producto = productoDAO.getByNombre(nombre);

        } else {

            // Ya existe: aumentar stock
            producto.setStock(producto.getStock() + cantidad);

            // Actualizar datos
            producto.setPrecioCompra(precioCompra);
            producto.setPrecioVenta(precioVenta);
            producto.setCategoriaProducto(categoria);
            producto.setProveedorProducto(proveedor);

            productoDAO.update(producto);
        }

        // Registrar movimiento
        Movimiento movimiento = new Movimiento();
        movimiento.setFecha(new Date());
        movimiento.setTipo(TipoMovimiento.INGRESO);
        movimiento.setUsuario(usuario);

        movimientoDAO.insert(movimiento);

        // Registrar detalle
        DetalleMovimiento detalle = new DetalleMovimiento();
        detalle.setMovimiento(movimiento);
        detalle.setProducto(producto);
        detalle.setCantidad(cantidad);

        detalleMovimientoDAO.insert(detalle);

        response.sendRedirect("ProductoServlet");
    }




}
