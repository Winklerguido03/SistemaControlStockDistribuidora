package servlets;

import dao.DetalleMovimientoDAO;
import dao.MovimientoDAO;
import dao.ProductoDAO;
import entities.DetalleMovimiento;
import entities.Movimiento;
import entities.Producto;
import entities.Usuario;
import enums.TipoMovimiento;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/EgresoServlet")
public class EgresoServlet extends HttpServlet {

    ProductoDAO productoDAO = new ProductoDAO();
    MovimientoDAO movimientoDAO = new MovimientoDAO();
    DetalleMovimientoDAO detalleMovimientoDAO = new DetalleMovimientoDAO();
    List<Producto> listaProductos=new ArrayList<>();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }


        request.setAttribute("listaProductos", productoDAO.getProductosConStock());

        request.getRequestDispatcher("egresos.jsp")
                .forward(request, response);


}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }


        int idProducto = Integer.parseInt(
                request.getParameter("cmbProducto")
        );

        int cantidad = Integer.parseInt(
                request.getParameter("txtCantidad")
        );


        // Buscar producto
        Producto producto = productoDAO.getById(idProducto);


        if (producto == null) {

            request.setAttribute("error", "Producto inexistente");
            request.getRequestDispatcher("egresos.jsp")
                    .forward(request, response);

            return;
        }


        // Validar stock
        if (producto.getStock() < cantidad) {

            request.setAttribute("error",
                    "Stock insuficiente. Disponible: "
                            + producto.getStock()
            );

            request.setAttribute("listaProductos",
                    productoDAO.getAll()
            );


            request.getRequestDispatcher("egresos.jsp")
                    .forward(request, response);

            return;
        }



        // Restar stock
        producto.setStock(
                producto.getStock() - cantidad
        );

        productoDAO.update(producto);



        // Crear movimiento
        Movimiento movimiento = new Movimiento();

        movimiento.setFecha(new Date());
        movimiento.setTipo(TipoMovimiento.EGRESO);
        movimiento.setUsuario(usuario);

        movimientoDAO.insert(movimiento);

        // Crear detalle movimiento
        DetalleMovimiento detalle = new DetalleMovimiento();

        detalle.setMovimiento(movimiento);
        detalle.setProducto(producto);
        detalle.setCantidad(cantidad);

        detalleMovimientoDAO.insert(detalle);



        response.sendRedirect("ProductoServlet");

    }

}
