package controllers;

import utils.UsuarioValidators;

public class UsuarioController {

    public void IniciarSesion(String usuario, String password) {
        boolean isValidEmail = UsuarioValidators.validarCorreo(usuario);

        if (!isValidEmail) {
            System.out.println("Correo Invalido");
            return;
        }
        boolean isValidPassword = UsuarioValidators.validarContrasena(password);

        if (!isValidPassword) {
            System.out.println("Contrasena Invalida");
            return;
        }
    }
    
}
