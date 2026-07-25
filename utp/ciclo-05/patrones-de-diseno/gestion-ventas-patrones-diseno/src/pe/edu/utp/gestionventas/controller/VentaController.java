package pe.edu.utp.gestionventas.controller;

import pe.edu.utp.gestionventas.adapter.ExportadorVenta;
import pe.edu.utp.gestionventas.builder.ComprobanteVenta;
import pe.edu.utp.gestionventas.command.ComandoVenta;
import pe.edu.utp.gestionventas.facade.VentaFacade;
import pe.edu.utp.gestionventas.modelo.producto.Producto;
import pe.edu.utp.gestionventas.modelo.venta.Venta;

// GRASP Controlador: coordina el caso de uso de venta sin cargar al Main.
public class VentaController {
    private VentaFacade ventaFacade;

    // Centraliza las dependencias necesarias para operar una venta.
    public VentaController(Venta venta, ExportadorVenta exportadorVenta) {
        if (venta == null) {
            throw new IllegalArgumentException("La venta no puede ser nula.");
        }

        this.ventaFacade = new VentaFacade(venta, exportadorVenta);
    }

    // Registra un producto delegando la validacion simple a la fachada.
    public boolean registrarProducto(Producto producto, int cantidad) {
        return ventaFacade.registrarProducto(producto, cantidad);
    }

    // Ejecuta una accion de venta encapsulada como Command.
    public void ejecutarComando(ComandoVenta comandoVenta) {
        ventaFacade.ejecutarComando(comandoVenta);
    }

    // Muestra el resumen comercial de la venta actual.
    public void mostrarResumen() {
        ventaFacade.mostrarResumen();
    }

    // Construye un comprobante con datos reales de la venta.
    public ComprobanteVenta generarComprobante() {
        return ventaFacade.generarComprobante();
    }

    // Envia la venta usando el adaptador configurado.
    public void exportarVenta() {
        ventaFacade.exportarVenta();
    }
}
