package views;

import controllers.InventarioController;
import java.util.Scanner;
import models.Usuario;

public class MenuVendedor {
    private final Scanner scanner = new Scanner(System.in);
    private final InventarioController inventarioController = new InventarioController();

    public void mostrarMenu(Usuario vendedor) {
        int opcion;
        do {
            System.out.println("\n=== === === MENÚ VENDEDOR === === ===");
            System.out.println("\t[1] Ver stock de productos");
            System.out.println("\t[2] Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> inventarioController.listarProductos();
                case 2 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 2);
          System.out.println("Regresando a la pantalla de inicio de sesión.");
          new MenuLogin().mostrarLogin();
    }
}
