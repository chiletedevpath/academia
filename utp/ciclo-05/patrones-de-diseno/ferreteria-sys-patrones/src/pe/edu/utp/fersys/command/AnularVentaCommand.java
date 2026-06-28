package pe.edu.utp.fersys.command;

import pe.edu.utp.fersys.modelo.venta.Venta;

// Command concreto: encapsula la anulacion de una venta.
public class AnularVentaCommand implements ComandoVenta {
    private Venta venta;

    // Recibe la venta que actuara como receptor del comando.
    public AnularVentaCommand(Venta venta) {
        if (venta == null) {
            throw new IllegalArgumentException("La venta no puede ser nula.");
        }

        this.venta = venta;
    }

    // Delega la accion al receptor sin exponerlo al cliente.
    @Override
    public void ejecutar() {
        venta.anular();
    }

    // Identifica el comando ejecutado en consola o auditoria.
    @Override
    public String obtenerNombre() {
        return "ANULAR_VENTA";
    }
}
