package dao;

import entities.Movimiento;
import entities.Usuario;
import enums.TipoMovimiento;
import interfaces.AdmConexion;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovimientoDAOTest {

    @Test
    void insert_deberiaGuardarMovimientoYAsignarIdGenerado() throws Exception {

        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);


        when(conn.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS)))
                .thenReturn(pst);

        when(pst.getGeneratedKeys())
                .thenReturn(rs);

        when(rs.next())
                .thenReturn(true);

        when(rs.getInt(1))
                .thenReturn(10);


        MovimientoDAO dao = Mockito.spy(new MovimientoDAO());

        doReturn(conn)
                .when((AdmConexion) dao)
                .obtenerConexion();


        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);


        Movimiento movimiento = new Movimiento();
        movimiento.setFecha(new java.util.Date());
        movimiento.setTipo(TipoMovimiento.INGRESO);
        movimiento.setUsuario(usuario);


        dao.insert(movimiento);


        assertEquals(10, movimiento.getIdMovimiento());


        verify(pst).setInt(3,1);
        verify(pst).executeUpdate();
        verify(pst).close();
        verify(conn).close();

    }



    @Test
    void getById_deberiaDevolverMovimientoCuandoExiste() throws Exception {


        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);


        when(conn.prepareStatement(anyString()))
                .thenReturn(pst);

        when(pst.executeQuery())
                .thenReturn(rs);


        when(rs.next())
                .thenReturn(true);

        when(rs.getInt("idMovimiento"))
                .thenReturn(1);

        when(rs.getDate("fecha"))
                .thenReturn(new java.sql.Date(System.currentTimeMillis()));

        when(rs.getString("tipo"))
                .thenReturn("INGRESO");

        when(rs.getInt("Usuario_idUsuario"))
                .thenReturn(1);



        MovimientoDAO dao = Mockito.spy(new MovimientoDAO());

        doReturn(conn)
                .when((AdmConexion) dao)
                .obtenerConexion();



        Movimiento movimiento = dao.getById(1);



        assertNotNull(movimiento);
        assertEquals(1,movimiento.getIdMovimiento());
        assertEquals(1,movimiento.getUsuario().getIdUsuario());


        verify(pst).setInt(1,1);

    }




    @Test
    void getById_deberiaDevolverNullSiNoExiste() throws Exception {


        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);


        when(conn.prepareStatement(anyString()))
                .thenReturn(pst);


        when(pst.executeQuery())
                .thenReturn(rs);


        when(rs.next())
                .thenReturn(false);



        MovimientoDAO dao = Mockito.spy(new MovimientoDAO());

        doReturn(conn)
                .when((AdmConexion) dao)
                .obtenerConexion();



        Movimiento resultado = dao.getById(99);


        assertNull(resultado);

    }




    @Test
    void delete_deberiaEliminarMovimiento() throws Exception {


        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);


        when(conn.prepareStatement(anyString()))
                .thenReturn(pst);



        MovimientoDAO dao = Mockito.spy(new MovimientoDAO());

        doReturn(conn)
                .when((AdmConexion) dao)
                .obtenerConexion();



        dao.delete(1);



        verify(pst)
                .setInt(1,1);

        verify(pst)
                .executeUpdate();

        verify(conn)
                .close();

    }




    @Test
    void update_deberiaActualizarMovimiento() throws Exception {


        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);


        when(conn.prepareStatement(anyString()))
                .thenReturn(pst);



        MovimientoDAO dao = Mockito.spy(new MovimientoDAO());


        doReturn(conn)
                .when((AdmConexion) dao)
                .obtenerConexion();



        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);


        Movimiento movimiento = new Movimiento();

        movimiento.setIdMovimiento(2);
        movimiento.setFecha(new java.util.Date());
        movimiento.setTipo(TipoMovimiento.EGRESO);
        movimiento.setUsuario(usuario);



        dao.update(movimiento);



        verify(pst)
                .setInt(3,2);

        verify(pst)
                .executeUpdate();


    }




    @Test
    void existsById_deberiaSerTrueSiExiste() throws Exception {

        MovimientoDAO dao = Mockito.spy(new MovimientoDAO());


        doReturn(new Movimiento())
                .when(dao)
                .getById(1);



        assertTrue(dao.existsById(1));

    }

}
