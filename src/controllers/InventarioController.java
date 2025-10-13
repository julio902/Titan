package controllers;

import models.Producto;
import services.InventarioService;
import java.util.Scanner;

public class InventarioController {
    private InventarioService service = new InventarioService();
    private Scanner scanner = new Scanner(System.in);

    public void agregarProducto() {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Cantidad: ");
        int cantidad = Integer.parseInt(scanner.nextLine());
        System.out.print("Precio: ");
        double precio = Double.parseDouble(scanner.nextLine());

        Producto producto = new Producto(codigo, nombre, cantidad, precio);
        service.agregarProducto(producto);
        System.out.println("✅ Producto agregado correctamente.");
    }

    public void listarProductos() {
        System.out.println("=== Lista de Productos ===");
        for (Producto p : service.listarProductos()) {
            System.out.println(p);
        }
    }

    public void eliminarProducto() {
        System.out.print("Ingrese el código del producto a eliminar: ");
        String codigo = scanner.nextLine();
        if (service.eliminarProducto(codigo)) {
            System.out.println("✅ Producto eliminado.");
        } else {
            System.out.println("❌ No se encontró el producto.");
        }
    }
}