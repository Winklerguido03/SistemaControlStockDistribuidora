package servlets;

import dao.CategoriaDAO;
import dao.ProductoDAO;
import dao.ProveedorDAO;
import entities.Categoria;
import entities.Producto;
import entities.Proveedor;
import entities.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/ProductoServlet")
public class ProductoServlet extends HttpServlet {

    ProductoDAO productoDAO = new ProductoDAO();
    List<Producto> listaProductos;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

        // Para obtener sesion de admin
        Usuario user = (Usuario) session.getAttribute("usuario");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String operacion = request.getParameter("operacion");

        if (operacion != null) {

            switch (operacion) {

                case "eliminar":

                    int id = Integer.parseInt(request.getParameter("id"));
                    productoDAO.delete(id);

                    response.sendRedirect("ProductoServlet");
                    return;

                case "editar":

                    int idEditar = Integer.parseInt(request.getParameter("id"));

                    Producto producto = productoDAO.getById(idEditar);

                    CategoriaDAO categoriaDAO = new CategoriaDAO();
                    ProveedorDAO proveedorDAO = new ProveedorDAO();


                    request.setAttribute("producto", producto);

                    request.setAttribute("listaCategorias", categoriaDAO.getAll());

                    request.setAttribute("listaProveedores", proveedorDAO.getAll());


                    request.getRequestDispatcher("editarProducto.jsp")
                            .forward(request, response);

                    return;
            }
        }

        // Para obtener productos
        listaProductos = productoDAO.getProductosConStock();
        request.setAttribute("listaProductos", listaProductos);
        request.getRequestDispatcher("productos.jsp").forward(request, response);


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accion = request.getParameter("operacion");

        switch (accion) {

            case "nuevo":

                Usuario user = (Usuario) request.getSession().getAttribute("usuario");

                if (user == null) {
                    response.sendRedirect("login.jsp");
                    return;
                }

                // Leer datos del formulario
                String nombre = request.getParameter("txtNombreProducto");
                int precioCompra = Integer.parseInt(request.getParameter("txtPrecioCompra"));
                int precioVenta = Integer.parseInt(request.getParameter("txtPrecioVenta"));
                int stock = Integer.parseInt(request.getParameter("txtCantidad"));

                // Leer los IDs de los select
                int idCategoria = Integer.parseInt(request.getParameter("cmbCategoria"));
                int idProveedor = Integer.parseInt(request.getParameter("cmbProveedor"));

                // Crear categoría
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(idCategoria);

                // Crear proveedor
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(idProveedor);

                // Crear producto
                Producto producto = new Producto();
                producto.setNombre(nombre);
                producto.setPrecioCompra(precioCompra);
                producto.setPrecioVenta(precioVenta);
                producto.setStock(stock);

                // Asignar categoría y proveedor
                producto.setCategoriaProducto(categoria);
                producto.setProveedorProducto(proveedor);

                // Guardar
                productoDAO.insert(producto);

                response.sendRedirect("ProductoServlet");
                break;

            case "actualizar":

                int idProducto = Integer.parseInt(
                        request.getParameter("idProducto")
                );


                 nombre = request.getParameter("txtNombreProducto");


                 precioCompra = Integer.parseInt(
                        request.getParameter("txtPrecioCompra")
                );


                 precioVenta = Integer.parseInt(
                        request.getParameter("txtPrecioVenta")
                );


                 stock = Integer.parseInt(
                        request.getParameter("txtCantidad")
                );


                 idCategoria = Integer.parseInt(
                        request.getParameter("cmbCategoria")
                );


                 idProveedor = Integer.parseInt(
                        request.getParameter("cmbProveedor")
                );


                 categoria = new Categoria();
                categoria.setIdCategoria(idCategoria);



                proveedor = new Proveedor();
                proveedor.setIdProveedor(idProveedor);



                producto = new Producto();

                producto.setIdProducto(idProducto);

                producto.setNombre(nombre);

                producto.setPrecioCompra(precioCompra);

                producto.setPrecioVenta(precioVenta);

                producto.setStock(stock);

                producto.setCategoriaProducto(categoria);

                producto.setProveedorProducto(proveedor);

                productoDAO.update(producto);

                response.sendRedirect("ProductoServlet");

                break;
        }
    }

}
