package views;

import controllers.InventarioController;
import java.util.Scanner;

public class MenuAlmacenista {
    private final Scanner scanner = new Scanner(System.in);
    private final InventarioController inventarioController = new InventarioController();

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n=== === === SISTEMA DE INVENTARIO === === ===");
            System.out.println("\n=== === === MENÚ ALMACEN  === === ===");
            System.out.println("\t[1] Agregar producto");
            System.out.println("\t[2] Listar productos");
            System.out.println("\t[3] Buscar producto");
            System.out.println("\t[4] Modificar producto");
            System.out.println("\t[5] Eliminar producto");
            System.out.println("\t[0] Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1 -> inventarioController.agregarProducto();
                case 2 -> inventarioController.listarProductos();
                case 3 -> inventarioController.buscarProducto();
                case 4 -> inventarioController.modificarProducto();
                case 5 -> inventarioController.eliminarProducto();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }
}
