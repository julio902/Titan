package views;

import controllers.InventarioController;
import java.util.Scanner;
import models.Usuario;

public class MenuAlmacenista {
    private final Scanner scanner = new Scanner(System.in);
    private final InventarioController inventarioController = new InventarioController();

    public void mostrarMenu(Usuario almacenista) {
        int opcion;
        do {
            System.out.println("\n=== === === MENÚ ALMACENISTA === === ===");
            System.out.println("\t[1] Agregar producto");
            System.out.println("\t[2] Eliminar producto");
            System.out.println("\t[3] Listar productos");
            System.out.println("\t[4] Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> inventarioController.agregarProducto();
                case 2 -> inventarioController.eliminarProducto();
                case 3 -> inventarioController.listarProductos();
                case 4 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
           System.out.println("Regresando a la pantalla de inicio de sesión.");
           new MenuLogin().mostrarLogin();
    }
}
