package models;
import controllers.InventarioController;
import views.MenuVendedor;

public class Vendedor extends Usuario {
    public Vendedor(String user, String correo, String password) {
        super(user, correo, password, "vendedor");
    }

    @Override
    public void mostrarMenu(InventarioController controller) {
        // El vendedor necesita el objeto usuario para saludar
        new MenuVendedor(controller).mostrarMenu(this);
    }
}