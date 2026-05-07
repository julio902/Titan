package models;
import controllers.InventarioController;
import views.MenuAlmacenista;

public class Almacenista extends Usuario {
    public Almacenista(String user, String correo, String password) {
        super(user, correo, password, "almacenista");
    }

    @Override
    public void mostrarMenu(InventarioController controller) {
        new MenuAlmacenista(controller).mostrarMenu();
    }
}