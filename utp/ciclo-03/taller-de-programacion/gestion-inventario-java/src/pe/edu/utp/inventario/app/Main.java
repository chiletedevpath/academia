package pe.edu.utp.inventario.app;

import pe.edu.utp.inventario.excepciones.DatoInvalidoException;
import pe.edu.utp.inventario.modelo.Producto;
import pe.edu.utp.inventario.servicio.Inventario;
import pe.edu.utp.inventario.util.Consola;

public class Main {

    public static void main(String[] args) {
        Inventario inventario = new Inventario();

        // El try cierra el Scanner al terminar la aplicación.
        try (Consola consola = new Consola()) {
            ejecutarMenu(inventario, consola);
        }
    }

    private static void ejecutarMenu(Inventario inventario, Consola consola) {
        char opcion;

        do {
            mostrarMenu();
            opcion = consola.leerOpcion("Seleccione una opción: ");

            try {
                procesarOpcion(opcion, inventario, consola);
            } catch (DatoInvalidoException e) {
                // Las reglas del negocio llegan al menú como mensajes controlados.
                System.out.println("No se pudo completar la operación: " + e.getMessage());
            } finally {
                // Mantiene una separación visual después de cada operación.
                if (opcion != 'X') {
                    System.out.println();
                }
            }
        } while (opcion != 'X');

        System.out.println("Gracias por utilizar Gestión de Inventario Java.");
    }

    private static void mostrarMenu() {
        System.out.println("""

                GESTIÓN DE INVENTARIO JAVA
                --------------------------
                A. Mostrar inventario
                B. Registrar producto
                C. Buscar producto
                D. Modificar producto
                E. Eliminar producto
                F. Registrar venta
                G. Resumen por categoría
                X. Salir
                """);
    }

    private static void procesarOpcion(char opcion, Inventario inventario, Consola consola)
            throws DatoInvalidoException {
        switch (opcion) {
            case 'A' -> System.out.println(inventario.generarReporte());
            case 'B' -> registrarProducto(inventario, consola);
            case 'C' -> buscarProducto(inventario, consola);
            case 'D' -> modificarProducto(inventario, consola);
            case 'E' -> eliminarProducto(inventario, consola);
            case 'F' -> registrarVenta(inventario, consola);
            case 'G' -> System.out.println(inventario.generarResumenPorCategoria());
            case 'X' -> {
            }
            default -> System.out.println("La opción ingresada no existe.");
        }
    }

    private static void registrarProducto(Inventario inventario, Consola consola)
            throws DatoInvalidoException {
        String codigo = consola.leerTexto("Código: ");
        String nombre = consola.leerTexto("Nombre: ");
        String categoria = consola.leerTexto("Categoría: ");
        double precio = consola.leerDecimal("Precio: ");
        int stock = consola.leerEntero("Stock: ");

        inventario.agregar(new Producto(codigo, nombre, categoria, precio, stock));
        System.out.println("Producto registrado correctamente.");
    }

    private static void buscarProducto(Inventario inventario, Consola consola)
            throws DatoInvalidoException {
        Producto producto = obtenerProducto(inventario, consola);
        System.out.println(producto);
    }

    private static void modificarProducto(Inventario inventario, Consola consola)
            throws DatoInvalidoException {
        Producto producto = obtenerProducto(inventario, consola);

        System.out.println("A. Nombre");
        System.out.println("B. Categoría");
        System.out.println("C. Precio");
        System.out.println("D. Stock");
        char campo = consola.leerOpcion("Campo a modificar: ");

        switch (campo) {
            case 'A' -> producto.setNombre(consola.leerTexto("Nuevo nombre: "));
            case 'B' -> producto.setCategoria(consola.leerTexto("Nueva categoría: "));
            case 'C' -> producto.setPrecio(consola.leerDecimal("Nuevo precio: "));
            case 'D' -> producto.setStock(consola.leerEntero("Nuevo stock: "));
            default -> throw new DatoInvalidoException("El campo seleccionado no existe.");
        }
        System.out.println("Producto modificado correctamente.");
    }

    private static void eliminarProducto(Inventario inventario, Consola consola)
            throws DatoInvalidoException {
        String codigo = consola.leerTexto("Código a eliminar: ");
        inventario.eliminar(codigo);
        System.out.println("Producto eliminado correctamente.");
    }

    private static void registrarVenta(Inventario inventario, Consola consola)
            throws DatoInvalidoException {
        String codigo = consola.leerTexto("Código del producto: ");
        int unidades = consola.leerEntero("Cantidad: ");
        double total = inventario.vender(codigo, unidades);
        System.out.printf("Venta registrada. Total: S/ %.2f%n", total);
    }

    private static Producto obtenerProducto(Inventario inventario, Consola consola)
            throws DatoInvalidoException {
        String codigo = consola.leerTexto("Código: ");
        Producto producto = inventario.buscarPorCodigo(codigo);

        // Centraliza la validación usada por búsqueda y modificación.
        if (producto == null) {
            throw new DatoInvalidoException("No se encontró el producto.");
        }
        return producto;
    }
}
