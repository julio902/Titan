package views;

import java.util.Scanner;

import controllers.InventarioController;

public class MenuInventarioAdmin {

    private final Scanner scanner = new Scanner(System.in);
    private final InventarioController inventarioController;

    public MenuInventarioAdmin(InventarioController inventarioController) {
        this.inventarioController = inventarioController;
    }

    public void mostrar() {

        int opcion = -1;

        do {
            System.out.println("\n=== GESTIÓN DE INVENTARIO ===");
            System.out.println("[1] Agregar producto");
            System.out.println("[2] Listar productos");
            System.out.println("[3] Buscar producto");
            System.out.println("[4] Modificar producto");
            System.out.println("[5] Eliminar producto");
            System.out.println("[6] Reponer stock");
            System.out.println("[7] Ver valor total del inventario");
            System.out.println("[0] Volver");
            System.out.print("Seleccione: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
                continue;
            }

            switch (opcion) {

                case 1 -> inventarioController.agregarProducto();

                case 2 -> inventarioController.listarProductos();

                case 3 -> inventarioController.buscarProducto();

                case 4 -> inventarioController.modificarProducto();

                case 5 -> inventarioController.eliminarProducto();

                case 6 -> inventarioController.reponerProducto();

                case 7 -> {
                    double total = inventarioController.calcularValorTotalInventario();
                    System.out.println("Valor total del inventario: $" + total);
                }

                case 0 -> System.out.println("Volviendo al menú administrador...");

                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }
}