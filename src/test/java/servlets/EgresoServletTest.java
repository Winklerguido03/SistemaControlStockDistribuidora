package servlets;

import dao.DetalleMovimientoDAO;
import dao.MovimientoDAO;
import dao.ProductoDAO;
import entities.Producto;
import entities.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EgresoServletTest {

    private void setField(Object objeto, String nombre, Object valor)
            throws Exception {


        Field campo =
                objeto.getClass()
                        .getDeclaredField(nombre);


        campo.setAccessible(true);


        campo.set(objeto, valor);

    }






    @Test
    void doGet_sinUsuario_deberiaRedirigirLogin()
            throws Exception {


        EgresoServlet servlet =
                new EgresoServlet();


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
    void doGet_conUsuario_deberiaMostrarProductos()
            throws Exception {


        EgresoServlet servlet =
                new EgresoServlet();


        ProductoDAO productoDAO =
                mock(ProductoDAO.class);



        setField(
                servlet,
                "productoDAO",
                productoDAO
        );



        when(productoDAO.getProductosConStock())
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



        when(request.getRequestDispatcher("egresos.jsp"))
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
    void doPost_productoInexistente_deberiaMostrarError()
            throws Exception {


        EgresoServlet servlet =
                new EgresoServlet();



        ProductoDAO productoDAO =
                mock(ProductoDAO.class);



        setField(
                servlet,
                "productoDAO",
                productoDAO
        );



        when(productoDAO.getById(1))
                .thenReturn(null);



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



        when(request.getParameter("cmbProducto"))
                .thenReturn("1");



        when(request.getParameter("txtCantidad"))
                .thenReturn("5");



        when(request.getRequestDispatcher("egresos.jsp"))
                .thenReturn(dispatcher);




        servlet.doPost(request,response);




        verify(request)
                .setAttribute(
                        "error",
                        "Producto inexistente"
                );



        verify(dispatcher)
                .forward(
                        request,
                        response
                );

    }









    @Test
    void doPost_stockInsuficiente_deberiaMostrarError()
            throws Exception {


        EgresoServlet servlet =
                new EgresoServlet();



        ProductoDAO productoDAO =
                mock(ProductoDAO.class);



        setField(
                servlet,
                "productoDAO",
                productoDAO
        );



        Producto producto =
                new Producto();


        producto.setStock(2);



        when(productoDAO.getById(1))
                .thenReturn(producto);



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



        when(request.getParameter("cmbProducto"))
                .thenReturn("1");



        when(request.getParameter("txtCantidad"))
                .thenReturn("10");



        when(request.getRequestDispatcher("egresos.jsp"))
                .thenReturn(dispatcher);




        servlet.doPost(request,response);



        verify(request)
                .setAttribute(
                        eq("error"),
                        contains("Stock insuficiente")
                );



        verify(dispatcher)
                .forward(
                        request,
                        response
                );

    }








    @Test
    void doPost_egresoCorrecto_deberiaActualizarStock()
            throws Exception {


        EgresoServlet servlet =
                new EgresoServlet();



        ProductoDAO productoDAO =
                mock(ProductoDAO.class);



        MovimientoDAO movimientoDAO =
                mock(MovimientoDAO.class);



        DetalleMovimientoDAO detalleDAO =
                mock(DetalleMovimientoDAO.class);




        setField(servlet,"productoDAO",productoDAO);
        setField(servlet,"movimientoDAO",movimientoDAO);
        setField(servlet,"detalleMovimientoDAO",detalleDAO);




        Producto producto =
                new Producto();



        producto.setStock(20);




        when(productoDAO.getById(1))
                .thenReturn(producto);




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



        when(request.getParameter("cmbProducto"))
                .thenReturn("1");



        when(request.getParameter("txtCantidad"))
                .thenReturn("5");




        servlet.doPost(request,response);




        verify(productoDAO)
                .update(producto);



        verify(movimientoDAO)
                .insert(any());



        verify(detalleDAO)
                .insert(any());



        verify(response)
                .sendRedirect("ProductoServlet");



        //20 - 5
        assert producto.getStock() == 15;

    }

}