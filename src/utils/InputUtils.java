package utils;

import java.util.Scanner;

/**
 * Utilidad centralizada para la lectura de datos desde la consola.
 * Maneja validaciones de formato y reintentos automáticos.
 */
public class InputUtils {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Lee una cadena de texto de la consola.
     * @param mensaje Mensaje a mostrar al usuario.
     * @return Texto ingresado (limpio de espacios al inicio/final).
     */
    public static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    /**
     * Lee un número entero de la consola con validación de formato.
     * @param mensaje Mensaje a mostrar al usuario.
     * @return El número entero válido.
     */
    public static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Por favor, ingrese un número entero.");
            }
        }
    }

    /**
     * Lee un número decimal de la consola con validación de formato.
     * @param mensaje Mensaje a mostrar al usuario.
     * @return El número decimal válido.
     */
    public static double leerDecimal(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Por favor, ingrese un número decimal.");
            }
        }
    }

    /**
     * Lee un entero opcional. Si el usuario deja el campo vacío, retorna el valor actual.
     * @param mensaje Mensaje a mostrar.
     * @param actual Valor actual a retornar por defecto.
     * @return El entero ingresado o el actual.
     */
    public static int leerEnteroOpcional(String mensaje, int actual) {
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) return actual;
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Ingrese un número o presione Enter para omitir.");
            }
        }
    }

    /**
     * Lee un decimal opcional. Si el usuario deja el campo vacío, retorna el valor actual.
     * @param mensaje Mensaje a mostrar.
     * @param actual Valor actual a retornar por defecto.
     * @return El decimal ingresado o el actual.
     */
    public static double leerDecimalOpcional(String mensaje, double actual) {
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) return actual;
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Ingrese un número o presione Enter para omitir.");
            }
        }
    }
}
