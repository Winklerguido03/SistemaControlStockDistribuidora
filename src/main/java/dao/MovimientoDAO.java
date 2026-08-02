package dao;

import entities.Movimiento;
import entities.Usuario;
import enums.TipoMovimiento;
import interfaces.AdmConexion;
import interfaces.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovimientoDAO implements DAO<Movimiento,Integer>, AdmConexion {

    private Connection conn;

    private static final String SQL_INSERT =
            "INSERT INTO movimiento (fecha, tipo, Usuario_idUsuario) VALUES (?, ?, ?)";

    private static final String SQL_GETALL =
            "SELECT * FROM movimiento ORDER BY idMovimiento DESC";

    private static final String SQL_GETBYID =
            "SELECT * FROM movimiento WHERE idMovimiento=?";

    private static final String SQL_DELETE =
            "DELETE FROM movimiento WHERE idMovimiento=?";

    private static final String SQL_UPDATE =
            "UPDATE movimiento SET fecha=?, tipo=? WHERE idMovimiento=?";

    @Override
    public List<Movimiento> getAll() {

        List<Movimiento> lista = new ArrayList<>();

        try{

            conn = obtenerConexion();

            PreparedStatement pst = conn.prepareStatement(SQL_GETALL);

            ResultSet rs = pst.executeQuery();

            while(rs.next()){

                Movimiento m = new Movimiento();

                m.setIdMovimiento(rs.getInt("idMovimiento"));
                m.setFecha(rs.getDate("fecha"));
                m.setTipo(TipoMovimiento.valueOf(rs.getString("tipo")));
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("Usuario_idUsuario"));
                m.setUsuario(usuario);

                lista.add(m);

            }

            rs.close();
            pst.close();
            conn.close();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return lista;
    }

    @Override
    public void insert(Movimiento movimiento) {

        try {

            conn = obtenerConexion();

            PreparedStatement pst = conn.prepareStatement(
                    SQL_INSERT,
                    Statement.RETURN_GENERATED_KEYS);

            pst.setDate(1, new java.sql.Date(movimiento.getFecha().getTime()));
            pst.setString(2, movimiento.getTipo().name());
            pst.setInt(3, movimiento.getUsuario().getIdUsuario());

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();

            if(rs.next()){
                movimiento.setIdMovimiento(rs.getInt(1));
            }

            rs.close();
            pst.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Movimiento movimiento) {

        try{

            conn = obtenerConexion();

            PreparedStatement pst = conn.prepareStatement(SQL_UPDATE);

            pst.setDate(1,new java.sql.Date(movimiento.getFecha().getTime()));
            pst.setString(2,movimiento.getTipo().name());
            pst.setInt(3,movimiento.getIdMovimiento());

            pst.executeUpdate();

            pst.close();
            conn.close();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public Movimiento getById(Integer id) {

        Movimiento m = null;

        try{

            conn = obtenerConexion();

            PreparedStatement pst = conn.prepareStatement(SQL_GETBYID);

            pst.setInt(1,id);

            ResultSet rs = pst.executeQuery();

            if(rs.next()){

                m = new Movimiento();

                m.setIdMovimiento(rs.getInt("idMovimiento"));
                m.setFecha(rs.getDate("fecha"));
                m.setTipo(TipoMovimiento.valueOf(rs.getString("tipo")));
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("Usuario_idUsuario"));
                m.setUsuario(usuario);

            }

            rs.close();
            pst.close();
            conn.close();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return m;
    }



    @Override
    public void delete(Integer id) {

        try{

            conn = obtenerConexion();

            PreparedStatement pst = conn.prepareStatement(SQL_DELETE);

            pst.setInt(1,id);

            pst.executeUpdate();

            pst.close();
            conn.close();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean existsById(Integer id) {

        return getById(id)!=null;

    }

    @Override
    public Connection obtenerConexion() throws SQLException {
        return AdmConexion.super.obtenerConexion();
    }
}
