package servlets;

import dao.ProductoDAO;
import dao.ProveedorDAO;
import entities.Producto;
import entities.Proveedor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/ProductosProveedorServlet")
public class ProductosProveedorServlet extends HttpServlet {

    private ProductoDAO productoDAO = new ProductoDAO();
    private ProveedorDAO proveedorDAO = new ProveedorDAO();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        int idProveedor = Integer.parseInt(
                request.getParameter("idProveedor")
        );


        List<Producto> productos =
                productoDAO.getProductosPorProveedor(idProveedor);


        Proveedor proveedor =
                proveedorDAO.getById(idProveedor);


        request.setAttribute("listaProductos", productos);
        request.setAttribute("proveedor", proveedor);


        request.getRequestDispatcher("productosProveedor.jsp")
                .forward(request, response);

    }
}
