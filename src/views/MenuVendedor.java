package views;

import controllers.InventarioController;
import models.Usuario;
import utils.InputUtils;

public class MenuVendedor {

    private final InventarioController inventarioController;

    // 🔥 IMPORTANTE: RECIBIR EL CONTROLLER
    public MenuVendedor(InventarioController inventarioController) {
        this.inventarioController = inventarioController;
    }

    public void mostrarMenu(Usuario vendedor) {

        int opcion = -1;

        do {
            System.out.println("\n=== MENÚ VENDEDOR ===");
            System.out.println("[1] Ver productos");
            System.out.println("[2] Buscar producto");
            System.out.println("[3] Vender producto 🧾");
            System.out.println("[0] Cerrar sesión");
            
            opcion = InputUtils.leerEntero("Opción: ");

            switch (opcion) {

                case 1 -> inventarioController.listarProductos();

                case 2 -> inventarioController.buscarProducto();

                case 3 -> inventarioController.venderProducto();

                case 0 -> System.out.println("Cerrando sesión...");

                default -> System.out.println("❌ Opción inválida.");
            }

        } while (opcion != 0);

        System.out.println("Regresando al inicio...");
    }
}