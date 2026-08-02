package servlets;

import dao.CategoriaDAO;
import dao.ProductoDAO;
import entities.Categoria;
import entities.Producto;
import entities.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/CategoriaServlet")
public class CategoriaServlet extends HttpServlet {

    CategoriaDAO categoriaDAO = new CategoriaDAO();
    List<Categoria> listaCategorias;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        // Para obtener sesion de admin
        Usuario user = (Usuario) session.getAttribute("usuario");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        //Para eliminar una noticia
        String accion = request.getParameter("operacion");

        if (accion != null) {
            switch (accion) {
                case "eliminar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    categoriaDAO.delete(id);
                    response.sendRedirect("categorias.jsp");
                    return;
            }
        }

        // Para obtener categorias
        listaCategorias = categoriaDAO.getAll();
        request.setAttribute("listaCategorias", listaCategorias);
        request.getRequestDispatcher("categorias.jsp").forward(request, response);



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

                String nombreCategoria = request.getParameter("txtNombreCategoria");


                Categoria categoria = new Categoria();
                categoria.setNombre(nombreCategoria);

                CategoriaDAO categoriaDAO = new CategoriaDAO();
                categoriaDAO.insert(categoria);

                response.sendRedirect("CategoriaServlet");
                break;
        }
    }
}
