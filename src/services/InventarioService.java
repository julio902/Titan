package services;

import java.util.ArrayList;
import java.util.List;

import models.Producto;
import utils.FileUtils;

public class InventarioService {

    private List<Producto> productos;
    private int contadorCodigo = 0;

    // ==============================================
    // CONSTRUCTOR
    // ==============================================
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
    // AGREGAR PRODUCTO (VALIDADO)
    // ==============================================
    public boolean agregarProducto(Producto p) {

        if (buscarProductoPorCodigo(p.getCodigo()) != null) {
            System.out.println("El producto ya existe");
            return false;
        }

        productos.add(p);
        FileUtils.guardarProductos(productos);
        return true;
    }

    // ==============================================
    // LISTAR PRODUCTOS (PROTEGIDO)
    // ==============================================
    public List<Producto> listarProductos() {
        return new ArrayList<>(productos);
    }

    // ==============================================
    // ELIMINAR PRODUCTO
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
    // BUSCAR POR COINCIDENCIAS
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
    // MODIFICAR PRODUCTO (USANDO POO)
    // ==============================================
    public boolean modificarProducto(String codigo, String nuevoNombre, String nuevaDescripcion, int nuevaCantidad, double nuevoValorCompra, double nuevoValorVenta) {

        Producto p = buscarProductoPorCodigo(codigo);

        if (p != null) {

            p.setNombre(nuevoNombre);
            p.setDescripcion(nuevaDescripcion);

            // Ajuste inteligente de stock
            int diferencia = nuevaCantidad - p.getCantidad();

            if (diferencia > 0) {
                p.reponer(diferencia);
            } else if (diferencia < 0) {
                p.vender(-diferencia);
            }

            p.setValorCompra(nuevoValorCompra);
            p.setValorVenta(nuevoValorVenta);

            FileUtils.guardarProductos(productos);
            return true;
        }

        return false;
    }

    // ==============================================
    // VENDER PRODUCTO (POO)
    // ==============================================
    public boolean venderProducto(String codigo, int cantidad) {
        Producto p = buscarProductoPorCodigo(codigo);

        if (p != null) {
            try {
                p.vender(cantidad);
                FileUtils.guardarProductos(productos);
                return true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return false;
    }

    // ==============================================
    // REPONER PRODUCTO (POO)
    // ==============================================
    public boolean reponerProducto(String codigo, int cantidad) {
        Producto p = buscarProductoPorCodigo(codigo);

        if (p != null) {
            try {
                p.reponer(cantidad);
                FileUtils.guardarProductos(productos);
                return true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return false;
    }

    // ==============================================
    // CALCULAR VALOR TOTAL DEL INVENTARIO
    // ==============================================
    public double calcularValorTotalInventario() {
        double total = 0;

        for (Producto p : productos) {
            total += p.calcularValorInventario();
        }

        return total;
    }
}