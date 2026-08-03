package entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DetalleMovimientoTest {

    @Test
    void constructorVacio_deberiaCrearDetalleMovimiento(){

        DetalleMovimiento detalle = new DetalleMovimiento();


        assertNotNull(detalle);

    }



    @Test
    void constructorConParametros_deberiaAsignarValores(){

        Producto producto = new Producto();
        producto.setIdProducto(1);


        Movimiento movimiento = new Movimiento();
        movimiento.setIdMovimiento(10);



        DetalleMovimiento detalle =
                new DetalleMovimiento(
                        5,
                        producto,
                        movimiento,
                        20
                );



        assertEquals(
                producto,
                detalle.getProducto()
        );


        assertEquals(
                movimiento,
                detalle.getMovimiento()
        );


        assertEquals(
                20,
                detalle.getCantidad()
        );


        // Este test actualmente fallaría porque el constructor no asigna el ID
        assertEquals(
                5,
                detalle.getIdDetalleMovimiento()
        );

    }





    @Test
    void setIdDetalleMovimiento_deberiaGuardarId(){

        DetalleMovimiento detalle = new DetalleMovimiento();


        detalle.setIdDetalleMovimiento(3);



        assertEquals(
                3,
                detalle.getIdDetalleMovimiento()
        );

    }






    @Test
    void setProducto_deberiaGuardarProducto(){

        DetalleMovimiento detalle = new DetalleMovimiento();


        Producto producto = new Producto();


        detalle.setProducto(producto);



        assertEquals(
                producto,
                detalle.getProducto()
        );

    }






    @Test
    void setMovimiento_deberiaGuardarMovimiento(){

        DetalleMovimiento detalle = new DetalleMovimiento();


        Movimiento movimiento = new Movimiento();


        detalle.setMovimiento(movimiento);



        assertEquals(
                movimiento,
                detalle.getMovimiento()
        );

    }






    @Test
    void setCantidad_deberiaGuardarCantidad(){

        DetalleMovimiento detalle = new DetalleMovimiento();


        detalle.setCantidad(50);



        assertEquals(
                50,
                detalle.getCantidad()
        );

    }

}