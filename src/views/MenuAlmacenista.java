package views;

import controllers.InventarioController;
import utils.InputUtils;

public class MenuAlmacenista {

    private final InventarioController inventarioController;

    // 🔥 IMPORTANTE: RECIBIR EL CONTROLLER
    public MenuAlmacenista(InventarioController inventarioController) {
        this.inventarioController = inventarioController;
    }

    public void mostrarMenu() {

        int opcion = -1;

        do {
            System.out.println("\n=== MENÚ ALMACENISTA ===");
            System.out.println("[1] Ver productos");
            System.out.println("[2] Buscar producto");
            System.out.println("[3] Reponer stock 📦");
            System.out.println("[0] Salir");
            
            opcion = InputUtils.leerEntero("Seleccione: ");

            switch (opcion) {

                case 1 -> inventarioController.listarProductos();

                case 2 -> inventarioController.buscarProducto();

                case 3 -> inventarioController.reponerProducto();

                case 0 -> System.out.println("Saliendo...");

                default -> System.out.println("❌ Opción inválida.");
            }

        } while (opcion != 0);
    }
}