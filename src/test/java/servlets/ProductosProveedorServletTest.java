package servlets;

import org.junit.jupiter.api.Test;

import dao.ProductoDAO;
import dao.ProveedorDAO;

import entities.Producto;
import entities.Proveedor;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductosProveedorServletTest {

    private void setField(Object objeto, String nombre, Object valor)
            throws Exception {


        Field campo =
                objeto.getClass()
                        .getDeclaredField(nombre);


        campo.setAccessible(true);


        campo.set(objeto, valor);

    }







    @Test
    void doGet_deberiaMostrarProductosPorProveedor()
            throws Exception {


        ProductosProveedorServlet servlet =
                new ProductosProveedorServlet();




        ProductoDAO productoDAO =
                mock(ProductoDAO.class);



        ProveedorDAO proveedorDAO =
                mock(ProveedorDAO.class);





        setField(
                servlet,
                "productoDAO",
                productoDAO
        );



        setField(
                servlet,
                "proveedorDAO",
                proveedorDAO
        );






        Producto producto =
                new Producto();


        producto.setNombre("Galletitas");



        List<Producto> productos =
                Arrays.asList(producto);






        Proveedor proveedor =
                new Proveedor();


        proveedor.setIdProveedor(1);
        proveedor.setNombre("Proveedor Test");







        when(productoDAO.getProductosPorProveedor(1))
                .thenReturn(productos);




        when(proveedorDAO.getById(1))
                .thenReturn(proveedor);







        HttpServletRequest request =
                mock(HttpServletRequest.class);



        HttpServletResponse response =
                mock(HttpServletResponse.class);




        RequestDispatcher dispatcher =
                mock(RequestDispatcher.class);






        when(request.getParameter("idProveedor"))
                .thenReturn("1");



        when(request.getRequestDispatcher("productosProveedor.jsp"))
                .thenReturn(dispatcher);







        servlet.doGet(
                request,
                response
        );







        verify(productoDAO)
                .getProductosPorProveedor(1);




        verify(proveedorDAO)
                .getById(1);





        verify(request)
                .setAttribute(
                        "listaProductos",
                        productos
                );





        verify(request)
                .setAttribute(
                        "proveedor",
                        proveedor
                );





        verify(dispatcher)
                .forward(
                        request,
                        response
                );

    }

}