package pe.edu.utp.inventario.excepciones;

public class DatoInvalidoException extends Exception {

    // Representa errores de validación que el menú puede comunicar al usuario.
    public DatoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
