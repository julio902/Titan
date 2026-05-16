package models;

public class Producto {
    private final String codigo;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private double valorCompra;
    private double valorVenta;

    public Producto(String codigo, String nombre,String descripcion, int cantidad, double valorCompra, double valorVenta) {
        this.codigo = codigo;
        setNombre(nombre);
        setDescripcion(descripcion);
        setCantidad(cantidad);
        setValorCompra(valorCompra);
        setValorVenta(valorVenta);
    }

    // Getters para acceder a los datos
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public double getValorVenta() {
        return valorVenta;
    }

    // Setters para modificar los datos la guardados en la lista 
     public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre inválido");
        }
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public void setCantidad(int cantidad) {
    if (cantidad < 0) {
        throw new IllegalArgumentException("La cantidad no puede ser negativa");
    }
    this.cantidad = cantidad;
}

    public void setValorCompra(double valorCompra) {
        if (valorCompra < 0) {
            throw new IllegalArgumentException("El valor de compra no puede ser negativo");
        }
        this.valorCompra = valorCompra;
    }

    public void setValorVenta(double valorVenta) {
        if (valorVenta < 0) {
            throw new IllegalArgumentException("El valor de venta no puede ser negativo");
        }
        this.valorVenta = valorVenta;
    }
     // Comportamiento POO vender y reponer productos, calcular valor del inventario
    public void vender(int cantidadVendida) {
        if (cantidadVendida <= 0 || cantidadVendida > cantidad) {
            throw new IllegalArgumentException("Cantidad inválida o stock insuficiente");
        }
        this.cantidad -= cantidadVendida;
    }

    public void reponer(int cantidadAgregada) {
        if (cantidadAgregada <= 0) {
            throw new IllegalArgumentException("Cantidad inválida");
        }
        this.cantidad += cantidadAgregada;
    }

    public double calcularValorInventario() {
        return cantidad * valorCompra;
    }


    @Override
    public String toString() {
       return String.format("%s - %s | %s | Cant: %d | Costo: $%.2f | Venta: $%.2f",
                codigo, nombre, descripcion, cantidad, valorCompra, valorVenta);
    }
}
