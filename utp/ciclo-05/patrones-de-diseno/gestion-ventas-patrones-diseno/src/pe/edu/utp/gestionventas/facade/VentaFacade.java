package pe.edu.utp.gestionventas.facade;

import pe.edu.utp.gestionventas.adapter.ExportadorVenta;
import pe.edu.utp.gestionventas.builder.ComprobanteVenta;
import pe.edu.utp.gestionventas.builder.ComprobanteVentaBuilder;
import pe.edu.utp.gestionventas.command.ComandoVenta;
import pe.edu.utp.gestionventas.config.ConfiguracionSistema;
import pe.edu.utp.gestionventas.modelo.producto.Producto;
import pe.edu.utp.gestionventas.modelo.venta.DetalleVenta;
import pe.edu.utp.gestionventas.modelo.venta.Venta;

// Patron Facade: simplifica el subsistema de venta, comprobante y exportacion.
public class VentaFacade {

    private Venta venta;
    private ExportadorVenta exportadorVenta;

    // Inyecta la venta real que la fachada coordinara.
    public VentaFacade(Venta venta, ExportadorVenta exportadorVenta) {
        if (venta == null) {
            throw new IllegalArgumentException("La venta no puede ser nula.");
        }

        this.venta = venta;
        this.exportadorVenta = exportadorVenta;
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

    // Ejecuta una accion de venta encapsulada como Command.
    public void ejecutarComando(ComandoVenta comandoVenta) {
        if (comandoVenta == null) {
            throw new IllegalArgumentException("El comando no puede ser nulo.");
        }

        System.out.println("Comando ejecutado: " + comandoVenta.obtenerNombre());
        comandoVenta.ejecutar();
    }

    // Construye el comprobante de venta con datos reales del subsistema.
    public ComprobanteVenta generarComprobante() {
        String moneda = ConfiguracionSistema.obtenerInstancia().obtenerMoneda();

        return new ComprobanteVentaBuilder()
                .conEncabezado("GESTION DE VENTAS CON PATRONES - COMPROBANTE" + System.lineSeparator()
                        + "Venta: " + venta.obtenerIdVenta()
                        + System.lineSeparator() + "Estado: " + venta.obtenerEstado())
                .conDetalle(generarDetalleComprobante())
                .conTotales(String.format("Subtotal: %s %.2f%nIGV: %s %.2f%nTotal: %s %.2f",
                        moneda,
                        venta.calcularSubtotal(),
                        moneda,
                        venta.calcularIgv(),
                        moneda,
                        venta.calcularTotal()))
                .conMensajeFinal("Gracias por su compra.")
                .construir();
    }

    // Exporta solo ventas confirmadas para proteger el flujo comercial.
    public void exportarVenta() {
        if (exportadorVenta == null) {
            return;
        }

        if (!venta.permiteExportar()) {
            throw new IllegalStateException("Solo se pueden exportar ventas confirmadas.");
        }

        exportadorVenta.exportar(venta);
    }

    // Arma el detalle textual del comprobante desde las lineas de venta.
    private String generarDetalleComprobante() {
        StringBuilder detalle = new StringBuilder();
        String moneda = ConfiguracionSistema.obtenerInstancia().obtenerMoneda();

        for (DetalleVenta item : venta.obtenerDetalles()) {
            detalle.append(String.format("%s x %d | P.U. %s %.2f | Subtotal %s %.2f%n",
                    item.obtenerProducto().obtenerNombre(),
                    item.obtenerCantidad(),
                    moneda,
                    item.obtenerPrecioUnitario(),
                    moneda,
                    item.calcularSubtotal()));
        }

        return detalle.toString().trim();
    }
}
