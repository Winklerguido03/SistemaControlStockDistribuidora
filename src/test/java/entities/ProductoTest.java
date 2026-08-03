package entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {

    @Test
    void constructorVacio_deberiaCrearProducto(){

        Producto producto = new Producto();


        assertNotNull(producto);

    }





    @Test
    void constructorConParametros_deberiaAsignarValores(){

        Producto producto =
                new Producto(
                        "Galletitas",
                        500,
                        800,
                        30
                );



        assertEquals(
                "Galletitas",
                producto.getNombre()
        );


        assertEquals(
                500,
                producto.getPrecioCompra()
        );


        assertEquals(
                800,
                producto.getPrecioVenta()
        );


        assertEquals(
                30,
                producto.getStock()
        );

    }





    @Test
    void setIdProducto_deberiaGuardarId(){

        Producto producto = new Producto();


        producto.setIdProducto(1);



        assertEquals(
                1,
                producto.getIdProducto()
        );

    }






    @Test
    void setNombre_deberiaGuardarNombre(){

        Producto producto = new Producto();


        producto.setNombre("Alfajor");



        assertEquals(
                "Alfajor",
                producto.getNombre()
        );

    }






    @Test
    void setCategoria_deberiaGuardarCategoria(){

        Producto producto = new Producto();


        Categoria categoria = new Categoria();

        categoria.setNombre("Golosinas");



        producto.setCategoriaProducto(categoria);



        assertEquals(
                categoria,
                producto.getCategoriaProducto()
        );

    }







    @Test
    void setProveedor_deberiaGuardarProveedor(){

        Producto producto = new Producto();


        Proveedor proveedor = new Proveedor();

        proveedor.setNombre("Proveedor Test");



        producto.setProveedorProducto(proveedor);



        assertEquals(
                proveedor,
                producto.getProveedorProducto()
        );

    }







    @Test
    void setPrecioCompra_deberiaGuardarPrecio(){

        Producto producto = new Producto();



        producto.setPrecioCompra(1000);



        assertEquals(
                1000,
                producto.getPrecioCompra()
        );

    }







    @Test
    void setPrecioVenta_deberiaGuardarPrecio(){

        Producto producto = new Producto();



        producto.setPrecioVenta(1500);



        assertEquals(
                1500,
                producto.getPrecioVenta()
        );

    }







    @Test
    void setStock_deberiaGuardarStock(){

        Producto producto = new Producto();



        producto.setStock(50);



        assertEquals(
                50,
                producto.getStock()
        );

    }






    @Test
    void modificarProducto_deberiaActualizarTodosLosDatos(){

        Producto producto = new Producto();


        Categoria categoria = new Categoria("Bebidas");

        Proveedor proveedor = new Proveedor();

        proveedor.setNombre("Proveedor");



        producto.setIdProducto(10);
        producto.setNombre("Coca Cola");
        producto.setCategoriaProducto(categoria);
        producto.setProveedorProducto(proveedor);
        producto.setPrecioCompra(1000);
        producto.setPrecioVenta(1500);
        producto.setStock(20);



        assertEquals(10, producto.getIdProducto());
        assertEquals("Coca Cola", producto.getNombre());
        assertEquals(categoria, producto.getCategoriaProducto());
        assertEquals(proveedor, producto.getProveedorProducto());
        assertEquals(1000, producto.getPrecioCompra());
        assertEquals(1500, producto.getPrecioVenta());
        assertEquals(20, producto.getStock());
    }

}