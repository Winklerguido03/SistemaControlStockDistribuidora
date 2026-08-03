package servlets;

import dao.CategoriaDAO;
import dao.ProductoDAO;
import dao.ProveedorDAO;
import entities.Categoria;
import entities.Producto;
import entities.Proveedor;
import jakarta.servlet.RequestDispatcher;
import org.junit.jupiter.api.Test;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DashboardServletTest {

    private void setField(Object objeto, String nombre, Object valor)
            throws Exception {

        Field campo =
                objeto.getClass()
                        .getDeclaredField(nombre);

        campo.setAccessible(true);

        campo.set(objeto, valor);
    }





    @Test
    void doGet_deberiaCalcularDatosDelDashboard()
            throws Exception {


        DashboardServlet servlet =
                new DashboardServlet();



        ProductoDAO productoDAO =
                mock(ProductoDAO.class);


        CategoriaDAO categoriaDAO =
                mock(CategoriaDAO.class);


        ProveedorDAO proveedorDAO =
                mock(ProveedorDAO.class);



        setField(
                servlet,
                "productoDAO",
                productoDAO
        );


        setField(
                servlet,
                "categoriaDAO",
                categoriaDAO
        );


        setField(
                servlet,
                "proveedorDAO",
                proveedorDAO
        );




        Producto p1 = new Producto();

        p1.setNombre("Producto 1");
        p1.setStock(10);



        Producto p2 = new Producto();

        p2.setNombre("Producto 2");
        p2.setStock(0);



        Producto p3 = new Producto();

        p3.setNombre("Producto 3");
        p3.setStock(5);



        List<Producto> productos =
                Arrays.asList(
                        p1,
                        p2,
                        p3
                );



        when(productoDAO.getAll())
                .thenReturn(productos);



        when(categoriaDAO.getAll())
                .thenReturn(
                        Collections.singletonList(
                                new Categoria("Bebidas")
                        )
                );



        when(proveedorDAO.getAll())
                .thenReturn(
                        Collections.singletonList(
                                new Proveedor()
                        )
                );




        HttpServletRequest request =
                mock(HttpServletRequest.class);



        HttpServletResponse response =
                mock(HttpServletResponse.class);



        RequestDispatcher dispatcher =
                mock(RequestDispatcher.class);



        when(request.getRequestDispatcher("index.jsp"))
                .thenReturn(dispatcher);




        servlet.doGet(
                request,
                response
        );




        verify(request)
                .setAttribute(
                        "totalProductos",
                        3
                );


        verify(request)
                .setAttribute(
                        "productosConStock",
                        2L
                );


        verify(request)
                .setAttribute(
                        "productosSinStock",
                        1L
                );


        verify(request)
                .setAttribute(
                        "stockTotal",
                        15
                );


        verify(request)
                .setAttribute(
                        "promedioStock",
                        5.0
                );



        verify(request)
                .setAttribute(
                        "productoMayorStock",
                        p1
                );



        verify(request)
                .setAttribute(
                        "productoMenorStock",
                        p2
                );



        verify(dispatcher)
                .forward(
                        request,
                        response
                );

    }

}