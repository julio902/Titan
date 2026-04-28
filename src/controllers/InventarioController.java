package controllers;

import java.util.List;
import java.util.Scanner;

import models.Producto;
import services.InventarioService;

public class InventarioController {

    private InventarioService service = new InventarioService();
    private Scanner scanner = new Scanner(System.in);

    // ================================
    //        AGREGAR PRODUCTO
    // ================================
    public void agregarProducto() {

        System.out.println("\n=== AGREGAR PRODUCTO ===");

        String codigo = service.generarCodigo();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }

        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine().trim();
        if (descripcion.isEmpty()) {
            System.out.println("La descripción no puede estar vacía.");
            return;
        }

        int cantidad;
        try {
            System.out.print("Cantidad: ");
            cantidad = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Cantidad inválida.");
            return;
        }

        double precio;
        try {
            System.out.print("Precio: ");
            precio = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Precio inválido.");
            return;
        }

        Producto producto = new Producto(codigo, nombre, descripcion, cantidad, precio);
        service.agregarProducto(producto);

        System.out.println("Producto agregado correctamente.");
    }

    // ================================
    //        LISTAR PRODUCTOS
    // ================================
    public void listarProductos() {

        List<Producto> lista = service.listarProductos();

        System.out.println("\n=== === === LISTA DE PRODUCTOS === === ===");

        if (lista.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        for (Producto p : lista) {
            System.out.printf("%-12s: %s%n", "Código", p.getCodigo());
            System.out.printf("%-12s: %s%n", "Nombre", p.getNombre());
            System.out.printf("%-12s: %s%n", "Descripción", p.getDescripcion());
            System.out.printf("%-12s: %d Uds%n", "Cantidad", p.getCantidad());
            System.out.printf("%-12s: $%.2f%n", "Precio", p.getPrecio());
            System.out.println("--------------------------------------------");

        }
    }

    // ================================
    //        BUSCAR PRODUCTO
    // ================================
    public void buscarProducto() {
        System.out.print("Buscar producto (nombre, desc o código): ");
        String busqueda = scanner.nextLine().trim();

        List<Producto> encontrados = service.buscarCoincidencias(busqueda);

        if (encontrados.isEmpty()) {
            System.out.println("No se encontraron productos.");
            return;
        }

        System.out.println("\n=== RESULTADOS ===");
        for (Producto p : encontrados) {
            System.out.println("Código: " + p.getCodigo());
            System.out.println("Nombre: " + p.getNombre());
            System.out.println("Descripción: " + p.getDescripcion());
            System.out.println("Cantidad: " + p.getCantidad()+"UND");
            System.out.println("Precio: $" + p.getPrecio());
            System.out.println("------------------------------------");
        }
    }

    // ================================
    //       MODIFICAR PRODUCTO
    // ================================
    public void modificarProducto() {

        System.out.print("Ingrese código o texto para buscar el producto: ");
        String busqueda = scanner.nextLine().trim();

        List<Producto> resultados = service.buscarCoincidencias(busqueda);

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron productos.");
            return;
        }

        // Si hay más de uno, pedir que seleccione
        if (resultados.size() > 1) {
            System.out.println("\nVarios productos encontrados:");
            for (int i = 0; i < resultados.size(); i++) {
                Producto p = resultados.get(i);
                System.out.println((i + 1) + ". " + p.getNombre() + " (Código: " + p.getCodigo() + ")");
            }

            System.out.print("Seleccione el número del producto: ");
            int opcion = Integer.parseInt(scanner.nextLine()) - 1;

            if (opcion < 0 || opcion >= resultados.size()) {
                System.out.println("Opción inválida.");
                return;
            }

            resultados = List.of(resultados.get(opcion));
        }

        Producto p = resultados.get(0);

        System.out.println("\n=== MODIFICAR PRODUCTO (" + p.getCodigo() + ") ===");

        System.out.print("Nuevo nombre (" + p.getNombre() + "): ");
        String nombre = scanner.nextLine();
        if (nombre.isEmpty()) nombre = p.getNombre();

        System.out.print("Nueva descripción (" + p.getDescripcion() + "): ");
        String descripcion = scanner.nextLine();
        if (descripcion.isEmpty()) descripcion = p.getDescripcion();

        System.out.print("Nueva cantidad (" + p.getCantidad() + "): ");
        String cant = scanner.nextLine();
        int cantidad = cant.isEmpty() ? p.getCantidad() : Integer.parseInt(cant);

        System.out.print("Nuevo precio (" + p.getPrecio() + "): ");
        String pre = scanner.nextLine();
        double precio = pre.isEmpty() ? p.getPrecio() : Double.parseDouble(pre);

        boolean ok = service.modificarProducto(
                p.getCodigo(),
                nombre,
                descripcion,
                cantidad,
                precio
        );

        if (ok)
            System.out.println("Producto modificado correctamente.");
        else
            System.out.println("Error modificando producto.");
    }

    // ================================
    //        ELIMINAR PRODUCTO
    // ================================
    public void eliminarProducto() {
        System.out.print("Código del producto a eliminar: ");
        String codigo = scanner.nextLine().trim();

        boolean eliminado = service.eliminarProducto(codigo);

        if (eliminado)
            System.out.println("Producto eliminado.");
        else
            System.out.println("Producto no encontrado.");
    }
}
