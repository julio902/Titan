package services;

import java.util.ArrayList;
import java.util.List;
import models.Producto;

public class InventarioService {

    private List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public List<Producto> listarProductos() {
        return productos;
    }

    public boolean eliminarProducto(String codigo) {
        Producto encontrado = null;
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                encontrado = p;
                break;
            }
        }
        if (encontrado != null) {
            productos.remove(encontrado);
            return true;
        }
        return false;
    }

    // todo: crear funciones para el CRUD con archivos 
}
