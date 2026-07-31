package entities;

public class Producto {

    private int idProducto;
    private String nombre;
    private Categoria categoriaProducto;
    private int precioCompra;
    private int precioVenta;
    private int stock;

    public Producto(){}

    public Producto(String nombre,int precioCompra,int precioVenta,int stock){
        this.nombre=nombre;
        this.precioCompra=precioCompra;
        this.precioVenta=precioVenta;
        this.stock=stock;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(Categoria categoria) {
        this.categoriaProducto = categoria;
    }

    public int getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(int precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(int precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
