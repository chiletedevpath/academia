package pe.edu.utp.gestionventas.command;

// Patron Command: encapsula una accion ejecutable sobre una venta.
public interface ComandoVenta {

    // Ejecuta la operacion concreta del comando.
    void ejecutar();

    // Devuelve el nombre tecnico del comando para trazabilidad.
    String obtenerNombre();
}
