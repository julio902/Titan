package services;

import java.util.ArrayList;
import java.util.List;
import models.Producto;
import utils.FileUtils;

public class InventarioService {

    private List<Producto> productos;
    private int contadorCodigo = 0;

    public InventarioService() {
        productos = FileUtils.cargarProductos();
        actualizarContadorCodigo();
    }

    // ==============================================
    // GENERAR CÓDIGO AUTOMÁTICO
    // ==============================================
    private void actualizarContadorCodigo() {
        for (Producto p : productos) {
            String codigo = p.getCodigo();

            if (codigo != null && codigo.startsWith("PRD-")) {
                try {
                    int num = Integer.parseInt(codigo.substring(4));

                    if (num > contadorCodigo) {
                        contadorCodigo = num;
                    }
                } catch (NumberFormatException e) {
                    // Ignorar códigos inválidos
                }
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
    // ELIMINAR PRODUCTO (MEJORADO)
    // ==============================================
    public boolean eliminarProducto(String codigo) {
        boolean eliminado = productos.removeIf(p -> 
            p.getCodigo().equalsIgnoreCase(codigo)
        );

        if (eliminado) {
            FileUtils.guardarProductos(productos);
        }

        return eliminado;
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
    // BUSCAR POR COINCIDENCIAS (MEJORADO)
    // ==============================================
    public List<Producto> buscarCoincidencias(String busqueda) {
        busqueda = busqueda.toLowerCase();
        List<Producto> resultados = new ArrayList<>();

        for (Producto p : productos) {

            String codigo = p.getCodigo() != null ? p.getCodigo().toLowerCase() : "";
            String nombre = p.getNombre() != null ? p.getNombre().toLowerCase() : "";
            String desc = p.getDescripcion() != null ? p.getDescripcion().toLowerCase() : "";

            if (codigo.contains(busqueda) || 
                nombre.contains(busqueda) || 
                desc.contains(busqueda)) {

                resultados.add(p);
            }
        }

        return resultados;
    }

    // ==============================================
    // MODIFICAR PRODUCTO (MEJORADO)
    // ==============================================
    public boolean modificarProducto(String codigo, String nuevoNombre, 
                                     String nuevaDescripcion, int nuevaCantidad, 
                                     double nuevoPrecio) {

        for (Producto p : productos) {

            if (p.getCodigo().equalsIgnoreCase(codigo)) {

                p.setNombre(nuevoNombre);
                p.setDescripcion(nuevaDescripcion);
                p.setCantidad(nuevaCantidad);
                p.setPrecio(nuevoPrecio);

                FileUtils.guardarProductos(productos);
                return true;
            }
        }

        return false;
    }
}