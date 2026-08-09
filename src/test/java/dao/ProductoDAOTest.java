package dao;

import entities.Categoria;
import entities.Producto;
import entities.Proveedor;
import interfaces.AdmConexion;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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

        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);


        when(conn.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS)))
                .thenReturn(pst);


        ProductoDAO dao = Mockito.spy(new ProductoDAO());


        doReturn(conn)
                .when((AdmConexion) dao)
                .obtenerConexion();


        Producto producto = crearProducto();


        dao.insert(producto);


        verify(pst).setString(1, "Palitos Salados 500g");
        verify(pst).setInt(2, 2000);
        verify(pst).setInt(3, 3500);
        verify(pst).setInt(4, 30);
        verify(pst).setInt(5, 1);
        verify(pst).setInt(6, 1);


        verify(pst).executeUpdate();

        verify(conn).close();

    }


    @Test
    void getById_deberiaDevolverProducto() throws Exception {


        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);


        when(conn.prepareStatement(anyString()))
                .thenReturn(pst);


        when(pst.executeQuery())
                .thenReturn(rs);


        when(rs.next())
                .thenReturn(true);


        when(rs.getInt("idProducto"))
                .thenReturn(1);

        when(rs.getString("nombre"))
                .thenReturn("Palitos Salados 500g");


        when(rs.getInt("precio_compra"))
                .thenReturn(2000);


        when(rs.getInt("precio_venta"))
                .thenReturn(3500);


        when(rs.getInt("stock"))
                .thenReturn(30);


        when(rs.getInt("Categoria_idCategoria"))
                .thenReturn(1);


        when(rs.getString("nombreCategoria"))
                .thenReturn("Snacks");


        when(rs.getInt("Proveedor_idProveedor"))
                .thenReturn(1);


        when(rs.getString("nombreProveedor"))
                .thenReturn("Los Rusitos");


        ProductoDAO dao = Mockito.spy(new ProductoDAO());


        doReturn(conn)
                .when((AdmConexion) dao)
                .obtenerConexion();


        Producto producto = dao.getById(1);


        assertNotNull(producto);
        assertEquals("Palitos Salados 500g", producto.getNombre());
        assertEquals(30, producto.getStock());

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


        ProductoDAO dao = Mockito.spy(new ProductoDAO());


        doReturn(conn)
                .when((AdmConexion) dao)
                .obtenerConexion();


        Producto resultado = dao.getById(99);


        assertNull(resultado);

    }


    @Test
    void existsById_deberiaSerTrueCuandoExiste() throws Exception {

        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(conn.prepareStatement(anyString())).thenReturn(pst);
        when(pst.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);

        ProductoDAO dao = Mockito.spy(new ProductoDAO());

        doReturn(conn)
                .when((AdmConexion) dao)
                .obtenerConexion();

        assertTrue(
                dao.existsById(1)
        );

    }


    @Test
    void existsById_deberiaSerFalseCuandoNoExiste() throws Exception {


        ProductoDAO dao = Mockito.spy(new ProductoDAO());


        doReturn(null)
                .when(dao)
                .getById(99);


        assertFalse(
                dao.existsById(99)
        );

    }


    @Test
    void delete_deberiaEliminarProducto() throws Exception {


        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);


        when(conn.prepareStatement(anyString()))
                .thenReturn(pst);


        ProductoDAO dao = Mockito.spy(new ProductoDAO());


        doReturn(conn)
                .when((AdmConexion) dao)
                .obtenerConexion();


        dao.delete(1);


        verify(pst)
                .setInt(1, 1);


        verify(pst)
                .executeUpdate();


    }


    @Test
    void getByNombre_deberiaEncontrarProducto() throws Exception {


        Connection conn = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);


        when(conn.prepareStatement(anyString()))
                .thenReturn(pst);


        when(pst.executeQuery())
                .thenReturn(rs);


        when(rs.next())
                .thenReturn(true);


        when(rs.getInt("idProducto"))
                .thenReturn(1);


        when(rs.getString("nombre"))
                .thenReturn("Palitos Salados 500g");


        ProductoDAO dao = Mockito.spy(new ProductoDAO());


        doReturn(conn)
                .when((AdmConexion) dao)
                .obtenerConexion();


        Producto producto = dao.getByNombre("Palitos Salados 500g");


        assertNotNull(producto);
        assertEquals("Palitos Salados 500g", producto.getNombre());

    }

}