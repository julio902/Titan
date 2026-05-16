package controllers.inventario;

import models.Producto;
import services.InventarioService;
import utils.InputUtils;

public class EliminarController extends InventarioBaseController {
    
    public EliminarController(InventarioService service) {
        super(service);
    }

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
}
