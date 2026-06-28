package pe.edu.utp.fersys.facade;

import pe.edu.utp.fersys.modelo.producto.Producto;
import pe.edu.utp.fersys.modelo.venta.Venta;

// Patron Facade: expone operaciones simples para registrar y resumir ventas.
public class VentaFacade {

    private Venta venta;

    // Inyecta la venta real que la fachada coordinara.
    public VentaFacade(Venta venta) {
        if (venta == null) {
            throw new IllegalArgumentException("La venta no puede ser nula.");
        }

        this.venta = venta;
    }

    // Oculta la validacion de stock y delega el registro al modelo Venta.
    public boolean registrarProducto(Producto producto, int cantidad) {
        if (producto == null) {
            return false;
        }

        if (cantidad <= 0) {
            return false;
        }

        return venta.agregarDetalle(producto, cantidad);
    }

    // Reduce el acoplamiento del Main con los detalles internos de Venta.
    public void mostrarResumen() {
        venta.mostrarResumenVenta();
    }
}
