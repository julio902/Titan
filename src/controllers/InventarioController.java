package controllers;

import java.util.Scanner;
import models.Producto;
import services.InventarioService;

public class InventarioController {
    private  InventarioService service = new InventarioService();
    private  Scanner scanner = new Scanner(System.in);

    public void agregarProducto() { // agregar producctos***********
        
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Cantidad: ");
        int cantidad = Integer.parseInt(scanner.nextLine());
        System.out.print("Precio: ");
        double precio = Double.parseDouble(scanner.nextLine());

        Producto producto = new Producto(codigo, nombre, cantidad, precio);
        service.agregarProducto(producto); // agrega el producto nuevo 
        System.out.println("Producto agregado correctamente.");
    }
    

    public void listarProductos() {  // lista los productos que ya estan guardados
        System.out.println("=== Lista de Productos ===");
        for (Producto p : service.listarProductos()) {  // recorre la lista de producto y cada posiscion la guarda en la variable p
            System.out.println(p); // muestra cada elemento de la lista guardado 
        }
    }
    

    public void eliminarProducto() {
        System.out.print("Ingrese el código del producto a eliminar: ");
        String codigo = scanner.nextLine();
        if (service.eliminarProducto(codigo)) {
            System.out.println(" Producto eliminado.");
        } else {
            System.out.println(" No se encontró el producto.");
        }
    }
}