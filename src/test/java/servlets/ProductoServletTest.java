package servlets;

import org.junit.jupiter.api.Test;

import dao.ProductoDAO;

import entities.Usuario;
import entities.Producto;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.*;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductoServletTest {

    private void setField(Object objeto,String nombre,Object valor)
            throws Exception {


        Field campo =
                objeto.getClass()
                        .getDeclaredField(nombre);


        campo.setAccessible(true);


        campo.set(objeto,valor);

    }






    @Test
    void doGet_sinUsuario_deberiaEnviarLogin()
            throws Exception {


        ProductoServlet servlet =
                new ProductoServlet();



        HttpServletRequest request =
                mock(HttpServletRequest.class);


        HttpServletResponse response =
                mock(HttpServletResponse.class);


        HttpSession session =
                mock(HttpSession.class);



        when(request.getSession())
                .thenReturn(session);



        when(session.getAttribute("usuario"))
                .thenReturn(null);




        servlet.doGet(request,response);




        verify(response)
                .sendRedirect("login.jsp");

    }







    @Test
    void doGet_conUsuario_deberiaCargarProductos()
            throws Exception {


        ProductoServlet servlet =
                new ProductoServlet();



        ProductoDAO dao =
                mock(ProductoDAO.class);



        setField(
                servlet,
                "productoDAO",
                dao
        );



        when(dao.getProductosConStock())
                .thenReturn(
                        Arrays.asList(
                                new Producto()
                        )
                );



        HttpServletRequest request =
                mock(HttpServletRequest.class);



        HttpServletResponse response =
                mock(HttpServletResponse.class);



        HttpSession session =
                mock(HttpSession.class);



        RequestDispatcher dispatcher =
                mock(RequestDispatcher.class);



        when(request.getSession())
                .thenReturn(session);



        when(session.getAttribute("usuario"))
                .thenReturn(new Usuario());



        when(request.getRequestDispatcher("productos.jsp"))
                .thenReturn(dispatcher);




        servlet.doGet(request,response);



        verify(request)
                .setAttribute(
                        eq("listaProductos"),
                        any()
                );



        verify(dispatcher)
                .forward(
                        request,
                        response
                );

    }









    @Test
    void doGet_eliminar_deberiaEliminarProducto()
            throws Exception {


        ProductoServlet servlet =
                new ProductoServlet();



        ProductoDAO dao =
                mock(ProductoDAO.class);



        setField(
                servlet,
                "productoDAO",
                dao
        );




        HttpServletRequest request =
                mock(HttpServletRequest.class);



        HttpServletResponse response =
                mock(HttpServletResponse.class);



        HttpSession session =
                mock(HttpSession.class);



        when(request.getSession())
                .thenReturn(session);



        when(session.getAttribute("usuario"))
                .thenReturn(new Usuario());



        when(request.getParameter("operacion"))
                .thenReturn("eliminar");



        when(request.getParameter("id"))
                .thenReturn("5");




        servlet.doGet(request,response);




        verify(dao)
                .delete(5);



        verify(response)
                .sendRedirect("ProductoServlet");

    }









    @Test
    void doPost_nuevo_deberiaInsertarProducto()
            throws Exception {



        ProductoServlet servlet =
                new ProductoServlet();



        ProductoDAO dao =
                mock(ProductoDAO.class);



        setField(
                servlet,
                "productoDAO",
                dao
        );



        HttpServletRequest request =
                mock(HttpServletRequest.class);



        HttpServletResponse response =
                mock(HttpServletResponse.class);



        HttpSession session =
                mock(HttpSession.class);




        when(request.getSession())
                .thenReturn(session);



        when(session.getAttribute("usuario"))
                .thenReturn(new Usuario());



        when(request.getParameter("operacion"))
                .thenReturn("nuevo");



        when(request.getParameter("txtNombreProducto"))
                .thenReturn("Caramelos");



        when(request.getParameter("txtPrecioCompra"))
                .thenReturn("100");



        when(request.getParameter("txtPrecioVenta"))
                .thenReturn("200");



        when(request.getParameter("txtCantidad"))
                .thenReturn("50");



        when(request.getParameter("cmbCategoria"))
                .thenReturn("1");



        when(request.getParameter("cmbProveedor"))
                .thenReturn("1");





        servlet.doPost(request,response);




        verify(dao)
                .insert(any(Producto.class));



        verify(response)
                .sendRedirect(
                        "ProductoServlet"
                );

    }









    @Test
    void doPost_actualizar_deberiaActualizarProducto()
            throws Exception {



        ProductoServlet servlet =
                new ProductoServlet();



        ProductoDAO dao =
                mock(ProductoDAO.class);



        setField(
                servlet,
                "productoDAO",
                dao
        );



        HttpServletRequest request =
                mock(HttpServletRequest.class);



        HttpServletResponse response =
                mock(HttpServletResponse.class);




        when(request.getParameter("operacion"))
                .thenReturn("actualizar");



        when(request.getParameter("idProducto"))
                .thenReturn("1");



        when(request.getParameter("txtNombreProducto"))
                .thenReturn("Chocolate");



        when(request.getParameter("txtPrecioCompra"))
                .thenReturn("500");



        when(request.getParameter("txtPrecioVenta"))
                .thenReturn("800");



        when(request.getParameter("txtCantidad"))
                .thenReturn("20");



        when(request.getParameter("cmbCategoria"))
                .thenReturn("1");



        when(request.getParameter("cmbProveedor"))
                .thenReturn("1");





        servlet.doPost(request,response);





        verify(dao)
                .update(any(Producto.class));



        verify(response)
                .sendRedirect(
                        "ProductoServlet"
                );

    }
}