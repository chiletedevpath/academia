package pe.com.utp.util;

public class Validador {

    // Verifica que un texto no sea nulo ni vacio.
    public boolean esTextoValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    // Verifica que un monto sea mayor que cero.
    public boolean esMontoValido(double monto) {
        return monto > 0;
    }

    // Valida un DNI peruano basico de 8 digitos.
    public boolean esDniValido(String dni) {
        return dni != null && dni.matches("\\d{8}");
    }
}
