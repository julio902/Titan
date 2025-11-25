package utils;

import java.io.*;
import java.util.*;
import models.Producto;
import models.Usuario;

public class FileUtils {

    private static final String FILE_PRODUCTOS = "productos.txt";
    private static final String FILE_USUARIOS = "usuarios.txt";

    // =================== PRODUCTOS ===================
    public static void guardarProductos(List<Producto> productos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PRODUCTOS))) {
            for (Producto p : productos) {
                writer.write(
                        p.getCodigo() + ";" +
                        p.getNombre() + ";" +
                        p.getdescripcion() + ";" +
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
                    String codigo = datos[0];
                    String nombre = datos[1];
                    String descripcion = datos[2];
                    int cantidad = Integer.parseInt(datos[3]);
                    double precio = Double.parseDouble(datos[4]);

                    productos.add(new Producto(codigo, nombre, descripcion, cantidad, precio));
                }
            }

        } catch (IOException e) {
            System.out.println("Error al cargar productos: " + e.getMessage());
        }

        return productos;
    }

    // =================== USUARIOS ===================
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

            // Crear usuarios por defecto con correo institucional
            usuarios.add(new Usuario("Administrador", "admin@confectexctg.com", "Discodurode1ter@", "administrador"));
            usuarios.add(new Usuario("Almacenista", "almacen@confectexctg.com", "123", "almacenista"));
            usuarios.add(new Usuario("Vendedor", "vendedor@confectexctg.com", "123", "vendedor"));

            guardarUsuarios(usuarios);
            return usuarios;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_USUARIOS))) {
            String linea;

            while ((linea = reader.readLine()) != null) {

                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split(";");

                // Formato: user;correo;password;rol
                if (datos.length == 4) {

                    String user = datos[0];
                    String correo = datos[1];
                    String password = datos[2];
                    String rol = datos[3];

                    usuarios.add(new Usuario(user, correo, password, rol));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar usuarios: " + e.getMessage());
        }

        return usuarios;
    }
}
