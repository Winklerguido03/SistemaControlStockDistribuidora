package dao;

import entities.DetalleMovimiento;
import interfaces.AdmConexion;
import interfaces.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalleMovimientoDAO implements DAO<DetalleMovimiento,Integer>, AdmConexion {
    private Connection conn;

    private static final String SQL_INSERT =
            "INSERT INTO detalle_movimiento (Movimiento_idMovimiento, Producto_idProducto, cantidad) VALUES (?,?,?)";

    @Override
    public void insert(DetalleMovimiento detalle){

        try{

            conn = obtenerConexion();

            PreparedStatement pst = conn.prepareStatement(SQL_INSERT);

            pst.setInt(1, detalle.getMovimiento().getIdMovimiento());
            pst.setInt(2, detalle.getProducto().getIdProducto());
            pst.setInt(3, detalle.getCantidad());

            pst.executeUpdate();

            pst.close();
            conn.close();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<DetalleMovimiento> getAll() {
        return new ArrayList<>();
    }

    @Override
    public void update(DetalleMovimiento objeto) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public DetalleMovimiento getById(Integer id) {
        return null;
    }

    @Override
    public boolean existsById(Integer id) {
        return false;
    }

}
