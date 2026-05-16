package controllers.inventario;

import java.util.List;
import models.Producto;
import services.InventarioService;
import utils.InputUtils;

public abstract class InventarioBaseController {
    protected final InventarioService service;

    public InventarioBaseController(InventarioService service) {
        this.service = service;
    }

    protected Producto seleccionarProducto() {
        String busqueda = InputUtils.leerTexto("Ingrese búsqueda o código exacto: ");
        List<Producto> resultados = service.buscarCoincidencias(busqueda);

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron productos.");
            return null;
        }

        if (resultados.size() == 1) {
            return resultados.get(0);
        }

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
}
