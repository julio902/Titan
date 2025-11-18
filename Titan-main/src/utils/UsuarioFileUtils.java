package utils;

import java.io.*;
import java.util.*;
import models.Usuario;

public class UsuarioFileUtils {
    private static final String FILE_PATH = "usuarios.txt";

    public static void guardarUsuarios(List<Usuario> usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Usuario u : usuarios) {
                writer.write(u.getUser() + ";" + u.getPassword() + ";" + u.getRol());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error al guardar los usuarios: " + e.getMessage());
        }
    }

    public static List<Usuario> cargarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        File archivo = new File(FILE_PATH);

        if (!archivo.exists()) return usuarios;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 3) {
                    usuarios.add(new Usuario(datos[0], datos[1], datos[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error al cargar los usuarios: " + e.getMessage());
        }

        return usuarios;
    }
}
