package controllers;

import java.util.List;
import models.Producto;
import services.InventarioService;
import controllers.inventario.*;

/**
 * InventarioController (Facade)
 * Redirige las peticiones a controladores especializados en el paquete controllers.inventario
 */
public class InventarioController {

    private final AgregarController agregarCtrl;
    private final ListarController listarCtrl;
    private final BuscarController buscarCtrl;
    private final ModificarController modificarCtrl;
    private final EliminarController eliminarCtrl;
    private final StockController stockCtrl;
    
    public InventarioController(InventarioService service) {
        this.agregarCtrl = new AgregarController(service);
        this.listarCtrl = new ListarController(service);
        this.buscarCtrl = new BuscarController(service);
        this.modificarCtrl = new ModificarController(service);
        this.eliminarCtrl = new EliminarController(service);
        this.stockCtrl = new StockController(service);
    }

    public void agregarProducto() {
        agregarCtrl.agregarProducto();
    }

    public void listarProductos() {
        listarCtrl.listarProductos();
    }

    public void buscarProducto() {
        buscarCtrl.buscarProducto();
    }

    public void modificarProducto() {
        modificarCtrl.modificarProducto();
    }

    public void eliminarProducto() {
        eliminarCtrl.eliminarProducto();
    }

    public void venderProducto() {
        stockCtrl.venderProducto();
    }

    public boolean venderProducto(String codigo, int cantidad) {
        return stockCtrl.venderProducto(codigo, cantidad);
    }

    public void reponerProducto() {
        stockCtrl.reponerProducto();
    }

    public double calcularValorTotalInventario() {
        return listarCtrl.calcularValorTotalInventario();
    }

    public List<Producto> obtenerProductos() {
        return listarCtrl.obtenerProductos();
    }
}
