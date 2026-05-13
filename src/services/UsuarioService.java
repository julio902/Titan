package services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import models.Usuario;
import utils.FileUtils;

public class UsuarioService {

    private List<Usuario> usuarios;

    public UsuarioService() {

        usuarios = FileUtils.cargarUsuarios();

        // Evitar errores si el archivo viene null
        if (usuarios == null) {
            usuarios = new ArrayList<>();
        }
    }

    // =========================================
    // LOGIN BÁSICO
    // =========================================
    public Usuario verificarUsuario(String user, String password) {

        for (Usuario u : usuarios) {

            if (u.getUser().equalsIgnoreCase(user)
                    && u.getPassword().equals(password)) {

                return u;
            }
        }

        return null;
    }

    // =========================================
    // LOGIN COMPLETO
    // =========================================
    public Usuario validarCredenciales(
            String user,
            String password,
            String rol
    ) {

        for (Usuario u : usuarios) {

            if (u.getCorreo().equalsIgnoreCase(user)
                    && u.getPassword().equals(password)
                    && u.getRol().equalsIgnoreCase(rol)) {

                return u;
            }
        }

        return null;
    }

    // =========================================
    // REGISTRAR USUARIO
    // =========================================
    public boolean registrarUsuario(Usuario nuevo) {

        // Validar duplicados
        for (Usuario u : usuarios) {

            if (u.getCorreo().equalsIgnoreCase(nuevo.getCorreo())
                    || u.getUser().equalsIgnoreCase(nuevo.getUser())) {

                return false;
            }
        }

        usuarios.add(nuevo);

        FileUtils.guardarUsuarios(usuarios);

        return true;
    }

    // =========================================
    // LISTAR USUARIOS
    // =========================================
    public List<Usuario> listarUsuarios() {

        // Devuelve copia protegida
        return new ArrayList<>(usuarios);
    }

    // =========================================
    // ELIMINAR USUARIO
    // =========================================
    public boolean eliminarUsuario(String username) {

        Iterator<Usuario> iterator = usuarios.iterator();

        while (iterator.hasNext()) {

            Usuario usuario = iterator.next();

            // Validar username
            if (usuario.getUser().equalsIgnoreCase(username)) {

                // Evitar eliminar administradores
                if (usuario.getRol().equalsIgnoreCase("Administrador")) {

                    System.out.println(
                            "❌ No se pueden eliminar administradores."
                    );

                    return false;
                }

                iterator.remove();

                FileUtils.guardarUsuarios(usuarios);

                return true;
            }
        }

        return false;
    }
}