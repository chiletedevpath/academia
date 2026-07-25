package pe.edu.utp.gestionventas.state;

import pe.edu.utp.gestionventas.modelo.venta.Venta;

// State concreto: representa una venta cancelada y sin nuevas transiciones.
public class EstadoVentaAnulada implements EstadoVenta {

    // Bloquea la confirmacion de una venta que ya fue anulada.
    @Override
    public void confirmar(Venta venta) {
        System.out.println("No se puede confirmar una venta anulada.");
    }

    // Evita anular nuevamente una venta que ya esta anulada.
    @Override
    public void anular(Venta venta) {
        System.out.println("La venta ya se encuentra anulada.");
    }

    // Una venta anulada queda cerrada para nuevos productos.
    @Override
    public boolean permiteAgregarProductos() {
        return false;
    }

    // Una venta anulada no debe exportarse como documento vigente.
    @Override
    public boolean permiteExportar() {
        return false;
    }

    // Devuelve el identificador tecnico del estado concreto.
    @Override
    public String obtenerNombre() {
        return "ANULADA";
    }
}
