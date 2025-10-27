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
    

    public void eliminarProducto() {  //eliminar producto***
        System.out.print("Ingrese el código del producto a eliminar: ");
        String codigo = scanner.nextLine();
        if (service.eliminarProducto(codigo)) {
            System.out.println(" Producto eliminado.");
        } else {
            System.out.println(" No se encontró el producto.");
        }
    }

    public void modificarProducto() { // modificar producto ***** 
        System.out.print("Ingrese el código del producto a modificar: ");
        String codigo = scanner.nextLine();
        Producto p = service.buscarProductoPorCodigo(codigo);
            // P guarda el producto que encontro 
        if (p == null) {
            System.out.println("❌ No se encontró el producto con ese código.");
            return;
        }

        System.out.println("Producto encontrado: " + p);
        System.out.print("Nuevo nombre (enter para mantener): ");
        String nuevoNombre = scanner.nextLine();
        System.out.print("Nueva cantidad (enter para mantener): ");
        String nuevaCant = scanner.nextLine();
        System.out.print("Nuevo precio (enter para mantener): ");
        String nuevoPrecio = scanner.nextLine();

        // Si el usuario deja el campo vacío, se conserva el valor anterior
        String nombreFinal = nuevoNombre.isEmpty() ? p.getNombre() : nuevoNombre; //operador ternario
        int cantidadFinal = nuevaCant.isEmpty() ? p.getCantidad() : Integer.parseInt(nuevaCant);
        double precioFinal = nuevoPrecio.isEmpty() ? p.getPrecio() : Double.parseDouble(nuevoPrecio);

        boolean actualizado = service.modificarProducto(p.getCodigo(), nombreFinal, cantidadFinal, precioFinal);

        if (actualizado) {
            System.out.println("Producto modificado correctamente.");
        } else {
            System.out.println("No se pudo modificar el producto.");
        }
    }
}