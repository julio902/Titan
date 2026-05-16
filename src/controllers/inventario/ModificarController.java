package controllers.inventario;

import models.Producto;
import services.InventarioService;
import utils.InputUtils;

public class ModificarController extends InventarioBaseController {
    
    public ModificarController(InventarioService service) {
        super(service);
    }

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

        boolean ok = service.modificarProducto(p.getCodigo(), nombre, desc, cantidad, precio);

        if (ok)
            System.out.println("✅ Producto modificado correctamente.");
        else
            System.out.println("❌ Error al modificar el producto.");
    }
}
