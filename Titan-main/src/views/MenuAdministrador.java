package views;

import controllers.UsuarioController;
import java.util.List;
import java.util.Scanner;
import models.Usuario;
import utils.UsuarioValidators;

public class MenuAdministrador {
    private final Scanner scanner = new Scanner(System.in);
    private final UsuarioController usuarioController = new UsuarioController();

    // Este es el método que llamará MenuLogin
    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n=== === === MENÚ ADMINISTRADOR === === ===");
            System.out.println("\t[1] Registrar nuevo usuario");
            System.out.println("\t[2] Listar usuarios");
            System.out.println("\t[3] Regresar al Menu Anterior");
            System.out.println("=============================================");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> registrarUsuario();
                case 2 -> mostrarUsuarios();
                case 3 -> {
                    System.out.println("Volviendo al Menu Anterior...");
                    return;
                }
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 3);
             System.out.println("Regresando a la pantalla de inicio de sesión.");
             new MenuLogin().mostrarLogin();
    }

    // Lista de usuarios registrados en el sistema 
    private void mostrarUsuarios() {
        List<Usuario> lista = usuarioController.obtenerUsuarios();

        if (lista.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }

        System.out.println("\n=== LISTA DE USUARIOS ===");
        for (Usuario u : lista) {
            System.out.println("- " + u.getUser() + "\t| Rol: \t" + u.getRol());
        }
    }
    // registro de usuarios 
    private void registrarUsuario() {

        System.out.print("Nuevo usuario: ");
        String user = scanner.nextLine();

        String userNormalizado = user.trim().replace(" ", "").toLowerCase();
        String dominio = "@confectexctg.com";
        String correoUsuario = userNormalizado+dominio;

        String password = UsuarioValidators.generarPasswordAleatoria();

        System.out.println("\nLa contraseña generada para el usuario es: " + password);

        System.out.println("\nSeleccione el rol del nuevo usuario:");
        System.out.println("\t[1] Administrador");
        System.out.println("\t[2] Almacenista");
        System.out.println("\t[3] Vendedor");
        System.out.print("Opción: ");

        String opcion = scanner.nextLine();
        String rol = "";

        

        // Eleccion de roles 
        switch (opcion) {
            case "1" -> rol = "administrador";
            case "2" -> rol = "almacenista";
            case "3" -> rol = "vendedor";
            default -> {
                System.out.println(" Opción inválida. No se registró el usuario.");
                return;
            }
        }

        usuarioController.registrarUsuario(new Usuario(correoUsuario, password, rol));
        System.out.println("Usuario registrado correctamente con rol: " + rol);
    }
}
