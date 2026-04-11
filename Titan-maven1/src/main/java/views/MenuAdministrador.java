package views;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import controllers.UsuarioController;
import models.Usuario;


public class MenuAdministrador {
    private final Scanner scanner = new Scanner(System.in);
    private final UsuarioController usuarioController = new UsuarioController();

    // Este es el método que llamará MenuLogin
    public void mostrar() {
        int opcion;
    // ==============================================
    // MENU ADMINISTRADOR 
    // ==============================================


        do {
            System.out.println("\n=== === === MENÚ ADMINISTRADOR === === ===");
            System.out.println("\n\t[1] Registrar nuevo usuario");
            System.out.println("\t[2] Listar usuarios");
            System.out.println("\t[3] Regresar al Menu Anterior");
            System.out.println("==========================================");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> registrarUsuario();
                case 2 -> mostrarUsuarios();
                case 3 -> {
                    System.out.println("Volviendo al Menu Anterior...");
                    return;
                }
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 3);
             System.out.println("Regresando a la pantalla de inicio de sesión.");
             new MenuLogin().mostrarLogin();
    }
    
    // ==============================================
    // REGISTRO DE USUARIOS NUEVOS 
    // ==============================================
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

    private void registrarUsuario() {

        System.out.println("\n=== === === Nuevos Usuarios === === ===");
        System.out.print("\nNuevo usuario: ");
        String user = scanner.nextLine();
        // creacion del correo mediante el nombre del nuevo usuario a ingresar al programa 
        String userNormalizado = user.trim().replace(" ", "").toLowerCase();
        String dominio = "@confectexctg.com";
        String correoUsuario = userNormalizado+dominio;

        String password;
        do {
        System.out.println("\nLa contraseña debe tener al menos 8 caracteres\nincluir una mayúscula\nuna minúscula\nun número y un carácter especial (@$!%*?&._)");
        System.out.print("\nContraseña: ");
        password = scanner.nextLine();
        System.out.println("\n===========================================");

            if (!validarContrasena(password)) {
                System.out.println("\nLa contraseña debe tener al menos 8 caracteres\nincluir una mayúscula\nuna minúscula\nun número y un carácter especial (@$!%*?&._)");
            }
        } while (!validarContrasena(password));

        // Aquí puedes continuar con el registro del usuario
        System.out.println("Usuario registrado exitosamente con el correo: " + correoUsuario);     
        
        // asignacion del rol para el nuevo usuario

        System.out.println("\nSeleccione el rol del nuevo usuario:");
        System.out.println("\t[1] Administrador");
        System.out.println("\t[2] Almacenista");
        System.out.println("\t[3] Vendedor");
        System.out.print("Opción: ");

        String opcion = scanner.nextLine();
        String rol = "";

        // Eleccion de roles 
        switch (opcion) {
            case "1" -> rol = "administrador";
            case "2" -> rol = "almacenista";
            case "3" -> rol = "vendedor";
            default -> {
                System.out.println(" Opción inválida. No se registró el usuario.");
                return;
            }
        }
            // creamos el usuario con su nombre, correo, password y rol 
        Usuario nuevoUsuario = new Usuario(
            user,               // Nombre real
            correoUsuario,      // Correo generado
            password,           // Contraseña
            rol                 // Roll Asignado
        );        

        boolean registrado = usuarioController.registrarUsuario(nuevoUsuario);

         if (registrado) {
            System.out.println("Usuario registrado correctamente con rol: " + rol);
        } else {
            System.out.println("No se pudo registrar el usuario (ya existe).");
        }
       
    }
    //================================================
    // Lista de usuarios registrados en el sistema
    //================================================

    private void mostrarUsuarios() {
        List<Usuario> lista = usuarioController.obtenerUsuarios();

        if (lista.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }

        System.out.println("\n\t=== === ===LISTA DE USUARIOS === === ===\n");
        System.out.printf("%-40s %-15s\n", "Usuario", "Rol");
        System.out.println("-------------------------------------------------------");
        for (Usuario u : lista) {
            System.out.printf("%-40s %-15s\n", u.getUser(), u.getRol());
        }

        System.out.println("=======================================================");
    }

    //private void modificarUsuario(){

        

        
    //}
}
