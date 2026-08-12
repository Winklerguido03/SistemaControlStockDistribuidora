package servlets;

import dao.CategoriaDAO;
import entities.Categoria;
import entities.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoriaServletTest {

    private CategoriaServlet crearServletConDAO(CategoriaDAO dao)
            throws Exception {

        CategoriaServlet servlet = new CategoriaServlet();

        Field campo = CategoriaServlet.class.getDeclaredField("categoriaDAO");

        campo.setAccessible(true);

        campo.set(servlet, dao);

        return servlet;
    }


    @Test
    void doGet_sinUsuario_deberiaRedirigirLogin()
            throws Exception {

        CategoriaServlet servlet = new CategoriaServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);

        HttpServletResponse response = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);

        when(session.getAttribute("usuario")).thenReturn(null);

        servlet.doGet(request,response);

        verify(response).sendRedirect("login.jsp");

    }


    @Test
    void doGet_conUsuario_deberiaMostrarCategorias()
            throws Exception {

        CategoriaDAO dao = mock(CategoriaDAO.class);

        List<Categoria> lista = new ArrayList<>();

        lista.add(new Categoria("Bebidas"));

        when(dao.getAll()).thenReturn(lista);

        CategoriaServlet servlet = crearServletConDAO(dao);

        HttpServletRequest request = mock(HttpServletRequest.class);

        HttpServletResponse response = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);

        when(session.getAttribute("usuario")).thenReturn(new Usuario());

        when(request.getRequestDispatcher("categorias.jsp")).thenReturn(dispatcher);

        servlet.doGet(request,response);

        verify(request).setAttribute("listaCategorias", lista);

        verify(dispatcher).forward(request, response);

    }


    @Test
    void doGet_eliminar_deberiaLlamarDelete()
            throws Exception {

        CategoriaDAO dao = mock(CategoriaDAO.class);

        CategoriaServlet servlet = crearServletConDAO(dao);

        HttpServletRequest request = mock(HttpServletRequest.class);

        HttpServletResponse response = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);

        when(session.getAttribute("usuario")).thenReturn(new Usuario());

        when(request.getParameter("operacion")).thenReturn("eliminar");

        when(request.getParameter("id")).thenReturn("5");

        servlet.doGet(request,response);

        verify(dao).delete(5);

        verify(response).sendRedirect("CategoriaServlet");

    }


    @Test
    void doPost_nuevo_deberiaInsertarCategoria()
            throws Exception {

        CategoriaDAO dao = mock(CategoriaDAO.class);

        CategoriaServlet servlet = crearServletConDAO(dao);

        HttpServletRequest request = mock(HttpServletRequest.class);

        HttpServletResponse response = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);

        when(session.getAttribute("usuario")).thenReturn(new Usuario());

        when(request.getParameter("operacion")).thenReturn("nuevo");

        when(request.getParameter("txtNombreCategoria")).thenReturn("Golosinas");

        servlet.doPost(request,response);

        verify(dao).insert(any(Categoria.class));

        verify(response).sendRedirect("CategoriaServlet");

    }


    @Test
    void doPost_actualizar_deberiaActualizarCategoria()
            throws Exception {

        CategoriaDAO dao = mock(CategoriaDAO.class);

        CategoriaServlet servlet = crearServletConDAO(dao);

        HttpServletRequest request = mock(HttpServletRequest.class);

        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("operacion")).thenReturn("actualizar");

        when(request.getParameter("txtId")).thenReturn("1");

        when(request.getParameter("txtNombreCategoria")).thenReturn("Nueva Categoria");

        servlet.doPost(request,response);

        verify(dao).update(any(Categoria.class));

        verify(response).sendRedirect("CategoriaServlet");

    }
}