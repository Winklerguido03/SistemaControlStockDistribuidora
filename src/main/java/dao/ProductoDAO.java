package dao;

import entities.Categoria;
import entities.Producto;
import entities.Proveedor;
import entities.Categoria;
import interfaces.AdmConexion;
import interfaces.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO implements DAO <Producto, Integer>, AdmConexion {

    private Connection conn = null;

    private static final String SQL_INSERT =
            "INSERT INTO producto (nombre, precio_compra, precio_venta, stock, Categoria_idCategoria, Proveedor_idProveedor)" +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE producto SET " +
                    "nombre = ?, precio_compra = ?, precio_venta = ?, stock = ?, Categoria_idCategoria = ?, Proveedor_idProveedor = ? " +
                    "WHERE idProducto = ?";

    private static final String SQL_DELETE =
            "DELETE FROM producto WHERE idProducto = ?";

    private static final String SQL_GETALL =
            "SELECT p.*, c.nombre AS nombreCategoria, pr.nombre AS nombreProveedor " +
                    "FROM producto p " +
                    "INNER JOIN categoria c ON p.Categoria_idCategoria = c.idCategoria " +
                    "INNER JOIN proveedor pr ON p.Proveedor_idProveedor = pr.idProveedor " +
                    "ORDER BY p.idProducto";

    private static final String SQL_GETBYID =
            "SELECT p.*, c.nombre AS nombreCategoria, pr.nombre AS nombreProveedor " +
                    "FROM producto p " +
                    "INNER JOIN categoria c ON p.Categoria_idCategoria = c.idCategoria " +
                    "INNER JOIN proveedor pr ON p.Proveedor_idProveedor = pr.idProveedor " +
                    "WHERE p.idProducto = ?";

    private static final String SQL_GETBYNOMBRE =
            "SELECT * FROM producto WHERE nombre = ?";

    private static final String SQL_GETPRODUCTOSCONSTOCK =
    "SELECT p.*, c.nombre AS nombreCategoria, pr.nombre AS nombreProveedor " +
            "FROM producto p " +
            "INNER JOIN categoria c ON p.Categoria_idCategoria = c.idCategoria " +
            "INNER JOIN proveedor pr ON p.Proveedor_idProveedor = pr.idProveedor " +
            "WHERE stock > 0 ORDER BY p.idProducto";

    private static final String SQL_GETPRODUCTOSPORPROVEEDOR =
            "SELECT p.*, c.nombre AS nombreCategoria, pr.nombre AS nombreProveedor " +
            "FROM producto p " +
            "INNER JOIN categoria c ON p.Categoria_idCategoria = c.idCategoria " +
            "INNER JOIN proveedor pr ON p.Proveedor_idProveedor = pr.idProveedor " +
            "WHERE p.Proveedor_idProveedor = ?";

    private static final String SQL_GETPRODUCTOSPORCATEGORIA =
            "SELECT p.*, c.nombre AS nombreCategoria, pr.nombre AS nombreProveedor " +
                    "FROM producto p " +
                    "INNER JOIN categoria c ON p.Categoria_idCategoria = c.idCategoria " +
                    "INNER JOIN proveedor pr ON p.Proveedor_idProveedor = pr.idProveedor " +
                    "WHERE p.Categoria_idCategoria = ?";

    @Override
    public List<Producto> getAll() {
        PreparedStatement pst = null;
        ResultSet rs = null;

        List<Producto> listaProductos = new ArrayList<>();

        try {
            conn = obtenerConexion();
            pst = conn.prepareStatement(SQL_GETALL);
            rs = pst.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecioCompra(rs.getInt("precio_compra"));
                producto.setPrecioVenta(rs.getInt("precio_venta"));
                producto.setStock(rs.getInt("stock"));
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("Categoria_idCategoria"));
                categoria.setNombre(rs.getString("nombre"));
                producto.setCategoriaProducto(categoria);

                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("Proveedor_idProveedor"));
                proveedor.setNombre(rs.getString("nombre"));
                producto.setProveedorProducto(proveedor);
                listaProductos.add(producto);
            }

            rs.close();
            pst.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Hubo un error al obtener la lista de productos");
            throw new RuntimeException(e);
        }
        return listaProductos;
    }

    @Override
    public void insert(Producto objeto) {

        Producto producto = objeto;

        PreparedStatement pst = null;

        try {
            conn = obtenerConexion();
            pst = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, producto.getNombre());
            pst.setInt(2, producto.getPrecioCompra());
            pst.setInt(3, producto.getPrecioVenta());
            pst.setInt(4,producto.getStock());
            pst.setInt(5, producto.getCategoriaProducto().getIdCategoria());
            pst.setInt(6,producto.getProveedorProducto().getIdProveedor());

            int resultado = pst.executeUpdate();
            if (resultado == 1) {
                System.out.println("El producto ha sido registrado");
            } else {
                System.out.println("No se pudo registrar el producto");
            }

            pst.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Hubo un error al registrar el producto");
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Producto objeto) {

        Producto producto = objeto;

        if (this.existsById(producto.getIdProducto())) {
            PreparedStatement pst = null;

            try {

                conn = obtenerConexion();
                pst = conn.prepareStatement(SQL_UPDATE);

                pst.setString(1, producto.getNombre());
                pst.setInt(2, producto.getPrecioCompra());
                pst.setInt(3, producto.getPrecioVenta());
                pst.setInt(4, producto.getStock());
                pst.setInt(5, producto.getCategoriaProducto().getIdCategoria());
                pst.setInt(6, producto.getProveedorProducto().getIdProveedor());
                pst.setInt(7, producto.getIdProducto());

                int resultado = pst.executeUpdate();
                if (resultado == 1) {
                    System.out.println("El producto se ha actualizado");
                } else {
                    System.out.println("No se pudo actualizar el producto");
                }

                pst.close();
                conn.close();

            } catch (SQLException e) {
                System.out.println("Hubo un error al actualizar el producto");
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void delete(Integer id) {

        PreparedStatement pst = null;

        try {
            conn = obtenerConexion();
            pst = conn.prepareStatement(SQL_DELETE);

            pst.setInt(1, id);

            int resultado = pst.executeUpdate();
            if (resultado == 1) {
                System.out.println("Producto eliminado");
            } else {
                System.out.println("No se pudo eliminar el producto");
            }

        } catch (SQLException e) {
            System.out.println("Hubo un error al intentar eliminar un producto");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Producto getById(Integer id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Producto producto = null;

        try {
            conn = obtenerConexion();
            pst = conn.prepareStatement(SQL_GETBYID);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("nombre"));

                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("Categoria_idCategoria"));
                categoria.setNombre(rs.getString("nombreCategoria"));
                producto.setCategoriaProducto(categoria);

                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("Proveedor_idProveedor"));
                proveedor.setNombre(rs.getString("nombreProveedor"));
                producto.setProveedorProducto(proveedor);

                producto.setPrecioCompra(rs.getInt("precio_compra"));
                producto.setPrecioVenta(rs.getInt("precio_venta"));
                producto.setStock(rs.getInt("stock"));
                producto.setPrecioCompra(rs.getInt("precio_compra"));
                producto.setPrecioVenta(rs.getInt("precio_venta"));
                producto.setStock(rs.getInt("stock"));
            }

            rs.close();
            pst.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return producto;
    }

    @Override
    public boolean existsById(Integer id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            conn = obtenerConexion();
            pst = conn.prepareStatement(SQL_GETBYID);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                existe = true;
            }

            rs.close();
            pst.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return existe;
    }

    public Producto getByNombre(String nombre) {

        PreparedStatement pst = null;
        ResultSet rs = null;
        Producto producto = null;

        try {

            conn = obtenerConexion();

            pst = conn.prepareStatement(SQL_GETBYNOMBRE);
            pst.setString(1, nombre);

            rs = pst.executeQuery();

            if (rs.next()) {

                producto = new Producto();

                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecioCompra(rs.getInt("precio_compra"));
                producto.setPrecioVenta(rs.getInt("precio_venta"));
                producto.setStock(rs.getInt("stock"));

                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("Categoria_idCategoria"));
                categoria.setNombre(rs.getString("nombre"));
                producto.setCategoriaProducto(categoria);

                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("Proveedor_idProveedor"));
                proveedor.setNombre(rs.getString("nombre"));
                producto.setProveedorProducto(proveedor);
            }

            rs.close();
            pst.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return producto;
    }

    public List<Producto> getProductosConStock() {

        List<Producto> lista = new ArrayList<>();

        try (Connection conn = obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(SQL_GETPRODUCTOSCONSTOCK);
             ResultSet rs = ps.executeQuery()) {


            while (rs.next()) {

                Producto producto = new Producto();

                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecioCompra(rs.getInt("precio_compra"));
                producto.setPrecioVenta(rs.getInt("precio_venta"));
                producto.setStock(rs.getInt("stock"));


                // Categoría y proveedor
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("Categoria_idCategoria"));
                categoria.setNombre(rs.getString("nombreCategoria"));
                producto.setCategoriaProducto(categoria);

                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("Proveedor_idProveedor"));
                proveedor.setNombre(rs.getString("nombreProveedor"));
                producto.setProveedorProducto(proveedor);


                lista.add(producto);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return lista;
    }

    public List<Producto> getProductosPorProveedor(int idProveedor){

        List<Producto> lista = new ArrayList<>();

        try(Connection conn = obtenerConexion();
            PreparedStatement ps = conn.prepareStatement(SQL_GETPRODUCTOSPORPROVEEDOR)) {


            ps.setInt(1,idProveedor);

            ResultSet rs = ps.executeQuery();


            while(rs.next()){

                Producto producto = new Producto();

                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecioCompra(rs.getInt("precio_compra"));
                producto.setPrecioVenta(rs.getInt("precio_venta"));
                producto.setStock(rs.getInt("stock"));

                // Categoría y proveedor
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("Categoria_idCategoria"));
                categoria.setNombre(rs.getString("nombreCategoria"));
                producto.setCategoriaProducto(categoria);

                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("Proveedor_idProveedor"));
                proveedor.setNombre(rs.getString("nombreProveedor"));
                producto.setProveedorProducto(proveedor);


                lista.add(producto);
            }


        } catch(Exception e){
            throw new RuntimeException(e);
        }


        return lista;
    }

    public List<Producto> getProductosPorCategoria(int idCategoria) {

        List<Producto> lista = new ArrayList<>();

        try (Connection conn = obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(SQL_GETPRODUCTOSPORCATEGORIA)) {


            ps.setInt(1, idCategoria);

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {


                Producto producto = new Producto();


                producto.setIdProducto(
                        rs.getInt("idProducto")
                );


                producto.setNombre(
                        rs.getString("nombre")
                );


                producto.setPrecioCompra(
                        rs.getInt("precio_compra")
                );


                producto.setPrecioVenta(
                        rs.getInt("precio_venta")
                );


                producto.setStock(
                        rs.getInt("stock")
                );


                Categoria categoria = new Categoria();

                categoria.setIdCategoria(
                        rs.getInt("Categoria_idCategoria")
                );

                categoria.setNombre(
                        rs.getString("nombreCategoria")
                );


                producto.setCategoriaProducto(categoria);


                Proveedor proveedor = new Proveedor();

                proveedor.setIdProveedor(
                        rs.getInt("Proveedor_idProveedor")
                );

                proveedor.setNombre(
                        rs.getString("nombreProveedor")
                );


                producto.setProveedorProducto(proveedor);


                lista.add(producto);

            }


        } catch (Exception e) {

            throw new RuntimeException(e);

        }


        return lista;
    }
}
