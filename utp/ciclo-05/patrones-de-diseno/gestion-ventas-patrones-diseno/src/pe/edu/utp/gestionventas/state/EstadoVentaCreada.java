package pe.edu.utp.gestionventas.state;

import pe.edu.utp.gestionventas.modelo.venta.Venta;

// State concreto: representa una venta registrada pero aun no cerrada.
public class EstadoVentaCreada implements EstadoVenta {

    // Cambia una venta creada al estado confirmado.
    @Override
    public void confirmar(Venta venta) {
        if (!venta.tieneDetalles()) {
            System.out.println("No se puede confirmar una venta sin productos.");
            return;
        }

        venta.cambiarEstado(new EstadoVentaConfirmada());
        System.out.println("Venta confirmada correctamente.");
    }

    // Cambia una venta creada al estado anulado.
    @Override
    public void anular(Venta venta) {
        venta.restaurarStock();
        venta.cambiarEstado(new EstadoVentaAnulada());
        System.out.println("Venta anulada correctamente.");
    }

    // Una venta creada aun puede recibir productos.
    @Override
    public boolean permiteAgregarProductos() {
        return true;
    }

    // Una venta creada todavia no debe exportarse.
    @Override
    public boolean permiteExportar() {
        return false;
    }

    // Devuelve el identificador tecnico del estado concreto.
    @Override
    public String obtenerNombre() {
        return "CREADA";
    }
}
