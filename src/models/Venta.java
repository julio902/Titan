package models;

/*
 * Clase Venta
 * Representa una venta realizada en el sistema
 */

public class Venta {

    // Número único de venta
    private String numeroVenta;

    // Usuario vendedor
    private String vendedor;

    // Datos cliente
    private String cedulaCliente;

    private String nombreCliente;

    private String telefonoCliente;

    // Producto vendido
    private String producto;

    // Cantidad vendida
    private int cantidad;

    // Total de la venta
    private double total;

    /*
     * Constructor
     */

    public Venta(
            String numeroVenta,
            String vendedor,
            String cedulaCliente,
            String nombreCliente,
            String telefonoCliente,
            String producto,
            int cantidad,
            double total
    ) {

        this.numeroVenta = numeroVenta;

        this.vendedor = vendedor;

        this.cedulaCliente = cedulaCliente;

        this.nombreCliente = nombreCliente;

        this.telefonoCliente = telefonoCliente;

        this.producto = producto;

        this.cantidad = cantidad;

        this.total = total;
    }

    // GETTERS

    public String getNumeroVenta() {
        return numeroVenta;
    }

    public String getVendedor() {
        return vendedor;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public String getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getTotal() {
        return total;
    }

    /*
     * Convierte objeto en texto
     * para guardar en TXT
     */

    @Override
    public String toString() {

        return numeroVenta + "," +
               vendedor + "," +
               cedulaCliente + "," +
               nombreCliente + "," +
               telefonoCliente + "," +
               producto + "," +
               cantidad + "," +
               total;
    }
}