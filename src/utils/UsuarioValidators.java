package utils;

import java.util.regex.Pattern;


public class UsuarioValidators {
    // 
    
    public static boolean validarCorreo(String correo) {
        // Expresión regular básica para validar formato de correo********
        String regexCorreo = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regexCorreo);

        // Retorna true si el correo cumple con el patrón
        return pattern.matcher(correo).matches();
    }
     public static boolean validarContrasena(String contrasena) {
        // Expresión regular para validar una contraseña segura******
        String regexContrasena =
                "^(?=.*[a-z])" +        // al menos una letra minúscula
                "(?=.*[A-Z])" +        // al menos una letra mayúscula
                "(?=.*\\d)" +          // al menos un dígito
                "(?=.*[@$!%*?&._-])" + // al menos un carácter especial
                "[A-Za-z\\d@$!%*?&._-]{8,}$"; // mínimo 8 caracteres

        Pattern pattern = Pattern.compile(regexContrasena);
        return pattern.matcher(contrasena).matches();
    }


}
