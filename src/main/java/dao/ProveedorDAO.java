package dao;

import entities.Proveedor;
import entities.Usuario;
import interfaces.AdmConexion;
import interfaces.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO implements DAO<Proveedor,Integer>, AdmConexion {

    private Connection conn = null;

    private static final String SQL_INSERT =
            "INSERT INTO proveedor (nombre, telefono, email, direccion)" +
                    "VALUES (?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE proveedor SET " +
                    "nombre = ?, telefono = ?, email = ?, direccion = ? " +
                    "WHERE idProveedor = ?";

    private static final String SQL_DELETE =
            "DELETE FROM proveedor WHERE idProveedor = ?";

    private static final String SQL_GETALL =
            "SELECT * FROM proveedor ORDER BY idProveedor";

    private static final String SQL_GETBYID =
            "SELECT * FROM proveedor WHERE idProveedor = ?";

    private static final String SQL_GETBYEMAIL =
            "SELECT * FROM proveedor WHERE email = ?";

    @Override
    public List<Proveedor> getAll() {
        PreparedStatement pst = null;
        ResultSet rs = null;

        List<Proveedor> listaProveedores = new ArrayList<>();

        try {
            conn = obtenerConexion();
            pst = conn.prepareStatement(SQL_GETALL);
            rs = pst.executeQuery();

            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("idProveedor"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setEmail(rs.getString("email"));
                proveedor.setDireccion(rs.getString("direccion"));
                listaProveedores.add(proveedor);
            }

            rs.close();
            pst.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Hubo un error al obtener la lista de proveedores");
            throw new RuntimeException(e);
        }
        return listaProveedores;
    }

    @Override
    public void insert(Proveedor objeto) {

        Proveedor proveedor = objeto;

        PreparedStatement pst = null;

        try {
            conn = obtenerConexion();
            pst = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, proveedor.getNombre());
            pst.setString(2, proveedor.getTelefono());
            pst.setString(3, proveedor.getEmail());
            pst.setString(4, proveedor.getDireccion());

            int resultado = pst.executeUpdate();
            if (resultado == 1) {
                System.out.println("El proveedor ha sido registrado");
            } else {
                System.out.println("No se pudo registrar el proveedor");
            }

            pst.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Hubo un error al registrar al proveedor");
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Proveedor objeto) {

        Proveedor proveedor = objeto;

        if (this.existsById(proveedor.getIdProveedor())) {
            PreparedStatement pst = null;

            try {

                conn = obtenerConexion();
                pst = conn.prepareStatement(SQL_UPDATE);

                pst.setString(1, proveedor.getNombre());
                pst.setString(2, proveedor.getTelefono());
                pst.setString(3, proveedor.getEmail());
                pst.setString(4, proveedor.getDireccion());

                pst.setInt(5, proveedor.getIdProveedor());

                int resultado = pst.executeUpdate();
                if (resultado == 1) {
                    System.out.println("El proveedor se ha actualizado");
                } else {
                    System.out.println("No se pudo actualizar el proveedor");
                }

                pst.close();
                conn.close();

            } catch (SQLException e) {
                System.out.println("Hubo un error al actualizar el proveedor");
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
                System.out.println("Proveedor eliminado");
            } else {
                System.out.println("No se pudo eliminar el proveedor");
            }

        } catch (SQLException e) {
            System.out.println("Hubo un error al intentar eliminar un proveedor");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Proveedor getById(Integer id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Proveedor proveedor = null;

        try {
            conn = obtenerConexion();
            pst = conn.prepareStatement(SQL_GETBYID);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("idProveedor"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setEmail(rs.getString("email"));
                proveedor.setDireccion(rs.getString("direccion"));
            }

            rs.close();
            pst.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return proveedor;
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
}
