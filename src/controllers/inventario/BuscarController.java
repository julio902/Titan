package controllers.inventario;

import java.util.List;
import models.Producto;
import services.InventarioService;
import utils.InputUtils;

public class BuscarController extends InventarioBaseController {
    
    public BuscarController(InventarioService service) {
        super(service);
    }

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

    // Exponemos seleccionarProducto si es necesario externamente (a través de InventarioController)
    public Producto ejecutarSeleccion() {
        return seleccionarProducto();
    }
}
