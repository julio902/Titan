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

        System.out.print("Buscar producto: ");
        String busqueda = scanner.nextLine().trim();

        List<Producto> encontrados = service.buscarCoincidencias(busqueda);

        if (encontrados.isEmpty()) {
            System.out.println("No se encontraron productos.");
            return;
        }

        for (Producto p : encontrados) {
            System.out.println(p);
        }
    }

    // ================================
    // MODIFICAR PRODUCTO
    // ================================
    public void modificarProducto() {

        System.out.print("Buscar producto: ");
        String busqueda = scanner.nextLine().trim();

        List<Producto> resultados = service.buscarCoincidencias(busqueda);

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron productos.");
            return;
        }

        Producto p = resultados.get(0);

        System.out.println("Modificando: " + p.getCodigo());

        System.out.print("Nuevo nombre (" + p.getNombre() + "): ");
        String nombre = scanner.nextLine();
        if (nombre.isEmpty()) nombre = p.getNombre();

        System.out.print("Nueva descripción (" + p.getDescripcion() + "): ");
        String desc = scanner.nextLine();
        if (desc.isEmpty()) desc = p.getDescripcion();

        System.out.print("Nueva cantidad (" + p.getCantidad() + "): ");
        String cantStr = scanner.nextLine();
        int cantidad = cantStr.isEmpty() ? p.getCantidad() : Integer.parseInt(cantStr);

        System.out.print("Nuevo precio (" + p.getPrecio() + "): ");
        String precioStr = scanner.nextLine();
        double precio = precioStr.isEmpty() ? p.getPrecio() : Double.parseDouble(precioStr);

        boolean ok = service.modificarProducto(
                p.getCodigo(), nombre, desc, cantidad, precio
        );

        if (ok)
            System.out.println("Producto modificado.");
        else
            System.out.println("Error.");
    }

    // ================================
    // ELIMINAR PRODUCTO
    // ================================
    public void eliminarProducto() {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        if (service.eliminarProducto(codigo))
            System.out.println("Producto eliminado.");
        else
            System.out.println("No encontrado.");
    }

    // ================================
    // VENDER PRODUCTO 🔥
    // ================================
    public void venderProducto() {

        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        int cantidad;
        try {
            System.out.print("Cantidad: ");
            cantidad = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Cantidad inválida.");
            return;
        }

        if (service.venderProducto(codigo, cantidad))
            System.out.println("Venta realizada.");
        else
            System.out.println("Error en la venta.");
    }

    // ================================
    // REPONER PRODUCTO 🔥
    // ================================
    public void reponerProducto() {

        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        int cantidad;
        try {
            System.out.print("Cantidad: ");
            cantidad = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Cantidad inválida.");
            return;
        }

        if (service.reponerProducto(codigo, cantidad))
            System.out.println("Stock actualizado.");
        else
            System.out.println("Error.");
    }
        public double calcularValorTotalInventario() {
            return service.calcularValorTotalInventario();
    }
}