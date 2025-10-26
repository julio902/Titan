package services;

import java.util.List;
import models.Producto;
import utils.FileUtils;

public class InventarioService {
    private List<Producto> productos;

    public InventarioService() {
        // Cargar productos desde el archivo al iniciar
        productos = FileUtils.cargarProductos();
    }

    public void agregarProducto(Producto p) {
        productos.add(p);
        FileUtils.guardarProductos(productos); //  Guardar al agregar
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
            FileUtils.guardarProductos(productos); //  Guardar al eliminar
            return true;
        }
        return false;
    }
    // Nueva función para buscar producto por código
    public Producto buscarProductoPorCodigo(String codigo) {
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

}

