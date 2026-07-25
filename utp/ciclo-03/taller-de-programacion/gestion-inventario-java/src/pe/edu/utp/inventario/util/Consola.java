package pe.edu.utp.inventario.util;

import java.util.Scanner;

public class Consola implements AutoCloseable {

    // Se usa una sola instancia para evitar conflictos al leer System.in.
    private final Scanner scanner = new Scanner(System.in);

    public String leerTexto(String mensaje) {
        String valor;
        do {
            System.out.print(mensaje);
            valor = scanner.nextLine().trim();
            if (valor.isEmpty()) {
                System.out.println("El valor no puede estar vacío.");
            }
        } while (valor.isEmpty());
        return valor;
    }

    public int leerEntero(String mensaje) {
        // Leer primero como texto evita residuos al combinar nextInt y nextLine.
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    public double leerDecimal(String mensaje) {
        while (true) {
            try {
                // Acepta punto o coma como separador decimal.
                return Double.parseDouble(leerTexto(mensaje).replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número decimal válido.");
            }
        }
    }

    public char leerOpcion(String mensaje) {
        // El menú trabaja con una sola letra en mayúscula.
        return Character.toUpperCase(leerTexto(mensaje).charAt(0));
    }

    @Override
    public void close() {
        scanner.close();
    }
}
