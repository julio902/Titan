package views;

import java.util.Scanner;
import controllers.UsuarioController;
import models.Usuario;

public class MenuLogin {
    private final Scanner scanner = new Scanner(System.in);
    private final UsuarioController usuarioController = new UsuarioController();

    public void mostrarLogin() {
        System.out.println("=== SISTEMA DE INVENTARIO ===");
        System.out.print("Usuario: ");
        String user = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        Usuario u = usuarioController.verificarUsuario(user, password);

        if (u != null) {
            System.out.println("\n✅ Bienvenido, " + u.getUser() + " (" + u.getRol() + ")\n");

            switch (u.getRol().toLowerCase()) {
                case "administrador" -> new MenuAdministrador().mostrarMenu(u);
                case "almacenista" -> new MenuAlmacenista().mostrarMenu(u);
                case "vendedor" -> new MenuVendedor().mostrarMenu(u);
                default -> System.out.println("Rol no reconocido.");
            }
        } else {
            System.out.println("❌ Credenciales incorrectas.");
        }
    }
}