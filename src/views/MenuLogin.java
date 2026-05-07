package views;

import controllers.UsuarioController;
import controllers.InventarioController;
import services.InventarioService;
import models.Usuario;
import models.Administrador;
import models.Vendedor;
import models.Almacenista;
import utils.InputUtils;

public class MenuLogin {

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

            String opcion = InputUtils.leerTexto("Opción: ");

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
                    System.out.println("❌ Opción inválida.\n");
                    continue;
                }
            }

            int intentos = 0;
            boolean loginExitoso = false;

            while (intentos < 3) {

                String user = InputUtils.leerTexto("Usuario (Correo): ");
                String pass = InputUtils.leerTexto("Contraseña: ");

                Usuario u = usuarioController.validarCredenciales(user, pass, rol);

                if (u != null) {

                    System.out.println("\n✅ Bienvenido " + u.getUser());
                    loginExitoso = true;

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
                    System.out.println("❌ Credenciales incorrectas (" + intentos + "/3)");
                }
            }

            if (!loginExitoso) {
                System.out.println("⚠️ Demasiados intentos fallidos para este rol.");
            }
        }
    }
}