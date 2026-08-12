package dao;

import entities.Proveedor;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ProveedorDAOTest {

    private Proveedor crearProveedor() {
        Proveedor proveedor = new Proveedor();

        proveedor.setIdProveedor(1);
        proveedor.setNombre("Los Rusitos");
        proveedor.setTelefono("3452556677");
        proveedor.setEmail("losrusitos@gmail.com");
        proveedor.setDireccion("calle 31");

        return proveedor;
    }

    @Test
    void insert_deberiaRegistrarProveedor() throws Exception {

        // Arrange
        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);

        when(conn.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS)))
                .thenReturn(pst);

        ProveedorDAO dao = spy(new ProveedorDAO());
        doReturn(conn).when(dao).obtenerConexion();

        Proveedor proveedor = crearProveedor();

        // Act
        dao.insert(proveedor);

        // Assert
        verify(pst).setString(1, "Los Rusitos");
        verify(pst).setString(2, "3452556677");
        verify(pst).setString(3, "losrusitos@gmail.com");
        verify(pst).setString(4, "calle 31");
        verify(pst).executeUpdate();
    }

    @Test
    void getById_deberiaDevolverProveedor() throws Exception {

        // Arrange
        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(conn.prepareStatement(anyString())).thenReturn(pst);
        when(pst.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);

        when(rs.getInt("idProveedor")).thenReturn(1);
        when(rs.getString("nombre")).thenReturn("Los Rusitos");
        when(rs.getString("telefono")).thenReturn("3452556677");
        when(rs.getString("email")).thenReturn("losrusitos@gmail.com");
        when(rs.getString("direccion")).thenReturn("calle 31");

        ProveedorDAO dao = spy(new ProveedorDAO());
        doReturn(conn).when(dao).obtenerConexion();

        // Act
        Proveedor resultado = dao.getById(1);

        // Assert
        assertNotNull(resultado);
        assertEquals("Los Rusitos", resultado.getNombre());
        assertEquals("losrusitos@gmail.com", resultado.getEmail());
    }

    @Test
    void getById_deberiaDevolverNullSiNoExiste() throws Exception {

        // Arrange
        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(conn.prepareStatement(anyString())).thenReturn(pst);
        when(pst.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(false);

        ProveedorDAO dao = spy(new ProveedorDAO());
        doReturn(conn).when(dao).obtenerConexion();

        // Act
        Proveedor resultado = dao.getById(99);

        // Assert
        assertNull(resultado);
    }

    @Test
    void existsById_deberiaSerTrueSiExiste() throws Exception {

        // Arrange
        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(conn.prepareStatement(anyString())).thenReturn(pst);
        when(pst.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);

        ProveedorDAO dao = spy(new ProveedorDAO());
        doReturn(conn).when(dao).obtenerConexion();

        // Act
        boolean resultado = dao.existsById(1);

        // Assert
        assertTrue(resultado);
    }

    @Test
    void existsById_deberiaSerFalseSiNoExiste() throws Exception {

        // Arrange
        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(conn.prepareStatement(anyString())).thenReturn(pst);
        when(pst.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(false);

        ProveedorDAO dao = spy(new ProveedorDAO());
        doReturn(conn).when(dao).obtenerConexion();

        // Act
        boolean resultado = dao.existsById(99);

        // Assert
        assertFalse(resultado);
    }

    @Test
    void delete_deberiaEliminarProveedor() throws Exception {

        // Arrange
        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);

        when(conn.prepareStatement(anyString())).thenReturn(pst);

        ProveedorDAO dao = spy(new ProveedorDAO());
        doReturn(conn).when(dao).obtenerConexion();

        // Act
        dao.delete(1);

        // Assert
        verify(pst).setInt(1, 1);
        verify(pst).executeUpdate();
    }

    @Test
    void update_deberiaActualizarProveedor() throws Exception {

        // Arrange
        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);

        when(conn.prepareStatement(anyString())).thenReturn(pst);

        ProveedorDAO dao = spy(new ProveedorDAO());

        doReturn(true).when(dao).existsById(1);
        doReturn(conn).when(dao).obtenerConexion();

        Proveedor proveedor = crearProveedor();

        // Act
        dao.update(proveedor);

        // Assert
        verify(pst).setString(1, "Los Rusitos");
        verify(pst).setInt(5, 1);
        verify(pst).executeUpdate();
    }
}