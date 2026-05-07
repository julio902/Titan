package controllers;

import java.util.List;

import models.Producto;
import services.InventarioService;
import utils.InputUtils;

public class InventarioController {

    private final InventarioService service;
    
    public InventarioController(InventarioService service) {
        this.service = service;
    }
    

    // ================================
    // AGREGAR PRODUCTO
    // ================================
    public void agregarProducto() {

        System.out.println("\n=== AGREGAR PRODUCTO ===");

        String codigo = service.generarCodigo();

        String nombre = InputUtils.leerTexto("Nombre: ");
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }

        String descripcion = InputUtils.leerTexto("Descripción: ");
        if (descripcion.isEmpty()) {
            System.out.println("La descripción no puede estar vacía.");
            return;
        }

        int cantidad = InputUtils.leerEntero("Cantidad: ");
        double precio = InputUtils.leerDecimal("Precio: ");

        Producto producto = new Producto(codigo, nombre, descripcion, cantidad, precio);

        if (service.agregarProducto(producto))
            System.out.println("✅ Producto agregado correctamente. Código asignado: " + codigo);
        else
            System.out.println("❌ Error al agregar producto.");
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

        String busqueda = InputUtils.leerTexto("Buscar producto (nombre, código o desc): ");

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
        String busqueda = InputUtils.leerTexto("Ingrese búsqueda o código exacto: ");

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
        
        int idx = InputUtils.leerEntero("Seleccione el número (o 0 para cancelar): ");
        if (idx > 0 && idx <= resultados.size()) {
            return resultados.get(idx - 1);
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

        String nombre = InputUtils.leerTexto("Nuevo nombre (" + p.getNombre() + "): ");
        if (nombre.isEmpty()) nombre = p.getNombre();

        String desc = InputUtils.leerTexto("Nueva descripción (" + p.getDescripcion() + "): ");
        if (desc.isEmpty()) desc = p.getDescripcion();

        int cantidad = InputUtils.leerEnteroOpcional("Nueva cantidad (" + p.getCantidad() + "): ", p.getCantidad());
        double precio = InputUtils.leerDecimalOpcional("Nuevo precio (" + p.getPrecio() + "): ", p.getPrecio());

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

        String confirm = InputUtils.leerTexto("¿Está seguro de eliminar el producto: " + p.getNombre() + "? (S/N): ").toLowerCase();

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
    // VENDER PRODUCTO 
    // ================================
    public void venderProducto() {

        Producto p = seleccionarProducto();
        if (p == null) return;

        System.out.println("Producto: " + p.getNombre() + " (Stock: " + p.getCantidad() + ")");
        
        int cantidad = InputUtils.leerEntero("Cantidad a vender: ");

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

        int cantidad = InputUtils.leerEntero("Cantidad a reponer: ");

        if (service.reponerProducto(p.getCodigo(), cantidad))
            System.out.println("✅ Stock actualizado.");
        else
            System.out.println("❌ Error al actualizar stock.");
    }

    public double calcularValorTotalInventario() {
        return service.calcularValorTotalInventario();
    }
}