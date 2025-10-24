package services;

import java.util.ArrayList;
import java.util.List;
import models.Usuario;

public class UsuarioService {
    private List<Usuario> usuarios = new ArrayList<>();

    void constructor(){
        usuarios.add(new Usuario("admin@confectex.com", "prueba123"));
    }

    public void agregarUsuario(Usuario p) {
        usuarios.add(p);
    }
}
