package models;
import views.MenuAdministrador;

public class Administrador extends Usuario {
    public Administrador(String user, String correo, String password) {
        super(user, correo, password, "administrador");
    }
    @Override
    public void mostrarMenu() {
        new MenuAdministrador().mostrar();
    }
}


/*con la clase abstracta de usuario heredamos sus atributos
creo las clases hijas de "ADMINISTRADOR", "ALAMACENISTA" Y "VENDEDOR"
y dentro de cada clase importamos de las vistas "views" es decir cada uno de sus menus 

 */