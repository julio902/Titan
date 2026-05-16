package views;

import controllers.InventarioController;
import controllers.VentaController;

import models.Producto;
import models.Usuario;
import models.Venta;

import utils.InputUtils;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class MenuVendedor {

    private final InventarioController inventarioController;
    private final VentaController ventaController;

    // Constructor

    public MenuVendedor(
            InventarioController inventarioController
    ) {

        this.inventarioController =
                inventarioController;
        this.ventaController = 
                new VentaController();
    }

    // MENÚ PRINCIPAL

    public void mostrarMenu(Usuario vendedor) {

        int opcion = -1;

        do {

            System.out.println(
                    "\n=== MENÚ VENDEDOR ==="
            );

            System.out.println(
                    "[1] Ver productos"
            );

            System.out.println(
                    "[2] Buscar producto"
            );

            System.out.println(
                    "[3] Vender producto 🧾"
            );

            System.out.println(
                    "[4] Ver mis ventas 📄"
            );

            System.out.println(
                    "[0] Cerrar sesión"
            );

            opcion =
                    InputUtils.leerEntero(
                            "Opción: "
                    );

            switch (opcion) {

                case 1 ->

                    inventarioController
                            .listarProductos();

                case 2 ->

                    inventarioController
                            .buscarProducto();

                case 3 ->

                    venderProducto(vendedor);

                case 4 ->

                    verMisVentas(vendedor);

                case 0 ->

                    System.out.println(
                            "Cerrando sesión..."
                    );

                default ->

                    System.out.println(
                            "❌ Opción inválida."
                    );
            }

        } while (opcion != 0);

        System.out.println(
                "Regresando al inicio..."
        );
    }

    // =========================================
    // VENDER PRODUCTO
    // =========================================

    public void venderProducto(Usuario vendedor) {
        System.out.println("\n========== REALIZAR VENTA ==========");

        // 1. DATOS DEL CLIENTE (UNA SOLA VEZ)
        String cedulaCliente = InputUtils.leerTexto("Cédula cliente: ");
        String nombreCliente = InputUtils.leerTexto("Nombre cliente: ");
        String telefonoCliente = InputUtils.leerTexto("Teléfono cliente: ");

        List<Venta> carrito = new java.util.ArrayList<>();
        String continuar;

        // 2. BUCLE DE SELECCIÓN DE PRODUCTOS
        do {
            List<Producto> productos = inventarioController.obtenerProductos();

            if (productos.isEmpty()) {
                System.out.println("❌ No hay productos disponibles.");
                break;
            }

            System.out.println("\n=== PRODUCTOS DISPONIBLES ===");
            for (int i = 0; i < productos.size(); i++) {
                Producto p = productos.get(i);
                System.out.println("[" + (i + 1) + "] " + p.getNombre() + 
                    " | Stock: " + p.getCantidad() + " | Precio: $" + p.getValorVenta());
            }

            int opcionProducto = InputUtils.leerEntero("\nSeleccione producto: ");
            if (opcionProducto < 1 || opcionProducto > productos.size()) {
                System.out.println("❌ Opción inválida.");
            } else {
                Producto producto = productos.get(opcionProducto - 1);
                int cantidad = InputUtils.leerEntero("Cantidad a vender: ");

                // Validar stock (considerando lo que ya hay en el carrito)
                int cantidadEnCarrito = 0;
                for (Venta v : carrito) {
                    if (v.getProducto().equals(producto.getNombre())) {
                        cantidadEnCarrito += v.getCantidad();
                    }
                }

                if ((cantidad + cantidadEnCarrito) > producto.getCantidad()) {
                    System.out.println("❌ Stock insuficiente.");
                } else {
                    double subtotal = cantidad * producto.getValorVenta();
                    // Agregamos al carrito (el número de venta se asignará al final)
                    carrito.add(new Venta(
                        "", // Temporal
                        vendedor.getUser(),
                        cedulaCliente,
                        nombreCliente,
                        telefonoCliente,
                        producto.getNombre(),
                        cantidad,
                        subtotal
                    ));
                    System.out.println("✅ Producto agregado al carrito.");
                }
            }

            continuar = InputUtils.leerTexto("¿Desea agregar otro producto? (S/N): ").toLowerCase();
        } while (continuar.equals("s"));

        // 3. PROCESAR VENTA SI EL CARRITO NO ESTÁ VACÍO
        if (carrito.isEmpty()) {
            System.out.println("Venta cancelada (carrito vacío).");
            return;
        }

        // Generar un único número de venta para toda la transacción
        String numeroVenta = ventaController.generarNumeroVenta();
        double granTotal = 0;

        System.out.println("\n========== FACTURA FINAL ==========");
        System.out.println("Venta #: " + numeroVenta);
        System.out.println("Cliente: " + nombreCliente + " (" + cedulaCliente + ")");
        System.out.println("------------------------------------");

        for (Venta item : carrito) {
            // Actualizar el número de venta real
            Venta ventaFinal = new Venta(
                numeroVenta,
                item.getVendedor(),
                item.getCedulaCliente(),
                item.getNombreCliente(),
                item.getTelefonoCliente(),
                item.getProducto(),
                item.getCantidad(),
                item.getTotal()
            );

            // Buscar el producto para obtener su código (necesario para el controlador)
            Producto p = null;
            for (Producto prod : inventarioController.obtenerProductos()) {
                if (prod.getNombre().equals(item.getProducto())) {
                    p = prod;
                    break;
                }
            }

            if (p != null && inventarioController.venderProducto(p.getCodigo(), item.getCantidad())) {
                ventaController.guardarVenta(ventaFinal);
                System.out.println("- " + item.getProducto() + " x" + item.getCantidad() + " : $" + item.getTotal());
                granTotal += item.getTotal();
            } else {
                System.out.println("❌ Error procesando ítem: " + item.getProducto());
            }
        }

        System.out.println("------------------------------------");
        System.out.println("TOTAL A PAGAR: $" + granTotal);
        System.out.println("✅ Venta registrada correctamente.");
    }

    // =========================================
    // VER MIS VENTAS
    // =========================================

    public void verMisVentas(Usuario vendedor) {
        List<Venta> todasLasVentas = ventaController.obtenerVentas();
        
        // 1. Agrupar ventas por número de factura (solo las del vendedor actual)
        Map<String, List<Venta>> ventasAgrupadas = new LinkedHashMap<>();

        for (Venta v : todasLasVentas) {
            if (v.getVendedor().equals(vendedor.getUser())) {
                ventasAgrupadas
                    .computeIfAbsent(v.getNumeroVenta(), k -> new ArrayList<>())
                    .add(v);
            }
        }

        if (ventasAgrupadas.isEmpty()) {
            System.out.println("\n❌ No tienes ventas registradas.");
            return;
        }

        System.out.println("\n======================================================");
        System.out.println("                 HISTORIAL DE VENTAS                          ");
        System.out.println("========================================================");

        for (Map.Entry<String, List<Venta>> entry : ventasAgrupadas.entrySet()) {
            String numFactura = entry.getKey();
            List<Venta> items = entry.getValue();
            Venta primera = items.get(0); // Para datos del cliente

            System.out.println("\nFACTURA #: " + numFactura);
            System.out.println("Cliente  : " + primera.getNombreCliente());
            System.out.println("Cédula   : " + primera.getCedulaCliente());
            System.out.println("Teléfono : " + primera.getTelefonoCliente());
            System.out.println("--------------------------------------------------------");
            System.out.printf("%-25s %-10s %-10s\n", "Producto", "Cant.", "Subtotal");
            System.out.println("--------------------------------------------------------");

            double totalFactura = 0;
            for (Venta item : items) {
                System.out.printf("%-25s %-10d $%-10.2f\n", 
                    item.getProducto(), 
                    item.getCantidad(), 
                    item.getTotal());
                totalFactura += item.getTotal();
            }

            System.out.println("--------------------------------------------------------");
            System.out.printf("%-36s $%-10.2f\n", "TOTAL FACTURA:", totalFactura);
            System.out.println("========================================================");
        }
    }
}