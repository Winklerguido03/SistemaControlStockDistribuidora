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
    void insert_deberiaGuardarDetalleMovimiento() throws Exception {

        Connection conexion = mock(Connection.class);
        PreparedStatement statement = mock(PreparedStatement.class);

        when(conexion.prepareStatement(anyString()))
                .thenReturn(statement);

        DetalleMovimientoDAO dao =
                Mockito.spy(new DetalleMovimientoDAO());

        doReturn(conexion)
                .when((AdmConexion) dao)
                .obtenerConexion();

        Producto producto = new Producto();
        producto.setIdProducto(1);

        Movimiento movimiento = new Movimiento();
        movimiento.setIdMovimiento(1);

        DetalleMovimiento detalle = new DetalleMovimiento();
        detalle.setProducto(producto);
        detalle.setMovimiento(movimiento);
        detalle.setCantidad(5);

        dao.insert(detalle);

        verify(statement).setInt(1, 1);
        verify(statement).setInt(2, 1);
        verify(statement).setInt(3, 5);

        verify(statement).executeUpdate();
        verify(statement).close();
        verify(conexion).close();
    }
}