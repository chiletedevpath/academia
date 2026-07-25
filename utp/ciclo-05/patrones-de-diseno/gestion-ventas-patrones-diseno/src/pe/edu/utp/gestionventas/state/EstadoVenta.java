package pe.edu.utp.gestionventas.state;

import pe.edu.utp.gestionventas.modelo.venta.Venta;

public interface EstadoVenta {

    // Confirma la venta segun las reglas del estado actual.
    void confirmar(Venta venta);

    // Anula la venta segun las reglas del estado actual.
    void anular(Venta venta);

    // Indica si el estado permite modificar productos de la venta.
    boolean permiteAgregarProductos();

    // Indica si el estado permite exportar la venta a facturacion.
    boolean permiteExportar();

    // Devuelve el nombre tecnico del estado para reportes y consola.
    String obtenerNombre();
}
