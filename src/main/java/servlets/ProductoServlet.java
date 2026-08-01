package servlets;

import dao.ProductoDAO;
import entities.Categoria;
import entities.Producto;
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

        // Para obtener productos
        listaProductos = productoDAO.getAll();
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

                String nombreProducto = request.getParameter("txtNombreProducto");
                String precioCompra = request.getParameter("txtPrecioCompra");
                String precioVenta = request.getParameter("txtPrecioVenta");
                String cantidad = request.getParameter("txtCantidad");


                Producto producto = new Producto();
                producto.setNombre(nombreProducto);
                producto.setPrecioCompra(Integer.parseInt(precioCompra));
                producto.setPrecioVenta(Integer.parseInt(precioVenta));
                producto.setStock(Integer.parseInt(cantidad));

                ProductoDAO dao = new ProductoDAO();
                dao.insert(producto);

                response.sendRedirect("index.jsp");
                break;
        }
    }

}
