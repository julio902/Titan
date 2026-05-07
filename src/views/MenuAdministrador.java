package views;

import java.util.List;
import java.util.regex.Pattern;

import controllers.UsuarioController;
import controllers.InventarioController;
import models.Administrador;
import models.Almacenista;
import models.Usuario;
import models.Vendedor;
import utils.InputUtils;

public class MenuAdministrador {

    private final UsuarioController usuarioController = new UsuarioController();
    private final InventarioController inventarioController;

    // 🔥 IMPORTANTE: RECIBIR CONTROLLER (NO CREARLO)
    public MenuAdministrador(InventarioController inventarioController) {
        this.inventarioController = inventarioController;
    }

    public void mostrar() {

        int opcion = -1;

        do {
            System.out.println("\n=== MENÚ ADMINISTRADOR ===");
            System.out.println("[1] Registrar usuario");
            System.out.println("[2] Listar usuarios");
            System.out.println("[3] Gestión de inventario 🔥");
            System.out.println("[0] Cerrar sesión");
            
            opcion = InputUtils.leerEntero("Seleccione: ");

            switch (opcion) {
                case 1 -> registrarUsuario();
                case 2 -> mostrarUsuarios();
                case 3 -> new MenuInventarioAdmin(inventarioController).mostrar(); // 🔥 SUBMENÚ
                case 0 -> System.out.println("Cerrando sesión...");
                default -> System.out.println("❌ Opción no válida");
            }

        } while (opcion != 0);
    }

    // ================================
    // MOSTRAR USUARIOS
    // ================================
    private void mostrarUsuarios() {

        List<Usuario> lista = usuarioController.obtenerUsuarios();

        if (lista.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }

        System.out.println("\n=== LISTA DE USUARIOS ===");
        System.out.printf("%-20s %-15s\n", "Usuario", "Rol");
        System.out.println("---------------------------------------");

        for (Usuario u : lista) {
            System.out.printf("%-20s %-15s\n", u.getUser(), u.getRol());
        }
    }

    // ================================
    // VALIDAR CONTRASEÑA
    // ================================
    public static boolean validarContrasena(String contrasena) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._-]).{8,}$";
        return Pattern.matches(regex, contrasena);
    }

    // ================================
    // REGISTRAR USUARIO
    // ================================
    private void registrarUsuario() {

        System.out.println("\n=== NUEVO USUARIO ===");

        String user = InputUtils.leerTexto("Usuario: ");
        if (user.isEmpty()) {
            System.out.println("❌ El nombre de usuario no puede estar vacío.");
            return;
        }

        String correo = user.trim().replace(" ", "").toLowerCase() + "@confectexctg.com";

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

        System.out.println("Rol: [1] Admin [2] Almacenista [3] Vendedor");
        String opcion = InputUtils.leerTexto("Seleccione: ");

        Usuario nuevo = null;

        switch (opcion) {
            case "1" -> nuevo = new Administrador(user, correo, password);
            case "2" -> nuevo = new Almacenista(user, correo, password);
            case "3" -> nuevo = new Vendedor(user, correo, password);
            default -> {
                System.out.println("❌ Rol inválido.");
                return;
            }
        }

        if (usuarioController.registrarUsuario(nuevo)) {
            System.out.println("✅ Usuario creado correctamente. Correo: " + correo);
        } else {
            System.out.println("❌ El usuario o correo ya existe.");
        }
    }
}