package servlets;

import dao.CategoriaDAO;
import dao.ProductoDAO;
import entities.Categoria;
import entities.Producto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/ProductosCategoriaServlet")
public class ProductosCategoriaServlet extends HttpServlet {

    private ProductoDAO productoDAO = new ProductoDAO();
    private CategoriaDAO categoriaDAO = new CategoriaDAO();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        int idCategoria = Integer.parseInt(
                request.getParameter("idCategoria")
        );


        List<Producto> productos =
                productoDAO.getProductosPorCategoria(idCategoria);


        Categoria categoria =
                categoriaDAO.getById(idCategoria);


        request.setAttribute("listaProductos", productos);
        request.setAttribute("categoria", categoria);


        request.getRequestDispatcher("productosCategoria.jsp")
                .forward(request, response);


    }

}
