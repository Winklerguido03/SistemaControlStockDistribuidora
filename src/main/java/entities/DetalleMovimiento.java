package entities;

public class DetalleMovimiento {

    private int idDetalleMovimiento;
    private Producto producto;
    private int cantidad;

    public DetalleMovimiento(){}

    public DetalleMovimiento(int idDetalleMovimiento,Producto producto,int cantidad){
        this.producto=producto;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
