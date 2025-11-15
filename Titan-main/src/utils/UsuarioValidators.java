package utils;

import java.security.SecureRandom;



public class UsuarioValidators {

    private static final String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String MINUSCULAS  = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMEROS = "0123456789";
    private static final String ESPECIALES = "!@#$%&-_?";

    private static final String ALL = MAYUSCULAS + MINUSCULAS + NUMEROS + ESPECIALES;
    private static final int DEFAULT_LENGTH = 8;

    public static String generarPasswordAleatoria() {
        return generarPasswordAleatoria(DEFAULT_LENGTH);
    }

    public static String generarPasswordAleatoria(int length){
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        sb.append(MAYUSCULAS.charAt(random.nextInt(MAYUSCULAS.length())));
        sb.append(MINUSCULAS.charAt(random.nextInt(MINUSCULAS.length())));
        sb.append(NUMEROS.charAt(random.nextInt(NUMEROS.length())));
        sb.append(ESPECIALES.charAt(random.nextInt(ESPECIALES.length())));

        for (int i = 4; i < length; i++) {
            sb.append(ALL.charAt(random.nextInt(ALL.length())));
        }
        return sb.toString();
    }


}