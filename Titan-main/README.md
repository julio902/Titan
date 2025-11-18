# 🧾 Sistema de Gestión de Inventario (Java - Consola)

Un proyecto de consola desarrollado en **Java**, que implementa una estructura **modular y escalable** siguiendo el patrón **MVC (Modelo - Vista - Controlador)**.

---

## 📂 Estructura del Proyecto

```
src/
 ├── app/
 │    └── Main.java
 ├── controllers/
 │    └── InventarioController.java
 ├── models/
 │    └── Producto.java
 ├── services/
 │    └── InventarioService.java
 ├── utils/
 │    └── InputUtils.java
 └── views/
      └── MenuInventario.java
```

---

## 🧩 Descripción de Carpetas

| Carpeta | Contiene | Descripción |
|----------|-----------|-------------|
| **app/** | `Main.java` | Punto de entrada del programa. Inicia la aplicación. |
| **controllers/** | `InventarioController.java` | Controla la comunicación entre Vista y Servicio. |
| **models/** | `Producto.java` | Clases que representan los datos del sistema. |
| **services/** | `InventarioService.java` | Lógica de negocio y operaciones sobre los datos. |
| **utils/** | `InputUtils.java` | Funciones auxiliares (validaciones, entrada de datos, etc.). |
| **views/** | `MenuInventario.java` | Interfaces de usuario por consola (menús, interacciones). |

---

## ⚙️ Ejemplo de cada archivo

### 🏁 `app/Main.java`
```java
package app;

import views.MenuInventario;

public class Main {
    public static void main(String[] args) {
        new MenuInventario().mostrarMenu();
    }
}
```

---

### 🧠 `controllers/InventarioController.java`
```java
package controllers;

import models.Producto;
import services.InventarioService;
import java.util.Scanner;

public class InventarioController {
    private InventarioService service = new InventarioService();
    private Scanner scanner = new Scanner(System.in);

    public void agregarProducto() {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Cantidad: ");
        int cantidad = Integer.parseInt(scanner.nextLine());
        System.out.print("Precio: ");
        double precio = Double.parseDouble(scanner.nextLine());

        Producto producto = new Producto(codigo, nombre, cantidad, precio);
        service.agregarProducto(producto);
        System.out.println("✅ Producto agregado correctamente.");
    }

    public void listarProductos() {
        System.out.println("=== Lista de Productos ===");
        for (Producto p : service.listarProductos()) {
            System.out.println(p);
        }
    }

    public void eliminarProducto() {
        System.out.print("Ingrese el código del producto a eliminar: ");
        String codigo = scanner.nextLine();
        if (service.eliminarProducto(codigo)) {
            System.out.println("✅ Producto eliminado.");
        } else {
            System.out.println("❌ No se encontró el producto.");
        }
    }
}
```

---

### 📦 `models/Producto.java`
```java
package models;

public class Producto {
    private String codigo;
    private String nombre;
    private int cantidad;
    private double precio;

    public Producto(String codigo, String nombre, int cantidad, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public double getPrecio() { return precio; }

    @Override
    public String toString() {
        return String.format("%s - %s | Cant: %d | $%.2f", codigo, nombre, cantidad, precio);
    }
}
```

---

### 🔧 `services/InventarioService.java`
```java
package services;

import models.Producto;
import java.util.ArrayList;
import java.util.List;

public class InventarioService {
    private List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public List<Producto> listarProductos() {
        return productos;
    }

    public boolean eliminarProducto(String codigo) {
        Producto encontrado = null;
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                encontrado = p;
                break;
            }
        }
        if (encontrado != null) {
            productos.remove(encontrado);
            return true;
        }
        return false;
    }
}
```

---

### 🧰 `utils/InputUtils.java`
```java
package utils;

import java.util.Scanner;

public class InputUtils {
    private static Scanner scanner = new Scanner(System.in);

    public static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Intente de nuevo.");
            }
        }
    }

    public static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Intente de nuevo.");
            }
        }
    }
}
```

---

### 🎨 `views/MenuInventario.java`
```java
package views;

import controllers.InventarioController;
import java.util.Scanner;

public class MenuInventario {
    private Scanner scanner = new Scanner(System.in);
    private InventarioController controller = new InventarioController();

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ DE INVENTARIO =====");
            System.out.println("[1] Agregar producto");
            System.out.println("[2] Listar productos");
            System.out.println("[3] Eliminar producto");
            System.out.println("[4] Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> controller.agregarProducto();
                case 2 -> controller.listarProductos();
                case 3 -> controller.eliminarProducto();
                case 4 -> System.out.println("👋 Saliendo...");
                default -> System.out.println("❌ Opción inválida");
            }
        } while (opcion != 4);
    }
}
```

---

## 🚀 Cómo ejecutar el programa

1. Clonar o descargar el repositorio.
2. Compilar el código:
   ```bash
   javac -d bin src/**/*.java
   ```
3. Ejecutar el programa:
   ```bash
   java -cp bin app.Main
   ```

---

## 📚 Patrón utilizado: MVC

```
┌──────────┐        ┌────────────┐        ┌──────────────┐
│   Vista  │ <----> │ Controlador│ <----> │   Modelo     │
└──────────┘        └────────────┘        └──────────────┘
```

- **Vista:** `MenuInventario.java`
- **Controlador:** `InventarioController.java`
- **Modelo:** `Producto.java`
- **Servicio:** `InventarioService.java` (lógica del negocio)
