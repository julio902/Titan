package controllers;

import java.util.List;
import java.util.Scanner;

import models.Producto;
import services.InventarioService;

public class InventarioController {

    private final InventarioService service;
    private final Scanner scanner = new Scanner(System.in);
    
    public InventarioController(InventarioService service) {
        this.service = service;
    }
    

    // ================================
    // AGREGAR PRODUCTO
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

        if (service.agregarProducto(producto))
            System.out.println("Producto agregado correctamente.");
        else
            System.out.println("Error al agregar producto.");
    }

    // ================================
    // LISTAR PRODUCTOS
    // ================================
    public void listarProductos() {

        List<Producto> lista = service.listarProductos();

        System.out.println("\n=== LISTA DE PRODUCTOS ===");

        if (lista.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        for (Producto p : lista) {
            System.out.println(p); // usa toString()
        }

        System.out.println("----------------------------------");
        System.out.println("Valor total inventario: $" + service.calcularValorTotalInventario());
    }

    // ================================
    // BUSCAR PRODUCTO
    // ================================
    public void buscarProducto() {

        System.out.print("Buscar producto (nombre, código o desc): ");
        String busqueda = scanner.nextLine().trim();

        List<Producto> encontrados = service.buscarCoincidencias(busqueda);

        if (encontrados.isEmpty()) {
            System.out.println("No se encontraron productos.");
            return;
        }

        System.out.println("\nResultados encontrados:");
        for (Producto p : encontrados) {
            System.out.println(p);
        }
    }

    // ================================
    // MÉTODO AUXILIAR: SELECCIONAR PRODUCTO
    // ================================
    private Producto seleccionarProducto() {
        System.out.print("Ingrese búsqueda o código exacto: ");
        String busqueda = scanner.nextLine().trim();

        List<Producto> resultados = service.buscarCoincidencias(busqueda);

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron productos.");
            return null;
        }

        if (resultados.size() == 1) {
            return resultados.get(0);
        }

        // Si hay varios, permitir elegir
        System.out.println("\nSe encontraron múltiples coincidencias. Seleccione una:");
        for (int i = 0; i < resultados.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + resultados.get(i));
        }
        System.out.print("Seleccione el número (o 0 para cancelar): ");
        
        try {
            int idx = Integer.parseInt(scanner.nextLine());
            if (idx > 0 && idx <= resultados.size()) {
                return resultados.get(idx - 1);
            }
        } catch (NumberFormatException e) {
            System.out.println("Selección inválida.");
        }

        return null;
    }

    // ================================
    // MODIFICAR PRODUCTO
    // ================================
    public void modificarProducto() {

        Producto p = seleccionarProducto();

        if (p == null) return;

        System.out.println("\nModificando: " + p.getCodigo());
        System.out.println("Deje en blanco para mantener el valor actual.");

        System.out.print("Nuevo nombre (" + p.getNombre() + "): ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) nombre = p.getNombre();

        System.out.print("Nueva descripción (" + p.getDescripcion() + "): ");
        String desc = scanner.nextLine().trim();
        if (desc.isEmpty()) desc = p.getDescripcion();

        System.out.print("Nueva cantidad (" + p.getCantidad() + "): ");
        String cantStr = scanner.nextLine().trim();
        int cantidad;
        try {
            cantidad = cantStr.isEmpty() ? p.getCantidad() : Integer.parseInt(cantStr);
        } catch (NumberFormatException e) {
            System.out.println("Cantidad inválida. No se cambió el stock.");
            cantidad = p.getCantidad();
        }

        System.out.print("Nuevo precio (" + p.getPrecio() + "): ");
        String precioStr = scanner.nextLine().trim();
        double precio;
        try {
            precio = precioStr.isEmpty() ? p.getPrecio() : Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            System.out.println("Precio inválido. No se cambió el precio.");
            precio = p.getPrecio();
        }

        boolean ok = service.modificarProducto(
                p.getCodigo(), nombre, desc, cantidad, precio
        );

        if (ok)
            System.out.println("✅ Producto modificado correctamente.");
        else
            System.out.println("❌ Error al modificar el producto.");
    }

    // ================================
    // ELIMINAR PRODUCTO
    // ================================
    public void eliminarProducto() {
        Producto p = seleccionarProducto();

        if (p == null) return;

        System.out.println("¿Está seguro de eliminar el producto: " + p.getNombre() + "? (S/N)");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("s")) {
            if (service.eliminarProducto(p.getCodigo()))
                System.out.println("✅ Producto eliminado.");
            else
                System.out.println("❌ Error al eliminar.");
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }

    // ================================
    // VENDER PRODUCTO 🔥
    // ================================
    public void venderProducto() {

        Producto p = seleccionarProducto();
        if (p == null) return;

        System.out.println("Producto: " + p.getNombre() + " (Stock: " + p.getCantidad() + ")");
        
        int cantidad;
        try {
            System.out.print("Cantidad a vender: ");
            cantidad = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Cantidad inválida.");
            return;
        }

        if (service.venderProducto(p.getCodigo(), cantidad))
            System.out.println("✅ Venta realizada.");
        else
            System.out.println("❌ Error en la venta (posible falta de stock).");
    }

    // ================================
    // REPONER PRODUCTO 
    // ================================
    public void reponerProducto() {

        Producto p = seleccionarProducto();
        if (p == null) return;

        System.out.println("Producto: " + p.getNombre() + " (Stock actual: " + p.getCantidad() + ")");

        int cantidad;
        try {
            System.out.print("Cantidad a reponer: ");
            cantidad = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Cantidad inválida.");
            return;
        }

        if (service.reponerProducto(p.getCodigo(), cantidad))
            System.out.println("✅ Stock actualizado.");
        else
            System.out.println("❌ Error al actualizar stock.");
    }
        public double calcularValorTotalInventario() {
            return service.calcularValorTotalInventario();
    }
}