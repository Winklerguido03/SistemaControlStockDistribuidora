package entities;

import enums.TipoMovimiento;

import java.util.Date;

public class Movimiento {

    private int idMovimiento;
    private Date Fecha;
    private TipoMovimiento Tipo;
    private Usuario usuario;

    public Movimiento (){}

    public Movimiento(Date Fecha,TipoMovimiento Tipo){
        this.Fecha=Fecha;
        this.Tipo=Tipo;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public TipoMovimiento getTipo() {
        return Tipo;
    }

    public void setTipo(TipoMovimiento tipo) {
        Tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
