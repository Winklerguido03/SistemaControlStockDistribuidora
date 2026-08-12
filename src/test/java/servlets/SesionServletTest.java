package servlets;

import dao.UsuarioDAO;
import entities.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import utils.PasswordUtil;

import java.io.IOException;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

class SesionServletTest {

    @Test
    void doGet_cerrarSesionSinSesion()
            throws Exception {

        SesionServlet servlet = new SesionServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("cerrarSesion"))
                .thenReturn("true");

        when(request.getSession(false))
                .thenReturn(null);

        servlet.doGet(request, response);

        verify(response)
                .sendRedirect("login.jsp?mensajeError=No+tienes+ninguna+sesion+activa.");
    }

    @Test
    void doGet_cerrarSesionConSesion()
            throws Exception {

        SesionServlet servlet = new SesionServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);

        when(request.getParameter("cerrarSesion"))
                .thenReturn("true");

        when(request.getSession(false))
                .thenReturn(session);

        when(session.getAttribute("usuario"))
                .thenReturn(new entities.Usuario());

        servlet.doGet(request, response);

        verify(session).invalidate();

        verify(response)
                .sendRedirect("login.jsp?mensajeExito=Se+ha+cerrado+la+sesion+correctamente.");
    }

    @Test
    void doGet_sinCerrarSesion()
            throws Exception {

        SesionServlet servlet = new SesionServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("cerrarSesion"))
                .thenReturn(null);

        servlet.doGet(request, response);

        verify(response)
                .sendRedirect("inicio");
    }



    @Test
    void validarCredenciales_loginExitoso() {
        SesionServlet servlet = new SesionServlet();

        UsuarioDAO daoMock = mock(UsuarioDAO.class);
        servlet.setUsuarioDao(daoMock);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);

        Usuario usuario = new Usuario();
        usuario.setUsername("admin");
        usuario.setPassword(PasswordUtil.hashPassword("123456"));

        when(request.getSession(false)).thenReturn(null);
        when(request.getSession(true)).thenReturn(session);
        when(request.getSession()).thenReturn(session);
        when(daoMock.getByUsername("admin")).thenReturn(usuario);

        String destino = servlet.validarCredenciales("admin", "123456", request);

        assertEquals("DashboardServlet", destino);
    }

    @Test
    void iniciarSesion() throws Exception {
        SesionServlet servlet = new SesionServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter("txtUsername")).thenReturn("");
        when(request.getParameter("txtPass")).thenReturn("");
        when(request.getSession()).thenReturn(session);

        servlet.iniciarSesion(request, response);

        verify(response).sendRedirect("login.jsp");
    }

    @Test
    void removerSesionActiva() {
        SesionServlet.removerSesionActiva("admin");
    }

    @Test
    void doPost() throws Exception {
        SesionServlet servlet = new SesionServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter("txtUsername")).thenReturn("");
        when(request.getParameter("txtPass")).thenReturn("");
        when(request.getSession()).thenReturn(session);

        servlet.doPost(request, response);

        verify(response).sendRedirect("login.jsp");
    }

}

