package views;

import controllers.UsuarioController;
import java.util.Scanner;
import models.Usuario;

public class MenuAdministrador {
    private final Scanner scanner = new Scanner(System.in);
    private final UsuarioController usuarioController = new UsuarioController();

    public void mostrarMenu(Usuario admin) {
        int opcion;
        do {
            System.out.println("=== MENÚ ADMINISTRADOR ===");
            System.out.println("1. Registrar nuevo usuario");
            System.out.println("2. Listar usuarios");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> registrarUsuario();
                case 2 -> usuarioController.listarUsuarios();
                case 3 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 3);
             System.out.println("Regresando a la pantalla de inicio de sesión.");
             new MenuLogin().mostrarLogin();
    }

    private void registrarUsuario() {
    System.out.print("Nuevo usuario: ");
    String user = scanner.nextLine();

    System.out.print("Contraseña: ");
    String password = scanner.nextLine();

    System.out.println("\nSeleccione el rol del nuevo usuario:");
    System.out.println("1. Administrador");
    System.out.println("2. Almacenista");
    System.out.println("3. Vendedor");
    System.out.print("Opción: ");

    String opcion = scanner.nextLine();
    String rol = "";

        switch (opcion) {
            case "1" -> rol = "administrador";
            case "2" -> rol = "almacenista";
            case "3" -> rol = "vendedor";
            default -> {
                System.out.println("⚠️ Opción inválida. No se registró el usuario.");
                return;
            }
        }

        usuarioController.registrarUsuario(new Usuario(user, password, rol));
        System.out.println("✅ Usuario registrado correctamente con rol: " + rol);
    }

}
