package entities;

import enums.TipoMovimiento;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MovimientoTest {

    @Test
    void constructorVacio_deberiaCrearMovimiento(){

        Movimiento movimiento = new Movimiento();


        assertNotNull(movimiento);

    }



    @Test
    void constructorConParametros_deberiaAsignarFechaYTipo(){

        Date fecha = new Date();


        Movimiento movimiento =
                new Movimiento(
                        fecha,
                        TipoMovimiento.INGRESO
                );



        assertEquals(
                fecha,
                movimiento.getFecha()
        );


        assertEquals(
                TipoMovimiento.INGRESO,
                movimiento.getTipo()
        );

    }





    @Test
    void setIdMovimiento_deberiaGuardarId(){

        Movimiento movimiento = new Movimiento();


        movimiento.setIdMovimiento(10);



        assertEquals(
                10,
                movimiento.getIdMovimiento()
        );

    }





    @Test
    void setFecha_deberiaGuardarFecha(){

        Movimiento movimiento = new Movimiento();


        Date fecha = new Date();


        movimiento.setFecha(fecha);



        assertEquals(
                fecha,
                movimiento.getFecha()
        );

    }





    @Test
    void setTipo_deberiaGuardarTipo(){

        Movimiento movimiento = new Movimiento();



        movimiento.setTipo(
                TipoMovimiento.EGRESO
        );



        assertEquals(
                TipoMovimiento.EGRESO,
                movimiento.getTipo()
        );

    }





    @Test
    void setUsuario_deberiaGuardarUsuario(){

        Movimiento movimiento = new Movimiento();


        Usuario usuario = new Usuario();


        movimiento.setUsuario(usuario);



        assertEquals(
                usuario,
                movimiento.getUsuario()
        );

    }





    @Test
    void modificarMovimiento_deberiaActualizarDatos(){

        Movimiento movimiento = new Movimiento();


        Date fecha = new Date();


        Usuario usuario = new Usuario();



        movimiento.setIdMovimiento(5);
        movimiento.setFecha(fecha);
        movimiento.setTipo(TipoMovimiento.INGRESO);
        movimiento.setUsuario(usuario);



        assertEquals(
                5,
                movimiento.getIdMovimiento()
        );


        assertEquals(
                fecha,
                movimiento.getFecha()
        );


        assertEquals(
                TipoMovimiento.INGRESO,
                movimiento.getTipo()
        );


        assertEquals(
                usuario,
                movimiento.getUsuario()
        );

    }

}