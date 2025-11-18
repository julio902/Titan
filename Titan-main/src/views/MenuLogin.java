package views;

import controllers.UsuarioController;
import java.util.Scanner;
import models.Usuario;

public class MenuLogin {

    private final Scanner scanner = new Scanner(System.in);
    private final UsuarioController usuarioController = new UsuarioController();

    public void mostrarLogin() {

        

        while (true) { 
            
            System.out.println("\n=== SISTEMA DE INVENTARIO CONFECTEX CARTAGENA ===");
            System.out.println("************** Inicio de Sesion *****************");
            System.out.println("Seleccione su Perfil de Usuario.");
            System.out.println("\t[1] Administrador                                        ||");
            System.out.println("\t[2] Vendedor                                             ||");
            System.out.println("\t[3] Almacenista                                          ||");
            System.out.println("\t[4] Salir                                                ||");
            System.out.println("==============================================================||");

            System.out.print("Opción: ");
            String opcion = scanner.nextLine();

            String rol = "";

            switch (opcion) {
                case "1" -> rol = "administrador";
                case "2" -> rol = "vendedor";
                case "3" -> rol = "almacenista";
                case "4" -> {
                    System.out.println("Saliendo del sistema...");
                    return;
                }
                default -> {
                    System.out.println("Opción inválida.\n");
                    continue;
                }
            }
        
            int intentos = 0;
            while (intentos < 3) {

                System.out.println("\n********************************");

                System.out.print("\tUsuario: ");
                String user = scanner.nextLine();

                System.out.print("\tContraseña: ");
                String pass = scanner.nextLine(); 
                
                System.out.println("********************************");

                // Validacion del usuario ingresado
                Usuario u = usuarioController.validarCredenciales(user, pass, rol);

                if (u != null) {

                    System.out.println("\n  Bienvenido " + u.getUser() + "\n");

                    switch (rol) {
                        case "administrador" -> new MenuAdministrador().mostrar();
                        case "vendedor"      -> new MenuVendedor().mostrarMenu(u);
                        case "almacenista"    -> new MenuAlmacenista().mostrarMenu(u);
                    }

                    break; // Regresar al Menu anterio
                } else {
                    intentos++;
                    System.out.println("\n Credenciales incorrectas.");
                    System.out.println("Intento " + intentos + " de 3\n");
                }
            }
        
            // si llega aquí = falló 3 veces
            System.out.println("Ha excedido el número de intentos.");        
            
        }   
    }
}
