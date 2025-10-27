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

    public void agregarProducto(Producto p) { // agregar producto******
        productos.add(p);
        FileUtils.guardarProductos(productos); //  Guardar al agregar
    }

    public List<Producto> listarProductos() {
        return productos;
    }

    public boolean eliminarProducto(String codigo) { // eliminar producto *****
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
    
    public Producto buscarProductoPorCodigo(String codigo) {  // Nueva función para buscar producto por código
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }
    // método para modificar producto***********
    public boolean modificarProducto(String codigo, String nuevoNombre, int nuevaCantidad, double nuevoPrecio) {
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                // Creamos un nuevo objeto actualizado
                Producto actualizado = new Producto(codigo, nuevoNombre, nuevaCantidad, nuevoPrecio);
                productos.set(i, actualizado); // reemplazamos el anterior
                FileUtils.guardarProductos(productos); // guardamos cambios
                return true;
            }
        }
        return false;
    }

}

