package servlets;

import dao.DetalleMovimientoDAO;
import dao.MovimientoDAO;
import dao.ProductoDAO;
import entities.Producto;
import entities.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IngresoServletTest {

    private void setField(Object objeto,String nombre,Object valor)
            throws Exception {


        Field campo =
                objeto.getClass()
                        .getDeclaredField(nombre);


        campo.setAccessible(true);


        campo.set(objeto,valor);

    }






    @Test
    void doPost_sinUsuario_deberiaRedirigirLogin()
            throws Exception {


        IngresoServlet servlet =
                new IngresoServlet();



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




        servlet.doPost(
                request,
                response
        );




        verify(response)
                .sendRedirect(
                        "login.jsp"
                );

    }









    @Test
    void doPost_productoNuevo_deberiaInsertarProductoMovimientoYDetalle()
            throws Exception {



        IngresoServlet servlet =
                new IngresoServlet();



        ProductoDAO productoDAO =
                mock(ProductoDAO.class);


        MovimientoDAO movimientoDAO =
                mock(MovimientoDAO.class);


        DetalleMovimientoDAO detalleDAO =
                mock(DetalleMovimientoDAO.class);



        setField(
                servlet,
                "productoDAO",
                productoDAO
        );


        setField(
                servlet,
                "movimientoDAO",
                movimientoDAO
        );


        setField(
                servlet,
                "detalleMovimientoDAO",
                detalleDAO
        );





        when(productoDAO.getByNombre("Alfajor"))
                .thenReturn(null);



        Producto productoGuardado =
                new Producto();



        productoGuardado.setIdProducto(1);
        productoGuardado.setNombre("Alfajor");



        when(productoDAO.getByNombre("Alfajor"))
                .thenReturn(
                        productoGuardado
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
                .thenReturn(
                        new Usuario()
                );



        when(request.getParameter("txtNombreProducto"))
                .thenReturn("Alfajor");



        when(request.getParameter("txtPrecioCompra"))
                .thenReturn("100");



        when(request.getParameter("txtPrecioVenta"))
                .thenReturn("200");



        when(request.getParameter("txtCantidad"))
                .thenReturn("10");



        when(request.getParameter("cmbCategoria"))
                .thenReturn("1");



        when(request.getParameter("cmbProveedor"))
                .thenReturn("1");





        servlet.doPost(
                request,
                response
        );





        verify(productoDAO)
                .insert(any(Producto.class));



        verify(movimientoDAO)
                .insert(any());



        verify(detalleDAO)
                .insert(any());



        verify(response)
                .sendRedirect(
                        "ProductoServlet"
                );

    }









    @Test
    void doPost_productoExistente_deberiaAumentarStock()
            throws Exception {



        IngresoServlet servlet =
                new IngresoServlet();



        ProductoDAO productoDAO =
                mock(ProductoDAO.class);


        MovimientoDAO movimientoDAO =
                mock(MovimientoDAO.class);


        DetalleMovimientoDAO detalleDAO =
                mock(DetalleMovimientoDAO.class);




        setField(
                servlet,
                "productoDAO",
                productoDAO
        );


        setField(
                servlet,
                "movimientoDAO",
                movimientoDAO
        );


        setField(
                servlet,
                "detalleMovimientoDAO",
                detalleDAO
        );




        Producto producto =
                new Producto();



        producto.setNombre("Galletita");
        producto.setStock(20);




        when(productoDAO.getByNombre("Galletita"))
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




        when(request.getParameter("txtNombreProducto"))
                .thenReturn("Galletita");



        when(request.getParameter("txtPrecioCompra"))
                .thenReturn("100");



        when(request.getParameter("txtPrecioVenta"))
                .thenReturn("200");



        when(request.getParameter("txtCantidad"))
                .thenReturn("5");



        when(request.getParameter("cmbCategoria"))
                .thenReturn("1");



        when(request.getParameter("cmbProveedor"))
                .thenReturn("1");






        servlet.doPost(
                request,
                response
        );





        verify(productoDAO)
                .update(producto);



        verify(movimientoDAO)
                .insert(any());



        verify(detalleDAO)
                .insert(any());



        assert producto.getStock() == 25;



        verify(response)
                .sendRedirect(
                        "ProductoServlet"
                );

    }
}