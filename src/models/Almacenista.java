package models;
import views.MenuAlmacenista;

public class Almacenista extends Usuario {
    public Almacenista(String user, String correo, String password) {
        super(user, correo, password, "almacenista");
    }

    @Override
    public void mostrarMenu() {
        new MenuAlmacenista().mostrarMenu();
    }
}