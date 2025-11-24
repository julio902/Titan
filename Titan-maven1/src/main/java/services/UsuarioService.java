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
            if (u.getCorreo().equalsIgnoreCase(user)
                    && u.getPassword().equals(password)
                    && u.getRol().equalsIgnoreCase(rol)) {
                return u;
            }
        }
        return null;
    }

    // REGISTRAR USUARIO CORRECTAMENTE 
    public boolean registrarUsuario(Usuario nuevo) {

        // Validar si ya existe el correo o nombre de usuario
        for (Usuario u : usuarios) {
            if (u.getCorreo().equalsIgnoreCase(nuevo.getCorreo())
                || u.getUser().equalsIgnoreCase(nuevo.getUser())) {

                return false; // Usuario ya existe
            }
        }

        // Agregar usuario
        usuarios.add(nuevo);
        FileUtils.guardarUsuarios(usuarios);

        return true; // Registrado correctamente
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }
}
