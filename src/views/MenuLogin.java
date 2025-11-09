package views;

import controllers.UsuarioController;
import java.util.Scanner;
import models.Usuario;

public class MenuLogin {
    private final Scanner scanner = new Scanner(System.in);
    private final UsuarioController usuarioController = new UsuarioController();
     

    public  void  mostarLogin(){
        int OpcionMenu=0;
        int Intentos =0;
        boolean Acceso = false;
        do{
        System.out.println("==.::SISTEMA DE INVENTARIO CONFECTEX::.==");
        System.out.println("1)Iniciar Sesion::");
        System.out.println("2)Salir del Systema:: ");
        System.out.println("Digite Opcion: ");
         String entrada  = scanner.nextLine();

         try {       
          OpcionMenu= Integer.parseInt(entrada);
        switch(OpcionMenu){ 

        case 1 ->{ 
         Intentos =0;
         Acceso=false;
         while (Intentos < 3 && !Acceso) {        
        System.out.println("=== SISTEMA DE INVENTARIO ===");
        System.out.print("Usuario: ");
        String user = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        Usuario u = usuarioController.verificarUsuario(user, password);
       
       if (u != null) {
        Acceso=true;
            System.out.println("\n Bienvenido, " + u.getUser() + " (" + u.getRol() + ")\n");

            switch (u.getRol().toLowerCase()) {
                case "administrador" -> new MenuAdministrador().mostrarMenu(u);
                case "almacenista" -> new MenuAlmacenista().mostrarMenu(u);
                case "vendedor" -> new MenuVendedor().mostrarMenu(u);
                default -> System.out.println("Rol no reconocido.");
            }
        } else {
             Intentos++;
             if (Intentos <3){
               System.out.println("Credenciales incorrectas. Intento " + Intentos + " de 3.\n");
            } else {
            System.out.println("Ha superado los 3 intentos fallidos. Regresando al menú principal...\n");
                               }
        }
           }
        }

        case 2 -> {
         
            System.out.println("==Gracias por utilizar nuestra APP==\n");
            System.exit(0);
        }

        default -> System.out.println("==Opcion no valida// Intenta nuevamente==\n");
        
        }

    }catch(NumberFormatException exception){

       System.out.println("Entrada inválida. Por favor, digite numero (1 o 2).\n");
    }
        
        }while(true);
    }
    
  
}
