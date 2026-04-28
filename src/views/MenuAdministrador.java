package views;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import controllers.UsuarioController;
import models.Administrador;
import models.Almacenista;
import models.Usuario;
import models.Vendedor;

public class MenuAdministrador {
    private final Scanner scanner = new Scanner(System.in);
    private final UsuarioController usuarioController = new UsuarioController();

    public void mostrar() {
        int opcion;
                        /*==================MENU ADMINISTRADOR===============
                        ===================================================== */


        do {
            System.out.println("\n=== === === MENÚ ADMINISTRADOR === === ===");//   vista principal del menu Administrador
            System.out.println("\n\t[1] Registrar nuevo usuario");
            System.out.println("\t[2] Listar usuarios");
            System.out.println("\t[3] Regresar al Menu Anterior");
            System.out.println("==========================================");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());  // evita que tenga errores comoel ingreso de letras o signos
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                opcion = 0;
                continue;
            }

            switch (opcion) {
                case 1 -> registrarUsuario();
                case 2 -> mostrarUsuarios();
                case 3 -> System.out.println("Volviendo al Menu Anterior...");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 3);
    }

    private void mostrarUsuarios() {   // =================== MOSTRAR USUARIO =================

        List<Usuario> lista = usuarioController.obtenerUsuarios(); // VALIDA SI LA LISTA ESTA VACIA
        if (lista.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }

        System.out.println("\n=== LISTA DE USUARIOS ==="); // LISTA DE USUARIOS YA CREADOS
        System.out.printf("%-20s %-15s\n", "Usuario", "Rol");
        System.out.println("---------------------------------------");
        for (Usuario u : lista) {
            System.out.printf("%-20s %-15s\n", u.getUser(), u.getRol());
        }
    }
    
    public static boolean validarContrasena(String contrasena) { //==================== VAIDACION DE LA CONRASEÑA ==============

        String regexContrasena = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._-])[A-Za-z\\d@$!%*?&._-]{8,}$"; //PARAMETROS DE VALIDACION PARA CREACION DE LA CONTRESEÑA
        return Pattern.compile(regexContrasena).matcher(contrasena).matches();
    }

    private void registrarUsuario() {       // ===========REGISTRAR USUARIO ================
        System.out.println("\n=== === === Nuevos Usuarios === === ===");
        System.out.print("\nNuevo usuario: ");
        String user = scanner.nextLine();
        
        String userNormalizado = user.trim().replace(" ", "").toLowerCase();
        String correoUsuario = userNormalizado + "@confectexctg.com";   // ------------------>   CREACION DE DOMINIO CONFECTEXCTG.COM

        String password;
        do {
            System.out.print("\nIngrese contraseña segura: ");
            password = scanner.nextLine();
            if (!validarContrasena(password)) {
                System.out.println("\n¿error! La contraseña no cumple con los requisitos de seguridad.");// MENSAJE POR SI LA CONTRASEÑA ESTA MALA
                System.out.println("""
                                    La contraseña debe tener:
                                    - Al menos una minúscula
                                    - Al menos una mayúscula
                                    - Al menos un número
                                    - Un símbolo
                                    - Mínimo 8 caracteres
                                    """);
            }
        } while (!validarContrasena(password));

        System.out.println("\nSeleccione el rol del nuevo usuario:");
        System.out.println("\t[1] Administrador");
        System.out.println("\t[2] Almacenista");
        System.out.println("\t[3] Vendedor");
        System.out.print("Opción: ");

        String opcion = scanner.nextLine();
        
        // ==========================================================
        // APLICAMOS PILOMORFISMO PARA LA CREACION DEL NUEVO USUARIO
        // ==========================================================
        Usuario nuevoUsuario = null;  // VARIABLE TIPO USUARIO DONDE GUARDAREMOS EL NUEVO USUARIO SELECCIONADO

        switch (opcion) {       // creamos un objeto tipo con el tipo de usuario seleccionado ya sea (ADMINISTRADOR, VENDEDOR. ALMACENISTA)
            case "1" -> nuevoUsuario = new Administrador(user, correoUsuario, password);
            case "2" -> nuevoUsuario = new Almacenista(user, correoUsuario, password);
            case "3" -> nuevoUsuario = new Vendedor(user, correoUsuario, password);
            default -> {
                System.out.println("Opción inválida. No se registró el usuario.");
                return;
            }
        }

        
        boolean registrado = usuarioController.registrarUsuario(nuevoUsuario); // VALIDA SI EL USUARIO EXISTE. LLEGADO EL CASO DE NO EXISTIR " GUARDARLO"

        if (registrado) {
            System.out.println("Usuario registrado correctamente como: " + nuevoUsuario.getRol());
        } else {
            System.out.println("No se pudo registrar el usuario (ya existe).");
        }
    }
}