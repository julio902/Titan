package controllers;

import java.util.List;
import models.Usuario;
import services.UsuarioService;

public class UsuarioController {

    private final UsuarioService usuarioService = new UsuarioService();

    public Usuario validarCredenciales(String user, String pass, String rol) {
        return usuarioService.validarCredenciales(user, pass, rol);
    }

    public void registrarUsuario(Usuario usuario) {
        usuarioService.registrarUsuario(usuario);
    }
    
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.listarUsuarios();
    }

    public void listarUsuarios() {
        for (Usuario u : usuarioService.listarUsuarios()) {
            System.out.println(u);
        }
    }
}
