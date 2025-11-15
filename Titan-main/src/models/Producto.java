package models;

public class Producto {
    private final String codigo;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private double precio;

    public Producto(String codigo, String nombre,String descripcion, int cantidad, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    // Getters para acceder a los datos 
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getdescripcion(){
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    // Setters para modificar los datos la guardados en la lista 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return String.format("%s - %s | Cant: %d | $%.2f", codigo, nombre, cantidad, precio);
    }
}
