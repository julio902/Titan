# 🧾 Titan - Sistema de Gestión de Inventario (Java)

**Titan** es un robusto sistema de gestión de inventario desarrollado en **Java**, diseñado bajo el patrón de arquitectura **MVC (Modelo-Vista-Controlador)**. La aplicación permite una gestión eficiente de productos y usuarios mediante un sistema de acceso basado en roles.

---

## 🚀 Funcionalidades Principales

-   **Acceso Basado en Roles:**
    -   **Administrador:** Control total sobre usuarios e inventario.
    -   **Almacenista:** Gestión de stock y productos.
    -   **Vendedor:** Consulta y registro de ventas.
-   **Persistencia de Datos:** Los datos se almacenan y cargan dinámicamente desde archivos de texto plano (`.txt`), garantizando que la información se conserve entre sesiones.
-   **Arquitectura MVC:** Separación clara entre la lógica de negocio, los datos y la interfaz de usuario.
-   **Interfaz de Consola Intuitiva:** Menús dinámicos adaptados según el tipo de usuario que inicia sesión.

---

## 📂 Estructura del Proyecto

```text
Titan/
├── src/
│   ├── app/
│   │   └── Main.java                 # Punto de entrada de la aplicación
│   ├── controllers/
│   │   ├── InventarioController.java  # Lógica de control para el inventario
│   │   └── UsuarioController.java     # Lógica de control para usuarios y login
│   ├── models/
│   │   ├── Usuario.java              # Clase base para usuarios
│   │   ├── Administrador.java        # Especialización para administradores
│   │   ├── Almacenista.java          # Especialización para almacenistas
│   │   ├── Vendedor.java             # Especialización para vendedores
│   │   └── Producto.java             # Representación de un producto
│   ├── services/
│   │   ├── InventarioService.java    # Lógica de negocio de productos
│   │   └── UsuarioService.java       # Lógica de negocio de usuarios
│   ├── utils/
│   │   └── FileUtils.java            # Utilidades para persistencia en archivos
│   └── views/
│       ├── MenuLogin.java            # Pantalla de inicio de sesión
│       ├── MenuAdministrador.java    # Menú principal para administradores
│       ├── MenuAlmacenista.java      # Menú principal para almacenistas
│       ├── MenuVendedor.java         # Menú principal para vendedores
│       └── MenuInventarioAdmin.java  # Submenú de inventario para admins
├── productos.txt                     # Almacenamiento persistente de productos
└── usuarios.txt                      # Almacenamiento persistente de usuarios
```

---

## 🛠️ Tecnologías Utilizadas

-   **Lenguaje:** Java (JDK 17 o superior recomendado).
-   **Persistencia:** Archivos de texto plano (`CSV` style).
-   **Paradigma:** Programación Orientada a Objetos (POO).

---

## ⚙️ Cómo Ejecutar el Programa

1.  **Requisitos:** Tener instalado el JDK.
2.  **Compilación:**
    Desde la raíz del proyecto, ejecuta:
    ```bash
    javac -d bin -sourcepath src src/app/Main.java
    ```
3.  **Ejecución:**
    ```bash
    java -cp bin app.Main
    ```

---

## 👥 Roles y Permisos

| Rol | Gestión de Usuarios | Gestión de Inventario | Visualización |
| :--- | :---: | :---: | :---: |
| **Administrador** | ✅ | ✅ | ✅ |
| **Almacenista** | ❌ | ✅ | ✅ |
| **Vendedor** | ❌ | ❌ | ✅ |

---

## 📝 Notas de Desarrollo

-   La aplicación utiliza `FileUtils` para leer y escribir en `productos.txt` y `usuarios.txt`.
-   El sistema de login valida las credenciales contra el archivo de usuarios.
-   Se ha priorizado la escalabilidad para permitir añadir nuevos tipos de productos o roles en el futuro.
