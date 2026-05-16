package controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import models.Venta;

/*
 * Controlador de ventas
 * Gestiona guardar y leer ventas
 */

public class VentaController {

    // Ruta archivo TXT (en la raíz para consistencia)
    private final String ARCHIVO = "ventas.txt";

    // Contador ventas
    private int contadorVentas = 1;

    public VentaController() {
        inicializarContador();
    }

    /*
     * Inicializar contador basado en las ventas existentes
     */
    private void inicializarContador() {
        List<Venta> ventas = obtenerVentas();
        for (Venta v : ventas) {
            try {
                // Formato: V-001 -> extraemos 001
                String numStr = v.getNumeroVenta().substring(2);
                int num = Integer.parseInt(numStr);
                if (num >= contadorVentas) {
                    contadorVentas = num + 1;
                }
            } catch (Exception e) {
                // Ignorar si el formato no coincide
            }
        }
    }

    /*================================================
     * Generar número venta
    * ================================================*/
    public String generarNumeroVenta() {
        return String.format("V-%03d", contadorVentas++);
    }

    /*================================================
     * Guardar venta en TXT
    * ================================================*/
    public void guardarVenta(Venta venta) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            bw.write(venta.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error guardando venta: " + e.getMessage());
        }
    }

    /*================================================
     * Obtener ventas del TXT
    * ================================================*/
    public List<Venta> obtenerVentas() {
        List<Venta> ventas = new ArrayList<>();
        File file = new File(ARCHIVO);
        
        if (!file.exists()) {
            return ventas;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                
                String[] datos = linea.split(",");
                if (datos.length < 8) continue;

                Venta venta = new Venta(
                    datos[0],
                    datos[1],
                    datos[2],
                    datos[3],
                    datos[4],
                    datos[5],
                    Integer.parseInt(datos[6]),
                    Double.parseDouble(datos[7])
                );
                ventas.add(venta);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error leyendo ventas: " + e.getMessage());
        }
        return ventas;
    }
}