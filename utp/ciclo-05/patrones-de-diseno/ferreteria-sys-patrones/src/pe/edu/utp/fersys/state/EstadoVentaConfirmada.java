package pe.edu.utp.fersys.state;

import pe.edu.utp.fersys.modelo.venta.Venta;

// State concreto: representa una venta validada y lista para cierre.
public class EstadoVentaConfirmada implements EstadoVenta {

    // Evita confirmar dos veces una venta que ya fue confirmada.
    @Override
    public void confirmar(Venta venta) {
        System.out.println("La venta ya se encuentra confirmada.");
    }

    // Permite anular una venta confirmada segun la regla del flujo actual.
    @Override
    public void anular(Venta venta) {
        venta.cambiarEstado(new EstadoVentaAnulada());
        System.out.println("Venta anulada desde estado confirmado.");
    }

    // Devuelve el identificador tecnico del estado concreto.
    @Override
    public String obtenerNombre() {
        return "CONFIRMADA";
    }
}
