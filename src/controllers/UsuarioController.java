package controllers;

import models.Usuario;
import services.UsuarioService;

public class UsuarioController {
    private UsuarioService service = new UsuarioService();

    public Usuario verificarUsuario(String user, String password) {
        return service.verificarUsuario(user, password);
    }

    public void registrarUsuario(Usuario nuevo) {
        service.registrarUsuario(nuevo);
        System.out.println("✅ Usuario registrado correctamente.");
    }

    public void listarUsuarios() {
        for (Usuario u : service.listarUsuarios()) {
            System.out.println(u);
        }
    }
}
