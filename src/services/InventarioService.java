package services;

import java.util.ArrayList;
import java.util.List;
import models.Producto;
import utils.FileUtils;

public class InventarioService {

    private List<Producto> productos;
    private int contadorCodigo = 0;

    public InventarioService() {
        // Cargar productos desde archivo
        productos = FileUtils.cargarProductos();

        // Ajustar contador según el último producto guardado
        actualizarContadorCodigo();
    }

    // ==============================================
    // GENERAR CÓDIGO AUTOMÁTICO
    // ==============================================
    private void actualizarContadorCodigo() {
        for (Producto p : productos) {
            try {
                // PRD-005 → 5
                String numStr = p.getCodigo().substring(4);
                int num = Integer.parseInt(numStr);

                if (num > contadorCodigo) {
                    contadorCodigo = num;  
                }
            } catch (NumberFormatException e) {
                // Ignorar por si el código no tiene formato válido
            }
        }
    }

    public String generarCodigo() {
        contadorCodigo++;
        return String.format("PRD-%03d", contadorCodigo);
    }

    // ==============================================
    // AGREGAR PRODUCTO
    // ==============================================
    public void agregarProducto(Producto p) {
        productos.add(p);
        FileUtils.guardarProductos(productos);
    }

    // ==============================================
    // LISTAR PRODUCTOS
    // ==============================================
    public List<Producto> listarProductos() {
        return productos;
    }

    // ==============================================
    // ELIMINAR PRODUCTO
    // ==============================================
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
            FileUtils.guardarProductos(productos);
            return true;
        }

        return false;
    }

    // ==============================================
    // BUSCAR POR CÓDIGO
    // ==============================================
    public Producto buscarProductoPorCodigo(String codigo) {
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    // ==============================================
    // 🔍 NUEVO: BUSCAR POR COINCIDENCIA (NOMBRE, DESC O CÓDIGO)
    // ==============================================
    public List<Producto> buscarCoincidencias(String busqueda) {
        busqueda = busqueda.toLowerCase();

        List<Producto> resultados = new ArrayList<>();

        for (Producto p : productos) {
            if (p.getCodigo().toLowerCase().contains(busqueda) ||
                p.getNombre().toLowerCase().contains(busqueda) ||
                p.getdescripcion().toLowerCase().contains(busqueda)) {

                resultados.add(p);
            }
        }

        return resultados;
    }

    // ==============================================
    // MODIFICAR PRODUCTO
    // ==============================================
    public boolean modificarProducto(String codigo, String nuevoNombre, 
                                     String nuevaDescripcion, int nuevaCantidad, 
                                     double nuevoPrecio) {

        for (int i = 0; i < productos.size(); i++) {

            Producto p = productos.get(i);

            if (p.getCodigo().equalsIgnoreCase(codigo)) {

                // Nuevo objeto actualizado
                Producto actualizado = new Producto(
                    codigo,
                    nuevoNombre,
                    nuevaDescripcion,
                    nuevaCantidad,
                    nuevoPrecio
                );

                productos.set(i, actualizado);
                FileUtils.guardarProductos(productos);
                return true;
            }
        }

        return false;
    }
}
