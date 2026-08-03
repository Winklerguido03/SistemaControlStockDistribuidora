package dao;

import entities.Usuario;
import interfaces.AdmConexion;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class UsuarioDAOTest {

    private Usuario crearUsuario(){

        Usuario usuario = new Usuario();

        usuario.setIdUsuario(1);
        usuario.setNombre("Guido");
        usuario.setApellido("Winkler");
        usuario.setUsername("guido123");
        usuario.setPassword("123456");

        return usuario;
    }



    @Test
    void insert_deberiaRegistrarUsuario() throws Exception {


        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);


        when(conn.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS)))
                .thenReturn(pst);



        UsuarioDAO dao = Mockito.spy(new UsuarioDAO());


        doReturn(conn)
                .when((AdmConexion) dao)
                .obtenerConexion();



        Usuario usuario = crearUsuario();



        dao.insert(usuario);



        verify(pst).setString(1,"Guido");
        verify(pst).setString(2,"Winkler");
        verify(pst).setString(3,"guido123");
        verify(pst).setString(4,"123456");


        verify(pst).executeUpdate();


        verify(conn).close();

    }





    @Test
    void getById_deberiaDevolverUsuario() throws Exception {


        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);



        when(conn.prepareStatement(anyString()))
                .thenReturn(pst);


        when(pst.executeQuery())
                .thenReturn(rs);


        when(rs.next())
                .thenReturn(true);



        when(rs.getInt("idUsuario"))
                .thenReturn(1);


        when(rs.getString("nombre"))
                .thenReturn("Guido");


        when(rs.getString("apellido"))
                .thenReturn("Winkler");


        when(rs.getString("username"))
                .thenReturn("guido123");


        when(rs.getString("password"))
                .thenReturn("123456");



        UsuarioDAO dao = Mockito.spy(new UsuarioDAO());



        doReturn(conn)
                .when((AdmConexion) dao)
                .obtenerConexion();



        Usuario usuario = dao.getById(1);



        assertNotNull(usuario);

        assertEquals(
                "Guido",
                usuario.getNombre()
        );


        assertEquals(
                "guido123",
                usuario.getUsername()
        );

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



        UsuarioDAO dao = Mockito.spy(new UsuarioDAO());


        doReturn(conn)
                .when((AdmConexion) dao)
                .obtenerConexion();



        Usuario usuario = dao.getById(99);



        assertNull(usuario);

    }








    @Test
    void getByUsername_deberiaEncontrarUsuario() throws Exception {


        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);



        when(conn.prepareStatement(anyString()))
                .thenReturn(pst);


        when(pst.executeQuery())
                .thenReturn(rs);


        when(rs.next())
                .thenReturn(true);



        when(rs.getInt("idUsuario"))
                .thenReturn(1);


        when(rs.getString("username"))
                .thenReturn("guido123");


        when(rs.getString("password"))
                .thenReturn("123456");



        UsuarioDAO dao = Mockito.spy(new UsuarioDAO());


        doReturn(conn)
                .when((AdmConexion) dao)
                .obtenerConexion();



        Usuario usuario = dao.getByUsername("guido123");



        assertNotNull(usuario);

        assertEquals(
                "guido123",
                usuario.getUsername()
        );

    }








    @Test
    void existsById_deberiaSerTrueSiExiste() throws Exception {


        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);



        when(conn.prepareStatement(anyString()))
                .thenReturn(pst);


        when(pst.executeQuery())
                .thenReturn(rs);


        when(rs.next())
                .thenReturn(true);



        UsuarioDAO dao = Mockito.spy(new UsuarioDAO());


        doReturn(conn)
                .when((AdmConexion) dao)
                .obtenerConexion();



        assertTrue(
                dao.existsById(1)
        );

    }







    @Test
    void delete_deberiaEliminarUsuario() throws Exception {


        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);



        when(conn.prepareStatement(anyString()))
                .thenReturn(pst);



        UsuarioDAO dao = Mockito.spy(new UsuarioDAO());


        doReturn(conn)
                .when((AdmConexion) dao)
                .obtenerConexion();



        dao.delete(1);



        verify(pst)
                .setInt(1,1);


        verify(pst)
                .executeUpdate();

    }








    @Test
    void update_deberiaActualizarUsuario() throws Exception {


        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);



        when(conn.prepareStatement(anyString()))
                .thenReturn(pst);



        UsuarioDAO dao = Mockito.spy(new UsuarioDAO());



        doReturn(true)
                .when(dao)
                .existsById(1);



        doReturn(conn)
                .when((AdmConexion) dao)
                .obtenerConexion();




        Usuario usuario = crearUsuario();



        dao.update(usuario);



        verify(pst)
                .setString(1,"Guido");


        verify(pst)
                .setInt(5,1);


        verify(pst)
                .executeUpdate();

    }

}