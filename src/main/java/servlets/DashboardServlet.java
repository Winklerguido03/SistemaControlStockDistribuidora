package servlets;

import dao.CategoriaDAO;
import dao.ProductoDAO;
import dao.ProveedorDAO;
import entities.Producto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {

    private ProductoDAO productoDAO = new ProductoDAO();
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private ProveedorDAO proveedorDAO = new ProveedorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Producto> productos = productoDAO.getAll();

        // ===== STREAMS =====

        long productosConStock = productos.stream()
                .filter(p -> p.getStock() > 0)
                .count();

        long productosSinStock = productos.stream()
                .filter(p -> p.getStock() == 0)
                .count();

        int stockTotal = productos.stream()
                .mapToInt(Producto::getStock)
                .sum();

        Producto productoMayorStock = productos.stream()
                .max(Comparator.comparingInt(Producto::getStock))
                .orElse(null);

        Producto productoMenorStock = productos.stream()
                .min(Comparator.comparingInt(Producto::getStock))
                .orElse(null);

        double promedioStock = productos.stream()
                .mapToInt(Producto::getStock)
                .average()
                .orElse(0);

        // ===== ATRIBUTOS =====

        request.setAttribute("totalProductos", productos.size());
        request.setAttribute("totalCategorias", categoriaDAO.getAll().size());
        request.setAttribute("totalProveedores", proveedorDAO.getAll().size());

        request.setAttribute("productosConStock", productosConStock);
        request.setAttribute("productosSinStock", productosSinStock);

        request.setAttribute("stockTotal", stockTotal);
        request.setAttribute("promedioStock", promedioStock);

        request.setAttribute("productoMayorStock", productoMayorStock);
        request.setAttribute("productoMenorStock", productoMenorStock);

        request.getRequestDispatcher("index.jsp")
                .forward(request, response);
    }
}

