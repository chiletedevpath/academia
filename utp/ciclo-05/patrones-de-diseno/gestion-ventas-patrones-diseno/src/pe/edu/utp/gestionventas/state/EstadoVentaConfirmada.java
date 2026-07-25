package pe.edu.utp.gestionventas.state;

import pe.edu.utp.gestionventas.modelo.venta.Venta;

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
        venta.restaurarStock();
        venta.cambiarEstado(new EstadoVentaAnulada());
        System.out.println("Venta anulada desde estado confirmado.");
    }

    // Una venta confirmada ya no debe modificar sus productos.
    @Override
    public boolean permiteAgregarProductos() {
        return false;
    }

    // Solo la venta confirmada queda lista para exportacion.
    @Override
    public boolean permiteExportar() {
        return true;
    }

    // Devuelve el identificador tecnico del estado concreto.
    @Override
    public String obtenerNombre() {
        return "CONFIRMADA";
    }
}
