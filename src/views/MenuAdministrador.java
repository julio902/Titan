package views;

import java.util.List;
import java.util.regex.Pattern;

import controllers.InventarioController;
import controllers.UsuarioController;
import controllers.VentaController;
import models.Administrador;
import models.Almacenista;
import models.Usuario;
import models.Vendedor;
import models.Venta;
import utils.InputUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class MenuAdministrador {

    private final UsuarioController usuarioController = new UsuarioController();
    private final InventarioController inventarioController;
    private final VentaController ventaController = new VentaController();

    // ======================================
    // CONSTRUCTOR
    // ======================================
    public MenuAdministrador(InventarioController inventarioController) {
        this.inventarioController = inventarioController;
    }

    // ======================================
    // MENÚ PRINCIPAL
    // ======================================
    public void mostrar() {

        int opcion = -1;

        do {

            System.out.println("\n=== MENÚ ADMINISTRADOR ===");
            System.out.println("[1] Registrar usuario");
            System.out.println("[2] Listar usuarios");
            System.out.println("[3] Eliminar usuario");
            System.out.println("[4] Gestión de inventario 🔥");
            System.out.println("[5] Resumen de ventas totales 📊");
            System.out.println("[0] Cerrar sesión");

            opcion = InputUtils.leerEntero("Seleccione: ");

            switch (opcion) {

                case 1 -> registrarUsuario();

                case 2 -> mostrarUsuarios();

                case 3 -> eliminarUsuario();

                case 4 -> new MenuInventarioAdmin(
                        inventarioController
                ).mostrar();

                case 5 -> mostrarResumenVentas();

                case 0 -> System.out.println("Cerrando sesión...");

                default -> System.out.println("❌ Opción no válida");
            }

        } while (opcion != 0);
    }

    // ======================================
    // MOSTRAR USUARIOS
    // ======================================
    private void mostrarUsuarios() {

        List<Usuario> lista = usuarioController.obtenerUsuarios();

        if (lista.isEmpty()) {

            System.out.println("❌ No hay usuarios registrados.");
            return;
        }

        System.out.println("\n=== LISTA DE USUARIOS ===");

        System.out.printf(
                "%-20s %-15s\n",
                "Usuario",
                "Rol"
        );

        System.out.println("---------------------------------------");

        for (Usuario u : lista) {

            System.out.printf(
                    "%-20s %-15s\n",
                    u.getUser(),
                    u.getRol()
            );
        }
    }

    // ======================================
    // ELIMINAR USUARIO
    // ======================================
    private void eliminarUsuario() {

        List<Usuario> lista = usuarioController.obtenerUsuarios();

        if (lista.isEmpty()) {

            System.out.println("❌ No hay usuarios registrados.");
            return;
        }

        System.out.println("\n=== ELIMINAR USUARIO ===");

        for (int i = 0; i < lista.size(); i++) {

            Usuario u = lista.get(i);

            System.out.println(
                    "[" + (i + 1) + "] "
                    + u.getUser()
                    + " - "
                    + u.getRol()
            );
        }

        int opcion = InputUtils.leerEntero(
                "Seleccione usuario a eliminar: "
        );

        if (opcion < 1 || opcion > lista.size()) {

            System.out.println("❌ Opción inválida.");
            return;
        }

        Usuario usuarioSeleccionado = lista.get(opcion - 1);

        boolean eliminado = usuarioController.eliminarUsuario(
                usuarioSeleccionado.getUser()
        );

        if (eliminado) {

            System.out.println("✅ Usuario eliminado correctamente.");

        } else {

            System.out.println("❌ No se pudo eliminar el usuario.");
        }
    }

    // ======================================
    // VALIDAR CONTRASEÑA
    // ======================================
    public static boolean validarContrasena(String contrasena) {

        String regex =
                "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)"
                + "(?=.*[@$!%*?&._-]).{8,}$";

        return Pattern.matches(regex, contrasena);
    }

    // ======================================
    // REGISTRAR USUARIO
    // ======================================
    private void registrarUsuario() {

        System.out.println("\n=== NUEVO USUARIO ===");

        String user = InputUtils.leerTexto("Usuario: ").trim();

        if (user.isEmpty()) {

            System.out.println(
                    "❌ El nombre de usuario no puede estar vacío."
            );

            return;
        }

        String correo =
                user.toLowerCase()
                + "@confectexctg.com";

        String password;

        do {

            password = InputUtils.leerTexto("Contraseña: ");

            if (!validarContrasena(password)) {

                System.out.println("""
                        ❌ Contraseña inválida:
                        - Minúscula
                        - Mayúscula
                        - Número
                        - Símbolo (@$!%*?&._-)
                        - Mínimo 8 caracteres
                        """);
            }

        } while (!validarContrasena(password));

        System.out.println(
                "Rol: [1] Admin [2] Almacenista [3] Vendedor"
        );

        int opcion = InputUtils.leerEntero("Seleccione: ");

        Usuario nuevo = null;

        switch (opcion) {

            case 1 ->
                nuevo = new Administrador(
                        user,
                        correo,
                        password
                );

            case 2 ->
                nuevo = new Almacenista(
                        user,
                        correo,
                        password
                );

            case 3 ->
                nuevo = new Vendedor(
                        user,
                        correo,
                        password
                );

            default -> {

                System.out.println("❌ Rol inválido.");
                return;
            }
        }

        if (usuarioController.registrarUsuario(nuevo)) {

            System.out.println(
                    "✅ Usuario creado correctamente."
            );

            System.out.println(
                    "Correo generado: " + correo
            );

        } else {

            System.out.println(
                    "❌ El usuario o correo ya existe."
            );
        }
    }

    // ======================================
    // MOSTRAR RESUMEN DE VENTAS TOTALES
    // ======================================
    private void mostrarResumenVentas() {
        List<Venta> todasLasVentas = ventaController.obtenerVentas();

        if (todasLasVentas.isEmpty()) {
            System.out.println("\n❌ No hay ventas registradas en el sistema.");
            return;
        }

        // Agrupar ventas por número de factura
        Map<String, List<Venta>> ventasAgrupadas = new LinkedHashMap<>();
        for (Venta v : todasLasVentas) {
            ventasAgrupadas
                .computeIfAbsent(v.getNumeroVenta(), k -> new ArrayList<>())
                .add(v);
        }

        System.out.println("\n========================================================");
        System.out.println("            RESUMEN GLOBAL DE VENTAS (ADMIN)            ");
        System.out.println("========================================================");

        double granTotalSistema = 0;

        for (Map.Entry<String, List<Venta>> entry : ventasAgrupadas.entrySet()) {
            String numFactura = entry.getKey();
            List<Venta> items = entry.getValue();
            Venta primera = items.get(0);

            System.out.println("\nFACTURA #: " + numFactura + " | Vendedor: " + primera.getVendedor());
            System.out.println("Cliente  : " + primera.getNombreCliente() + " (" + primera.getCedulaCliente() + ")");
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
            granTotalSistema += totalFactura;
        }

        System.out.println("\n>>> TOTAL RECAUDADO EN EL SISTEMA: $" + granTotalSistema + " <<<");
    }
}