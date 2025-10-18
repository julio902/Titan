package views;

import java.util.Scanner;

import controllers.UsuarioController;

public class MenuUsuario {
    private Scanner scanner = new Scanner(System.in);
    private UsuarioController controller = new UsuarioController();

    public void InterfazUsuario() {
        String usuario, password;

        System.out.println("\n===== Incio de sesion =====");

        System.out.println("Ingrese usuario: ");
        usuario = scanner.nextLine();
        System.out.println("Ingrese su contrasena: ");
        password = scanner.nextLine();

        controller.IniciarSesion(usuario, password);
    }
}
