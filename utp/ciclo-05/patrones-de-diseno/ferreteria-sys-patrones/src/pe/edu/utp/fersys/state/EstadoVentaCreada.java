package pe.edu.utp.fersys.state;

import pe.edu.utp.fersys.modelo.venta.Venta;

// State concreto: representa una venta registrada pero aun no cerrada.
public class EstadoVentaCreada implements EstadoVenta {

    // Cambia una venta creada al estado confirmado.
    @Override
    public void confirmar(Venta venta) {
        venta.cambiarEstado(new EstadoVentaConfirmada());
        System.out.println("Venta confirmada correctamente.");
    }

    // Cambia una venta creada al estado anulado.
    @Override
    public void anular(Venta venta) {
        venta.cambiarEstado(new EstadoVentaAnulada());
        System.out.println("Venta anulada correctamente.");
    }

    // Devuelve el identificador tecnico del estado concreto.
    @Override
    public String obtenerNombre() {
        return "CREADA";
    }
}
