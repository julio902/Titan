package views;

import java.util.Scanner;

import controllers.UsuarioController;
import controllers.InventarioController;
import services.InventarioService;
import models.Usuario;
import models.Administrador;
import models.Vendedor;
import models.Almacenista;

public class MenuLogin {

    private final Scanner scanner = new Scanner(System.in);
    private final UsuarioController usuarioController = new UsuarioController();

    // 🔥 UNA SOLA INSTANCIA (CLAVE)
    private final InventarioService inventarioService = new InventarioService();
    private final InventarioController inventarioController = new InventarioController(inventarioService);

    public void mostrarLogin() {

        while (true) {

            System.out.println("\n=== SISTEMA DE INVENTARIO CONFECTEX CARTAGENA ===");
            System.out.println("[1] Administrador");
            System.out.println("[2] Vendedor");
            System.out.println("[3] Almacenista");
            System.out.println("[4] Salir");

            System.out.print("Opción: ");
            String opcion = scanner.nextLine();

            String rol = "";

            switch (opcion) {
                case "1" -> rol = "administrador";
                case "2" -> rol = "vendedor";
                case "3" -> rol = "almacenista";
                case "4" -> {
                    System.out.println("Saliendo...");
                    return;
                }
                default -> {
                    System.out.println("Opción inválida.\n");
                    continue;
                }
            }

            int intentos = 0;

            while (intentos < 3) {

                System.out.print("Usuario: ");
                String user = scanner.nextLine();

                System.out.print("Contraseña: ");
                String pass = scanner.nextLine();

                Usuario u = usuarioController.validarCredenciales(user, pass, rol);

                if (u != null) {

                    System.out.println("\nBienvenido " + u.getUser());

                    // 🔥 AQUÍ ESTÁ LA CLAVE
                    if (u instanceof Administrador) {
                        new MenuAdministrador(inventarioController).mostrar();
                    } 
                    else if (u instanceof Vendedor) {
                        new MenuVendedor(inventarioController).mostrarMenu(u);
                    } 
                    else if (u instanceof Almacenista) {
                        new MenuAlmacenista(inventarioController).mostrarMenu();
                    }

                    break;

                } else {
                    intentos++;
                    System.out.println("Credenciales incorrectas (" + intentos + "/3)");
                }
            }

            System.out.println("Demasiados intentos fallidos.");
        }
    }
}