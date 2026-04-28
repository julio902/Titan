package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File; // Importamos Administrador, Vendedor, Almacenista, etc.
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Administrador;
import models.Almacenista;
import models.Producto;
import models.Usuario;
import models.Vendedor;

public class FileUtils {

    private static final String FILE_PRODUCTOS = "productos.txt";
    private static final String FILE_USUARIOS = "usuarios.txt";

    // ==============================================
    // PRODUCTOS: GUARDAR Y CARGAR
    // ==============================================
    public static void guardarProductos(List<Producto> productos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PRODUCTOS))) {
            for (Producto p : productos) {
                writer.write(
                    p.getCodigo() + ";" +
                    p.getNombre() + ";" +
                    p.getDescripcion() + ";" +
                    p.getCantidad() + ";" +
                    p.getPrecio()
                );
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar productos: " + e.getMessage());
        }
    }

    public static List<Producto> cargarProductos() {
        List<Producto> productos = new ArrayList<>();
        File archivo = new File(FILE_PRODUCTOS);
        if (!archivo.exists()) return productos;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PRODUCTOS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(";");
                if (datos.length == 5) {
                    productos.add(new Producto(
                        datos[0], // codigo
                        datos[1], // nombre
                        datos[2], // descripcion
                        Integer.parseInt(datos[3]), // cantidad
                        Double.parseDouble(datos[4]) // precio
                    ));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al cargar productos: " + e.getMessage());
        }
        return productos;
    }

    // ==============================================
    // USUARIOS: ACTUALIZADO CON CLASE ABSTRACTA
    // ==============================================
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

        // Si el archivo no existe, creamos los hijos específicos de la clase abstracta
        if (!archivo.exists()) {
            usuarios.add(new Administrador("Administrador", "admin@confectexctg.com", "Discodurode1ter@"));
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
                    String user = datos[0];
                    String correo = datos[1];
                    String password = datos[2];
                    String rol = datos[3].toLowerCase().trim();

                    // POLIMORFISMO: Creamos la instancia según el rol leído
                    Usuario u = null;
                    switch (rol) {
                        case "administrador" -> u = new Administrador(user, correo, password);
                        case "almacenista" -> u = new Almacenista(user, correo, password);
                        case "vendedor" -> u = new Vendedor(user, correo, password);
                    }
                    if (u != null) usuarios.add(u);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar usuarios: " + e.getMessage());
        }
        return usuarios;
    }
}