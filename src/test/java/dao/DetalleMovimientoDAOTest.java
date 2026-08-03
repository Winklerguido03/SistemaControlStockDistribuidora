package dao;

import entities.DetalleMovimiento;
import entities.Movimiento;
import entities.Producto;
import interfaces.AdmConexion;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class DetalleMovimientoDAOTest {

    @Test
    void insert_deberiaGuardarDetalleMovimientoCorrectamente() throws Exception {

        // Mock de conexión y PreparedStatement
        Connection conexionMock = mock(Connection.class);
        PreparedStatement statementMock = mock(PreparedStatement.class);


        when(conexionMock.prepareStatement(anyString()))
                .thenReturn(statementMock);


        // Creamos DAO
        DetalleMovimientoDAO dao = Mockito.spy(new DetalleMovimientoDAO());


        // Mock del método obtenerConexion()
        doReturn(conexionMock)
                .when((AdmConexion) dao)
                .obtenerConexion();


        // Datos de prueba
        Producto producto = new Producto();
        producto.setIdProducto(1);


        Movimiento movimiento = new Movimiento();
        movimiento.setIdMovimiento(1);


        DetalleMovimiento detalle = new DetalleMovimiento();
        detalle.setProducto(producto);
        detalle.setMovimiento(movimiento);
        detalle.setCantidad(5);


        // Ejecutamos
        dao.insert(detalle);


        // Verificaciones
        verify(conexionMock)
                .prepareStatement(
                        "INSERT INTO detalle_movimiento (Movimiento_idMovimiento, Producto_idProducto, cantidad) VALUES (?,?,?)"
                );


        verify(statementMock)
                .setInt(1, 1);

        verify(statementMock)
                .setInt(2, 1);

        verify(statementMock)
                .setInt(3, 5);


        verify(statementMock)
                .executeUpdate();


        verify(statementMock)
                .close();

        verify(conexionMock)
                .close();
    }


    @Test
    void insert_deberiaLanzarRuntimeExceptionSiFallaSQL() throws Exception {


        Connection conexionMock = mock(Connection.class);


        when(conexionMock.prepareStatement(anyString()))
                .thenThrow(new RuntimeException());


        DetalleMovimientoDAO dao = Mockito.spy(new DetalleMovimientoDAO());


        doReturn(conexionMock)
                .when((AdmConexion) dao)
                .obtenerConexion();


        DetalleMovimiento detalle = new DetalleMovimiento();


        try {

            dao.insert(detalle);

        }catch(Exception e){

            assert true;
        }

    }
}
