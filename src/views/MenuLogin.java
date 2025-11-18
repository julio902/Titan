package views;

import controllers.UsuarioController;
import java.util.Scanner;

public class MenuLogin {
    private final Scanner scanner = new Scanner(System.in);
    private final UsuarioController usuarioController = new UsuarioController();

    public void mostrarLogin() {
        System.out.println("=== SISTEMA DE INVENTARIO CONFECTEX CARTAGENA ===");
        System.out.println("************** Inicio de Sesion *****************");
        System.out.println("Seleccione su Perfil de Usuario.");
        System.out.println("\t[1] Administrador                                        ||");
        System.out.println("\t[2] Vendedor                                             ||");
        System.out.println("\t[3] Producción                                           ||");
        System.out.println("\t[4] Salir                                                ||");
        System.out.println("===========================================================||");
    
        String opcion = scanner.nextLine().trim();

        switch (opcion) {
            case 1 -> MenuAdministrador();

            
            default -> throw new AssertionError();
        }
        
        }
}
