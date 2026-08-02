package entities;

public class DetalleMovimiento {

    private int idDetalleMovimiento;
    private Producto producto;
    private Movimiento movimiento;
    private int cantidad;

    public DetalleMovimiento(){}

    public DetalleMovimiento(int idDetalleMovimiento,Producto producto,Movimiento movimiento,int cantidad){
        this.producto=producto;
        this.movimiento=movimiento;
        this.cantidad=cantidad;
    }

    public int getIdDetalleMovimiento() {
        return idDetalleMovimiento;
    }

    public void setIdDetalleMovimiento(int idDetalleMovimiento) {
        this.idDetalleMovimiento = idDetalleMovimiento;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Movimiento getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
