package dao;

import entities.Usuario;
import interfaces.AdmConexion;
import interfaces.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements DAO<Usuario, Integer>, AdmConexion {

    private Connection conn = null;

    private static final String SQL_INSERT =
            "INSERT INTO usuario (nombre, apellido, username, password )" +
                    "VALUES (?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE usuario SET " +
                    "nombre = ?, apellido = ?, username = ?, password = ? " +
                    "WHERE idUsuario = ?";

    private static final String SQL_DELETE =
            "DELETE FROM usuario WHERE idUsuario = ?";

    private static final String SQL_GETALL =
            "SELECT * FROM usuario ORDER BY idUsuario";

    private static final String SQL_GETBYID =
            "SELECT * FROM usuario WHERE idUsuario = ?";

    private static final String SQL_GETBYUSERNAME =
            "SELECT * FROM usuario WHERE username = ?";

    @Override
    public List<Usuario> getAll() {

        PreparedStatement pst = null;
        ResultSet rs = null;

        List<Usuario> listaUsuarios = new ArrayList<>();

        try {
            conn = obtenerConexion();
            pst = conn.prepareStatement(SQL_GETALL);
            rs = pst.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setPassword(rs.getString("apellido"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                listaUsuarios.add(usuario);
            }

            rs.close();
            pst.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Hubo un error al obtener la lista de usuarios");
            throw new RuntimeException(e);
        }
        return listaUsuarios;

    }

    @Override
    public void insert(Usuario objeto) {

        Usuario usuario = objeto;

        PreparedStatement pst = null;

        try {
            conn = obtenerConexion();
            pst = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getApellido());
            pst.setString(3, usuario.getUsername());
            pst.setString(4, usuario.getPassword());

            int resultado = pst.executeUpdate();
            if (resultado == 1) {
                System.out.println("El usuario ha sido registrado");
            } else {
                System.out.println("No se pudo registrar el usuario");
            }

            pst.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Hubo un error al registrar al usuario");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Usuario objeto) {

        Usuario usuario = objeto;

        if (this.existsById(usuario.getIdUsuario())) {
            PreparedStatement pst = null;

            try {

                conn = obtenerConexion();
                pst = conn.prepareStatement(SQL_UPDATE);

                pst.setString(1, usuario.getNombre());
                pst.setString(2, usuario.getApellido());
                pst.setString(3, usuario.getUsername());
                pst.setString(4, usuario.getPassword());

                pst.setInt(5, usuario.getIdUsuario());

                int resultado = pst.executeUpdate();
                if (resultado == 1) {
                    System.out.println("El usuario se ha actualizado");
                } else {
                    System.out.println("No se pudo actualizar el usuario");
                }

                pst.close();
                conn.close();

            } catch (SQLException e) {
                System.out.println("Hubo un error al actualizar el usuario");
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
                System.out.println("Usuario eliminado");
            } else {
                System.out.println("No se pudo eliminar el usuario");
            }

        } catch (SQLException e) {
            System.out.println("Hubo un error al intentar eliminar un usuario");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Usuario getById(Integer id) {

        PreparedStatement pst = null;
        ResultSet rs = null;
        Usuario usuario = null;

        try {
            conn = obtenerConexion();
            pst = conn.prepareStatement(SQL_GETBYID);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
            }

            rs.close();
            pst.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
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

    public Usuario getByUsername(String id) {

        PreparedStatement psBuscar = null;
        ResultSet rs = null;
        Usuario usuario = null;

        try {
            conn = obtenerConexion();
            psBuscar = conn.prepareStatement(SQL_GETBYUSERNAME);
            psBuscar.setString(1, id);
            rs = psBuscar.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));

            }

            rs.close();
            psBuscar.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
}
