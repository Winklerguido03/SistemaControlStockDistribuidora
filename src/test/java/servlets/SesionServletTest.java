package servlets;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;

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

        servlet.doGet(request,response);

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

        servlet.doGet(request,response);

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

        servlet.doGet(request,response);

        verify(response)
                .sendRedirect("inicio");
    }

}