package services;

import java.util.List;
import models.Usuario;
import utils.FileUtils;

public class UsuarioService {

    private List<Usuario> usuarios;

    public UsuarioService() {
        usuarios = FileUtils.cargarUsuarios();
    }

    // LOGIN BÁSICO (usuario + password)
    public Usuario verificarUsuario(String user, String password) {
        for (Usuario u : usuarios) {
            if (u.getUser().equalsIgnoreCase(user)
                    && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    // LOGIN COMPLETO (usuario + password + rol)
    public Usuario validarCredenciales(String user, String password, String rol) {

        for (Usuario u : usuarios) {
            if (u.getUser().equalsIgnoreCase(user)
                    && u.getPassword().equals(password)
                    && u.getRol().equalsIgnoreCase(rol)) {
                return u;
            }
        }
        return null;
    }

    public void registrarUsuario(Usuario nuevo) {
        usuarios.add(nuevo);
        FileUtils.guardarUsuarios(usuarios);
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }
}
