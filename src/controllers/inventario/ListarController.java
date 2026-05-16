package controllers.inventario;

import java.util.List;
import models.Producto;
import services.InventarioService;

public class ListarController extends InventarioBaseController {
    
    public ListarController(InventarioService service) {
        super(service);
    }

    public void listarProductos() {
        List<Producto> lista = service.listarProductos();
        System.out.println("\n=== LISTA DE PRODUCTOS ===");

        if (lista.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        for (Producto p : lista) {
            System.out.println(p);
        }

        System.out.println("----------------------------------");
        System.out.println("Valor total inventario: $" + service.calcularValorTotalInventario());
    }

    public double calcularValorTotalInventario() {
        return service.calcularValorTotalInventario();
    }

    public List<Producto> obtenerProductos() {
        return service.listarProductos();
    }
}
