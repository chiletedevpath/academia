package pe.com.utp.util;

public class GeneradorCodigo {

    // Genera codigos con prefijo y numeracion de tres digitos.
    public String generar(String prefijo, int numero) {
        return prefijo + "-" + String.format("%03d", numero);
    }
}
