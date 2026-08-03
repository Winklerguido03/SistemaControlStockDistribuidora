package servlets;

import dao.UsuarioDAO;
import entities.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.PasswordUtil;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet("/sesion")
public class SesionServlet extends HttpServlet {

    private UsuarioDAO usuarioDao = new UsuarioDAO();
    // fix: se guardaran sesiones en vez de usuarios
    private static Map<String, HttpSession> sesionesActivas = new ConcurrentHashMap<>();

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String cerrarSesionParam = req.getParameter("cerrarSesion");

        if ("true".equals(cerrarSesionParam)) {
            cerrarSesion(req, res);
        } else {
            res.sendRedirect("inicio");
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        iniciarSesion(req, res);
    }

    // para cerrar sesion
    private void cerrarSesion(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("usuario") != null) {
            session.invalidate();
            res.sendRedirect("login.jsp?mensajeExito=Se+ha+cerrado+la+sesion+correctamente.");
        } else {
            res.sendRedirect("login.jsp?mensajeError=No+tienes+ninguna+sesion+activa.");
        }
    }

    public static void removerSesionActiva(String username) {
        if (username != null) {
            sesionesActivas.remove(username);
        }
    }

    // metodos
    // para iniciar sesion
    private void iniciarSesion(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // parámetros de la petición
        String username = req.getParameter("txtUsername");
        String password = req.getParameter("txtPass");

        // valido credenciales
        String destino = validarCredenciales(username, password, req);

        res.sendRedirect(destino);
    }

    // para validar email y password
    private String validarCredenciales(String username, String password, HttpServletRequest req) {
        if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
            req.getSession().setAttribute("mensajeError", "Nombre de usuario y contraseña son obligatorios.");
            return "login.jsp";
        }

        username = username.trim();

        if (sesionesActivas.containsKey(username)) {
            try {
                sesionesActivas.get(username).invalidate();
            } catch (IllegalStateException e){
            }
        }

        Usuario usuario = usuarioDao.getByUsername(username);

        if (usuario == null) {
            req.getSession().setAttribute("mensajeError", "Nombre de usuario y/o contraseña incorrecta.");
            return "login.jsp";
        }

        try {
            if (!PasswordUtil.verifyPassword(password, usuario.getPassword())) {
                req.getSession().setAttribute("mensajeError", "Nombre de usuario y/o contraseña incorrecta.");
                return "login.jsp";
            }
        } catch (Exception e) {
            System.err.println("Error verificando contraseña: " + e.getMessage());
            req.getSession().setAttribute("mensajeError", "Error en el sistema de autenticación.");
            return "login.jsp";
        }
/*
    HttpSession sesion = req.getSession(true);
    req.changeSessionId();
    sesion.setAttribute("usuario", usuario);
    sesionesActivas.put(email, sesion);*/
        HttpSession sesionVieja = req.getSession(false);
        if (sesionVieja != null) {
            sesionVieja.invalidate();
        }
        HttpSession sesion = req.getSession(true);
        sesion.setAttribute("usuario", usuario);
        sesionesActivas.put(username, sesion);

        // No es necesario mandar el mensaje de éxito a "inicio" a menos que lo leas allá
        return "DashboardServlet";
    }

}
