package controllers;

import java.util.Scanner;
import models.Producto;
import services.InventarioService;

public class InventarioController {
    private  InventarioService service = new InventarioService();
    private  Scanner scanner = new Scanner(System.in);

    public void agregarProducto() { // Agregar producto 

        System.out.println("\n=== === === AGREGAR PRODUCTO === === ===");

        // Código automático
        String codigo = service.generarCodigo();
        

        // Nombre producto
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }

        // descripcion del procducto 
        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine().trim();
        if (descripcion.isEmpty()) {
            System.out.println("La Descripcion no puede estar vacía.");
            return;
        }

        // Cantidad
        int cantidad;
        try {
            System.out.print("Cantidad: ");
            cantidad = Integer.parseInt(scanner.nextLine());
            if (cantidad < 0) {
                System.out.println("La cantidad no puede ser negativa.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: La cantidad debe ser un número.");
            return;
        }

        // Precio
        double precio;
        try {
            System.out.print("Precio: ");
            precio = Double.parseDouble(scanner.nextLine());
            if (precio < 0) {
                System.out.println("El precio no puede ser negativo.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El precio debe ser un número.");
            return;
        }

        // Crear producto
        Producto producto = new Producto(codigo, nombre, descripcion, cantidad, precio);

        // Guardar producto
        service.agregarProducto(producto);

        System.out.println("Producto agregado correctamente.");
    }

    public void listarProductos() {
        System.out.println("\n=== LISTA DE PRODUCTOS ===");

        for (Producto p : service.listarProductos()) {
            System.out.println("Código: " + p.getCodigo());
            System.out.println("Nombre: " + p.getNombre());
            System.out.println("Descripción: " + p.getdescripcion());
            System.out.println("Cantidad: " + p.getCantidad());
            System.out.println("Precio: " + p.getPrecio());
            System.out.println("----------------------------------");
        }
    
    }

    public void eliminarProducto() {
        System.out.print("Ingrese el código del producto a eliminar: ");
        String codigo = scanner.nextLine().trim();

        boolean eliminado = service.eliminarProducto(codigo);

        if (eliminado) {
            System.out.println("Producto eliminado correctamente.");
        } else {
            System.out.println("No se encontró un producto con ese código.");
        }
    }


}