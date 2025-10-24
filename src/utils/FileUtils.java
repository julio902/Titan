package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import models.Producto;

public class FileUtils {
    private static final String FILE_PATH = "productos.txt";

    // 🔹 Guardar lista completa en el archivo
    public static void guardarProductos(List<Producto> productos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Producto p : productos) {
                writer.write(p.getCodigo() + ";" + p.getNombre() + ";" + p.getCantidad() + ";" + p.getPrecio());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error al guardar los productos: " + e.getMessage());
        }
    }

    // 🔹 Cargar productos desde el archivo (si existe)
    public static List<Producto> cargarProductos() {
        List<Producto> productos = new ArrayList<>();

        File archivo = new File(FILE_PATH);
        if (!archivo.exists()) {
            return productos; // retorna lista vacía si no hay archivo
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 4) {
                    String codigo = datos[0];
                    String nombre = datos[1];
                    int cantidad = Integer.parseInt(datos[2]);
                    double precio = Double.parseDouble(datos[3]);
                    productos.add(new Producto(codigo, nombre, cantidad, precio));
                }
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error al cargar los productos: " + e.getMessage());
        }

        return productos;
    }
}
