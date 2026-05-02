package models;

public class Producto {
    private final String codigo;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private double precio;

    public Producto(String codigo, String nombre,String descripcion, int cantidad, double precio) {
        this.codigo = codigo;
        setNombre(nombre);
        setDescripcion(descripcion);
        setCantidad(cantidad);
        setPrecio(precio);
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

    public double getPrecio() {
        return precio;
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

    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precio = precio;
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
        return cantidad * precio;
    }


    @Override
    public String toString() {
       return String.format("%s - %s | %s | Cant: %d | $%.2f",
                codigo, nombre, descripcion, cantidad, precio);
    }
}
