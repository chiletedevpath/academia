package pe.edu.utp.fersys.state;

import pe.edu.utp.fersys.modelo.venta.Venta;

public interface EstadoVenta {

    // Confirma la venta segun las reglas del estado actual.
    void confirmar(Venta venta);

    // Anula la venta segun las reglas del estado actual.
    void anular(Venta venta);

    // Devuelve el nombre tecnico del estado para reportes y consola.
    String obtenerNombre();
}
