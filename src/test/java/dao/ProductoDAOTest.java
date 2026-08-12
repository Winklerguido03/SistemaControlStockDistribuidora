package dao;

import entities.Categoria;
import entities.Producto;
import entities.Proveedor;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoDAOTest {

    private Producto crearProducto() {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(1);
        categoria.setNombre("Snacks");

        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(1);
        proveedor.setNombre("Los Rusitos");

        Producto producto = new Producto();
        producto.setIdProducto(1);
        producto.setNombre("Palitos Salados 500g");
        producto.setPrecioCompra(2000);
        producto.setPrecioVenta(3500);
        producto.setStock(30);
        producto.setCategoriaProducto(categoria);
        producto.setProveedorProducto(proveedor);

        return producto;
    }

    @Test
    void insert_deberiaRegistrarProducto() throws Exception {

        // Arrange
        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);

        when(conn.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS)))
                .thenReturn(pst);

        ProductoDAO dao = spy(new ProductoDAO());

        doReturn(conn).when(dao).obtenerConexion();

        Producto producto = crearProducto();

        // Act
        dao.insert(producto);

        // Assert
        verify(pst).setString(1, "Palitos Salados 500g");
        verify(pst).setInt(2, 2000);
        verify(pst).setInt(3, 3500);
        verify(pst).setInt(4, 30);
        verify(pst).setInt(5, 1);
        verify(pst).setInt(6, 1);
        verify(pst).executeUpdate();
    }

    @Test
    void getById_deberiaDevolverProducto() throws Exception {

        // Arrange
        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(conn.prepareStatement(anyString())).thenReturn(pst);
        when(pst.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);

        when(rs.getInt("idProducto")).thenReturn(1);
        when(rs.getString("nombre")).thenReturn("Palitos Salados 500g");
        when(rs.getInt("precio_compra")).thenReturn(2000);
        when(rs.getInt("precio_venta")).thenReturn(3500);
        when(rs.getInt("stock")).thenReturn(30);
        when(rs.getInt("Categoria_idCategoria")).thenReturn(1);
        when(rs.getString("nombreCategoria")).thenReturn("Snacks");
        when(rs.getInt("Proveedor_idProveedor")).thenReturn(1);
        when(rs.getString("nombreProveedor")).thenReturn("Los Rusitos");

        ProductoDAO dao = spy(new ProductoDAO());
        doReturn(conn).when(dao).obtenerConexion();

        // Act
        Producto resultado = dao.getById(1);

        // Assert
        assertNotNull(resultado);
        assertEquals("Palitos Salados 500g", resultado.getNombre());
        assertEquals(30, resultado.getStock());
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

        ProductoDAO dao = spy(new ProductoDAO());
        doReturn(conn).when(dao).obtenerConexion();

        // Act
        Producto resultado = dao.getById(99);

        // Assert
        assertNull(resultado);
    }

    @Test
    void existsById_deberiaSerTrueCuandoExiste() throws Exception {

        // Arrange
        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(conn.prepareStatement(anyString())).thenReturn(pst);
        when(pst.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);

        ProductoDAO dao = spy(new ProductoDAO());
        doReturn(conn).when(dao).obtenerConexion();

        // Act
        boolean resultado = dao.existsById(1);

        // Assert
        assertTrue(resultado);
    }

    @Test
    void existsById_deberiaSerFalseCuandoNoExiste() throws Exception {

        // Arrange
        ProductoDAO dao = spy(new ProductoDAO());

        doReturn(null).when(dao).getById(99);

        // Act
        boolean resultado = dao.existsById(99);

        // Assert
        assertFalse(resultado);
    }

    @Test
    void delete_deberiaEliminarProducto() throws Exception {

        // Arrange
        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);

        when(conn.prepareStatement(anyString())).thenReturn(pst);

        ProductoDAO dao = spy(new ProductoDAO());
        doReturn(conn).when(dao).obtenerConexion();

        // Act
        dao.delete(1);

        // Assert
        verify(pst).setInt(1, 1);
        verify(pst).executeUpdate();
    }

    @Test
    void getByNombre_deberiaEncontrarProducto() throws Exception {

        // Arrange
        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(conn.prepareStatement(anyString())).thenReturn(pst);
        when(pst.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);

        when(rs.getInt("idProducto")).thenReturn(1);
        when(rs.getString("nombre")).thenReturn("Palitos Salados 500g");

        ProductoDAO dao = spy(new ProductoDAO());
        doReturn(conn).when(dao).obtenerConexion();

        // Act
        Producto resultado = dao.getByNombre("Palitos Salados 500g");

        // Assert
        assertNotNull(resultado);
        assertEquals("Palitos Salados 500g", resultado.getNombre());
    }

    @Test
    void getAll_deberiaDevolverListaDeProductos() throws Exception {

        // Arrange
        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(conn.prepareStatement(anyString())).thenReturn(pst);
        when(pst.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true, false);

        when(rs.getInt("idProducto")).thenReturn(1);
        when(rs.getString("nombre")).thenReturn("Palitos Salados 500g");

        ProductoDAO dao = spy(new ProductoDAO());
        doReturn(conn).when(dao).obtenerConexion();

        // Act
        List<Producto> resultado = dao.getAll();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(pst).executeQuery();
    }

    @Test
    void update_deberiaActualizarProducto() throws Exception {

        // Arrange
        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);

        when(conn.prepareStatement(anyString())).thenReturn(pst);

        ProductoDAO dao = spy(new ProductoDAO());
        doReturn(conn).when(dao).obtenerConexion();
        doReturn(true).when(dao).existsById(1);

        Producto producto = crearProducto();

        // Act
        dao.update(producto);

        // Assert
        verify(pst).setString(1, "Palitos Salados 500g");
        verify(pst).setInt(7, 1);
        verify(pst).executeUpdate();
    }

    @Test
    void getProductosConStock_deberiaDevolverLista() throws Exception {

        // Arrange
        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(conn.prepareStatement(anyString())).thenReturn(pst);
        when(pst.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true, false);

        when(rs.getInt("idProducto")).thenReturn(1);
        when(rs.getString("nombre")).thenReturn("Palitos Salados 500g");

        ProductoDAO dao = spy(new ProductoDAO());
        doReturn(conn).when(dao).obtenerConexion();

        // Act
        List<Producto> resultado = dao.getProductosConStock();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    void getProductosPorProveedor_deberiaDevolverLista() throws Exception {

        // Arrange
        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(conn.prepareStatement(anyString())).thenReturn(pst);
        when(pst.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true, false);

        when(rs.getInt("idProducto")).thenReturn(1);

        ProductoDAO dao = spy(new ProductoDAO());
        doReturn(conn).when(dao).obtenerConexion();

        // Act
        List<Producto> resultado = dao.getProductosPorProveedor(1);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(pst).setInt(1, 1);
    }

    @Test
    void getProductosPorCategoria_deberiaDevolverLista() throws Exception {

        // Arrange
        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(conn.prepareStatement(anyString())).thenReturn(pst);
        when(pst.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true, false);

        when(rs.getInt("idProducto")).thenReturn(1);

        ProductoDAO dao = spy(new ProductoDAO());
        doReturn(conn).when(dao).obtenerConexion();

        // Act
        List<Producto> resultado = dao.getProductosPorCategoria(1);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(pst).setInt(1, 1);
    }
}

