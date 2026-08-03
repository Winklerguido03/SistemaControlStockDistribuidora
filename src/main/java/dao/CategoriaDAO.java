package dao;

import entities.Categoria;
import entities.Producto;
import interfaces.AdmConexion;
import interfaces.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO implements DAO<Categoria, Integer>, AdmConexion {

    private Connection conn = null;

    private static final String SQL_INSERT =
            "INSERT INTO categoria (nombre)" +
                    "VALUES (?)";

    private static final String SQL_UPDATE =
            "UPDATE categoria SET " +
                    "nombre = ? " +
                    "WHERE idCategoria = ?";

    private static final String SQL_DELETE =
            "DELETE FROM categoria WHERE idCategoria = ?";

    private static final String SQL_GETALL =
            "SELECT * FROM categoria ORDER BY idCategoria";

    private static final String SQL_GETBYID =
            "SELECT * FROM categoria WHERE idCategoria = ?";

    @Override
    public List<Categoria> getAll() {
        PreparedStatement pst = null;
        ResultSet rs = null;

        List<Categoria> listaCategorias = new ArrayList<>();

        try {
            conn = obtenerConexion();
            pst = conn.prepareStatement(SQL_GETALL);
            rs = pst.executeQuery();

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setNombre(rs.getString("nombre"));
                listaCategorias.add(categoria);
            }

            rs.close();
            pst.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Hubo un error al obtener la lista de categorias");
            throw new RuntimeException(e);
        }
        return listaCategorias;
    }

    @Override
    public void insert(Categoria objeto) {

        Categoria categoria = objeto;

        PreparedStatement pst = null;

        try {
            conn = obtenerConexion();
            pst = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, categoria.getNombre());

            int resultado = pst.executeUpdate();
            if (resultado == 1) {
                System.out.println("La categoria ha sido registrado");
            } else {
                System.out.println("No se pudo registrar la categoria");
            }

            pst.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Hubo un error al registrar la categoria");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Categoria objeto) {

        Categoria categoria = objeto;

        if (this.existsById(categoria.getIdCategoria())) {
            PreparedStatement pst = null;

            try {

                conn = obtenerConexion();
                pst = conn.prepareStatement(SQL_UPDATE);

                pst.setString(1, categoria.getNombre());

                pst.setInt(2, categoria.getIdCategoria());

                int resultado = pst.executeUpdate();
                if (resultado == 1) {
                    System.out.println("La categoria se ha actualizado");
                } else {
                    System.out.println("No se pudo actualizar la categoria");
                }

                pst.close();
                conn.close();

            } catch (SQLException e) {
                System.out.println("Hubo un error al actualizar la categoria");
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
                System.out.println("Categoria eliminada");
            } else {
                System.out.println("No se pudo eliminar la categoria");
            }

        } catch (SQLException e) {
            System.out.println("Hubo un error al intentar eliminar una categoria");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Categoria getById(Integer id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Categoria categoria = null;

        try {
            conn = obtenerConexion();
            pst = conn.prepareStatement(SQL_GETBYID);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setNombre(rs.getString("nombre"));
            }

            rs.close();
            pst.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoria;
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
