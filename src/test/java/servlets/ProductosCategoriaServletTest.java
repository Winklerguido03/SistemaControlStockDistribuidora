package servlets;

import org.junit.jupiter.api.Test;

import dao.CategoriaDAO;
import dao.ProductoDAO;

import entities.Categoria;
import entities.Producto;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;

class ProductosCategoriaServletTest {

    private void setField(Object objeto, String nombre, Object valor)
            throws Exception {


        Field campo =
                objeto.getClass()
                        .getDeclaredField(nombre);


        campo.setAccessible(true);


        campo.set(objeto, valor);

    }







    @Test
    void doGet_deberiaMostrarProductosPorCategoria()
            throws Exception {


        ProductosCategoriaServlet servlet =
                new ProductosCategoriaServlet();



        ProductoDAO productoDAO =
                mock(ProductoDAO.class);


        CategoriaDAO categoriaDAO =
                mock(CategoriaDAO.class);




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





        Producto producto =
                new Producto();


        producto.setNombre("Chocolate");



        List<Producto> productos =
                Arrays.asList(producto);




        Categoria categoria =
                new Categoria();


        categoria.setIdCategoria(1);
        categoria.setNombre("Golosinas");





        when(productoDAO.getProductosPorCategoria(1))
                .thenReturn(productos);



        when(categoriaDAO.getById(1))
                .thenReturn(categoria);






        HttpServletRequest request =
                mock(HttpServletRequest.class);



        HttpServletResponse response =
                mock(HttpServletResponse.class);




        RequestDispatcher dispatcher =
                mock(RequestDispatcher.class);





        when(request.getParameter("idCategoria"))
                .thenReturn("1");



        when(request.getRequestDispatcher("productosCategoria.jsp"))
                .thenReturn(dispatcher);






        servlet.doGet(
                request,
                response
        );






        verify(productoDAO)
                .getProductosPorCategoria(1);



        verify(categoriaDAO)
                .getById(1);




        verify(request)
                .setAttribute(
                        "listaProductos",
                        productos
                );



        verify(request)
                .setAttribute(
                        "categoria",
                        categoria
                );




        verify(dispatcher)
                .forward(
                        request,
                        response
                );

    }
}