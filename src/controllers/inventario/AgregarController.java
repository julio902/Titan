package controllers.inventario;

import models.Producto;
import services.InventarioService;
import utils.InputUtils;

public class AgregarController extends InventarioBaseController {
    
    public AgregarController(InventarioService service) {
        super(service);
    }

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
        double valorCompra = InputUtils.leerDecimal("Valor: ");
        double valorVenta = InputUtils.leerDecimal("Valor de venta: ");

        Producto producto = new Producto(codigo, nombre, descripcion, cantidad, valorCompra, valorVenta);

        if (service.agregarProducto(producto))
            System.out.println("✅ Producto agregado correctamente. Código asignado: " + codigo);
        else
            System.out.println("❌ Error al agregar producto.");
    }
}
