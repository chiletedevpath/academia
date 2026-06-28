package pe.edu.utp.fersys.controller;

import pe.edu.utp.fersys.adapter.ExportadorVenta;
import pe.edu.utp.fersys.builder.ComprobanteVenta;
import pe.edu.utp.fersys.builder.ComprobanteVentaBuilder;
import pe.edu.utp.fersys.command.ComandoVenta;
import pe.edu.utp.fersys.facade.VentaFacade;
import pe.edu.utp.fersys.modelo.producto.Producto;
import pe.edu.utp.fersys.modelo.venta.DetalleVenta;
import pe.edu.utp.fersys.modelo.venta.Venta;

// GRASP Controlador: coordina el caso de uso de venta sin cargar al Main.
public class VentaController {
    private Venta venta;
    private VentaFacade ventaFacade;
    private ExportadorVenta exportadorVenta;

    // Centraliza las dependencias necesarias para operar una venta.
    public VentaController(Venta venta, ExportadorVenta exportadorVenta) {
        if (venta == null) {
            throw new IllegalArgumentException("La venta no puede ser nula.");
        }

        this.venta = venta;
        this.ventaFacade = new VentaFacade(venta);
        this.exportadorVenta = exportadorVenta;
    }

    // Registra un producto delegando la validacion simple a la fachada.
    public boolean registrarProducto(Producto producto, int cantidad) {
        return ventaFacade.registrarProducto(producto, cantidad);
    }

    // Ejecuta una accion de venta encapsulada como Command.
    public void ejecutarComando(ComandoVenta comandoVenta) {
        if (comandoVenta == null) {
            throw new IllegalArgumentException("El comando no puede ser nulo.");
        }

        System.out.println("Comando ejecutado: " + comandoVenta.obtenerNombre());
        comandoVenta.ejecutar();
    }

    // Muestra el resumen comercial de la venta actual.
    public void mostrarResumen() {
        ventaFacade.mostrarResumen();
    }

    // Construye un comprobante con datos reales de la venta.
    public ComprobanteVenta generarComprobante() {
        return new ComprobanteVentaBuilder()
                .conEncabezado("FERRESYS - COMPROBANTE DE VENTA" + System.lineSeparator()
                        + "Venta: " + venta.obtenerIdVenta()
                        + System.lineSeparator() + "Estado: " + venta.obtenerEstado())
                .conDetalle(generarDetalleComprobante())
                .conTotales(String.format("Subtotal: S/ %.2f%nIGV: S/ %.2f%nTotal: S/ %.2f",
                        venta.calcularSubtotal(),
                        venta.calcularIgv(),
                        venta.calcularTotal()))
                .conMensajeFinal("Gracias por su compra.")
                .construir();
    }

    // Envia la venta usando el adaptador configurado.
    public void exportarVenta() {
        if (exportadorVenta != null) {
            exportadorVenta.exportar(venta);
        }
    }

    // Arma el detalle textual del comprobante desde las lineas de venta.
    private String generarDetalleComprobante() {
        StringBuilder detalle = new StringBuilder();

        for (DetalleVenta item : venta.obtenerDetalles()) {
            detalle.append(String.format("%s x %d | P.U. S/ %.2f | Subtotal S/ %.2f%n",
                    item.obtenerProducto().obtenerNombre(),
                    item.obtenerCantidad(),
                    item.obtenerPrecioUnitario(),
                    item.calcularSubtotal()));
        }

        return detalle.toString().trim();
    }
}
