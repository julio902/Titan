package views;

import controllers.InventarioController;
import java.util.Scanner;

public class MenuInventario {

    private Scanner scanner = new Scanner(System.in);
    private InventarioController controller = new InventarioController();

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ DE INVENTARIO =====");
            System.out.println("[1] Agregar producto");
            System.out.println("[2] Listar productos");
            System.out.println("[3] Eliminar producto");
            System.out.println("[4] Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 ->
                    controller.agregarProducto();
                case 2 ->
                    controller.listarProductos();
                case 3 ->
                    controller.eliminarProducto();
                case 4 ->
                    System.out.println("👋 Saliendo...");
                default ->
                    System.out.println("❌ Opción inválida");
            }
        } while (opcion != 4);

    }

}
