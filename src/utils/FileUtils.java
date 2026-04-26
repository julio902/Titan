package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Administrador;
import models.Almacenista;
import models.Usuario;
import models.Vendedor;

public class FileUtils {

    private static final String FILE_PRODUCTOS = "productos.txt";
    private static final String FILE_USUARIOS = "usuarios.txt";

    // ... (El método guardarProductos y cargarProductos se mantienen igual) ...

    public static void guardarUsuarios(List<Usuario> usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_USUARIOS))) {
            for (Usuario u : usuarios) {
                writer.write(
                        u.getUser() + ";" +
                        u.getCorreo() + ";" +
                        u.getPassword() + ";" +
                        u.getRol()
                );
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

    public static List<Usuario> cargarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        File archivo = new File(FILE_USUARIOS);

        if (!archivo.exists()) {
            // CAMBIO: Ahora creamos instancias de las clases HIJAS
            usuarios.add(new Administrador("Administrador", "admin@confectexctg.com", "123"));
            usuarios.add(new Almacenista("Almacenista", "almacen@confectexctg.com", "123"));
            usuarios.add(new Vendedor("Vendedor", "vendedor@confectexctg.com", "123"));

            guardarUsuarios(usuarios);
            return usuarios;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_USUARIOS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split(";");

                if (datos.length == 4) {
                    // 1. Extraemos los datos del arreglo
                    String user = datos[0];
                    String correo = datos[1];
                    String password = datos[2];
                    String rol = datos[3].toLowerCase().trim(); // limpiamos y pasamos a minisculas

                    // 2. Usamos el switch para decidir qué OBJETO crear
                    switch (rol) {
                        case "administrador" -> usuarios.add(new Administrador(user, correo, password));
                        case "almacenista"   -> usuarios.add(new Almacenista(user, correo, password));
                        case "vendedor"      -> usuarios.add(new Vendedor(user, correo, password));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar usuarios: " + e.getMessage());
        }

        return usuarios;
    }
}