package pe.edu.utp.fersys.adapter;

import pe.edu.utp.fersys.modelo.venta.Venta;

// Adapter concreto: traduce una Venta al formato del servicio externo.
public class FacturacionExternaAdapter implements ExportadorVenta {
    private ServicioFacturacionExterna servicioExterno;

    // Inyecta el servicio incompatible que sera adaptado.
    public FacturacionExternaAdapter(ServicioFacturacionExterna servicioExterno) {
        if (servicioExterno == null) {
            throw new IllegalArgumentException("El servicio externo no puede ser nulo.");
        }

        this.servicioExterno = servicioExterno;
    }

    // Convierte el objeto Venta en los parametros que espera el servicio externo.
    @Override
    public void exportar(Venta venta) {
        servicioExterno.enviarDocumento(
                venta.obtenerIdVenta(),
                venta.calcularTotal(),
                venta.obtenerEstado()
        );
    }
}
