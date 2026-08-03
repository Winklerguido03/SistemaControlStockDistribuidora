package servlets;

import dao.ProveedorDAO;
import entities.Proveedor;
import entities.Usuario;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProveedorServletTest {

    private void setField(Object objeto, String nombre, Object valor)
            throws Exception {

        Field campo = objeto.getClass().getDeclaredField(nombre);
        campo.setAccessible(true);
        campo.set(objeto, valor);
    }

    @Test
    void doGet_sinUsuario_deberiaRedirigirALogin()
            throws Exception {

        ProveedorServlet servlet = new ProveedorServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("usuario")).thenReturn(null);

        servlet.doGet(request, response);

        verify(response).sendRedirect("login.jsp");
    }

    @Test
    void doGet_listarProveedores()
            throws Exception {

        ProveedorServlet servlet = new ProveedorServlet();

        ProveedorDAO dao = mock(ProveedorDAO.class);

        setField(servlet, "proveedorDAO", dao);

        when(dao.getAll()).thenReturn(Arrays.asList(new Proveedor()));

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("usuario")).thenReturn(new Usuario());

        when(request.getRequestDispatcher("proveedores.jsp"))
                .thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute(eq("listaProveedores"), any());
        verify(dispatcher).forward(request, response);
    }

    @Test
    void doGet_eliminarProveedor()
            throws Exception {

        ProveedorServlet servlet = new ProveedorServlet();

        ProveedorDAO dao = mock(ProveedorDAO.class);

        setField(servlet, "proveedorDAO", dao);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("usuario")).thenReturn(new Usuario());

        when(request.getParameter("operacion"))
                .thenReturn("eliminar");

        when(request.getParameter("id"))
                .thenReturn("1");

        servlet.doGet(request, response);

        verify(dao).delete(1);
        verify(response).sendRedirect("ProveedorServlet");
    }

    @Test
    void doGet_editarProveedor()
            throws Exception {

        ProveedorServlet servlet = new ProveedorServlet();

        ProveedorDAO dao = mock(ProveedorDAO.class);

        setField(servlet, "proveedorDAO", dao);

        Proveedor proveedor = new Proveedor();

        when(dao.getById(1)).thenReturn(proveedor);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("usuario")).thenReturn(new Usuario());

        when(request.getParameter("operacion"))
                .thenReturn("editar");

        when(request.getParameter("id"))
                .thenReturn("1");

        when(request.getRequestDispatcher("editarProveedor.jsp"))
                .thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute("proveedor", proveedor);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void doPost_nuevoProveedor()
            throws Exception {

        ProveedorServlet servlet = new ProveedorServlet();

        ProveedorDAO dao = mock(ProveedorDAO.class);

        setField(servlet, "proveedorDAO", dao);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("usuario")).thenReturn(new Usuario());

        when(request.getParameter("operacion"))
                .thenReturn("nuevo");

        when(request.getParameter("txtNombreProveedor"))
                .thenReturn("Proveedor Test");

        when(request.getParameter("txtTelefono"))
                .thenReturn("3482123456");

        when(request.getParameter("txtEmail"))
                .thenReturn("proveedor@test.com");

        when(request.getParameter("txtDireccion"))
                .thenReturn("Santa Fe");

        servlet.doPost(request, response);

        verify(dao).insert(any(Proveedor.class));
        verify(response).sendRedirect("ProveedorServlet");
    }

    @Test
    void doPost_actualizarProveedor()
            throws Exception {

        ProveedorServlet servlet = new ProveedorServlet();

        ProveedorDAO dao = mock(ProveedorDAO.class);

        setField(servlet, "proveedorDAO", dao);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("operacion"))
                .thenReturn("actualizar");

        when(request.getParameter("txtId"))
                .thenReturn("1");

        when(request.getParameter("txtNombreProveedor"))
                .thenReturn("Proveedor Nuevo");

        when(request.getParameter("txtTelefono"))
                .thenReturn("111111");

        when(request.getParameter("txtEmail"))
                .thenReturn("nuevo@test.com");

        when(request.getParameter("txtDireccion"))
                .thenReturn("Av. Siempre Viva");

        servlet.doPost(request, response);

        verify(dao).update(any(Proveedor.class));
        verify(response).sendRedirect("ProveedorServlet");
    }

}