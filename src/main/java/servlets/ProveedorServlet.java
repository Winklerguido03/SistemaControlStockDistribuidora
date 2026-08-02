package servlets;

import dao.CategoriaDAO;
import dao.ProveedorDAO;
import entities.Categoria;
import entities.Proveedor;
import entities.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/ProveedorServlet")
public class ProveedorServlet extends HttpServlet {

    ProveedorDAO proveedorDAO = new ProveedorDAO();
    List<Proveedor> listaProveedores;

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
                    proveedorDAO.delete(id);
                    response.sendRedirect("ProveedorServlet");
                    return;
            }
        }

        // Para obtener proveedores
        listaProveedores = proveedorDAO.getAll();
        request.setAttribute("listaProveedores", listaProveedores);
        request.getRequestDispatcher("proveedores.jsp").forward(request, response);


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

                String nombreProveedor = request.getParameter("txtNombreProveedor");
                String telefono = request.getParameter("txtTelefono");
                String email = request.getParameter("txtEmail");
                String direccion = request.getParameter("txtDireccion");

                Proveedor proveedor = new Proveedor();
                proveedor.setNombre(nombreProveedor);
                proveedor.setTelefono(telefono);
                proveedor.setEmail(email);
                proveedor.setDireccion(direccion);

                proveedorDAO.insert(proveedor);

                response.sendRedirect("ProveedorServlet");
                break;
        }
    }
}
