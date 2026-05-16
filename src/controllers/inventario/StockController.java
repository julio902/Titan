package controllers.inventario;

import models.Producto;
import services.InventarioService;
import utils.InputUtils;

public class StockController extends InventarioBaseController {
    
    public StockController(InventarioService service) {
        super(service);
    }

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

    public boolean venderProducto(String codigo, int cantidad) {
        return service.venderProducto(codigo, cantidad);
    }

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
}
