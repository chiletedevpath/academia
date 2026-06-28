package pe.edu.utp.fersys.modelo.venta;

import pe.edu.utp.fersys.modelo.cliente.Cliente;
import pe.edu.utp.fersys.modelo.producto.Producto;
import pe.edu.utp.fersys.modelo.usuario.Usuario;
import pe.edu.utp.fersys.observer.StockObserver;
import pe.edu.utp.fersys.state.EstadoVenta;
import pe.edu.utp.fersys.state.EstadoVentaCreada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// GRASP Experto: Venta calcula importes porque conoce todos sus detalles.
public class Venta {
    private String idVenta;
    private Cliente cliente;
    private Usuario usuario;
    private List<DetalleVenta> detalles;
    private static final double PORCENTAJE_IGV = 0.18;
    private List<StockObserver> stockObservers;
    private EstadoVenta estadoVenta;

    // Crea una venta vacia asociada a un cliente y al usuario vendedor.
    public Venta(String idVenta, Cliente cliente, Usuario usuario) {
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.usuario = usuario;
        this.detalles = new ArrayList<>();
        this.stockObservers = new ArrayList<>();
        this.estadoVenta = new EstadoVentaCreada();
    }

    // State: reemplaza el comportamiento actual de la venta por otro estado.
    public void cambiarEstado(EstadoVenta nuevoEstado) {
        this.estadoVenta = nuevoEstado;
    }

    // State: expone el nombre tecnico del estado actual de la venta.
    public String obtenerEstado() {
        if (this.estadoVenta == null) {
            return "SIN_ESTADO";
        }
        return this.estadoVenta.obtenerNombre();
    }

    // State: delega la confirmacion al estado actual de la venta.
    public void confirmar() {
        this.estadoVenta.confirmar(this);
    }

    // State: delega la anulacion al estado actual de la venta.
    public void anular() {
        this.estadoVenta.anular(this);
    }

    // Devuelve el codigo interno que identifica la venta.
    public String obtenerIdVenta() {
        return this.idVenta;
    }

    // Expone una vista de solo lectura para reportes y comprobantes.
    public List<DetalleVenta> obtenerDetalles() {
        return Collections.unmodifiableList(detalles);
    }

    // Registra un detalle, descuenta stock y dispara notificaciones Observer.
    public boolean agregarDetalle(Producto producto, int cantidad) {
        if (producto == null || !producto.tieneStockDisponible(cantidad)) {
            return false;
        }

        producto.reducirStock(cantidad);
        notificarObservadores(producto);
        detalles.add(new DetalleVenta(producto, cantidad));
        return true;
    }

    // Calcula subtotal a partir de los detalles que componen la venta.
    public double calcularSubtotal() {
        double subtotal = 0;

        for (DetalleVenta detalle : detalles) {
            subtotal += detalle.calcularSubtotal();
        }

        return subtotal;
    }

    // Calcula el IGV aplicando el 18 por ciento al subtotal.
    public double calcularIgv() {
        return calcularSubtotal() * PORCENTAJE_IGV;
    }

    // Calcula el total final sumando subtotal e IGV.
    public double calcularTotal() {
        return calcularSubtotal() + calcularIgv();
    }

    // Imprime un resumen simple para validar la venta desde consola.
    public void mostrarResumenVenta() {
        System.out.println("Venta: " + idVenta);

        for (DetalleVenta detalle : detalles) {
            System.out.printf(
                    "%s x %d = S/ %.2f%n", detalle.obtenerProducto().obtenerNombre(),
                    detalle.obtenerCantidad(), detalle.calcularSubtotal());
        }

        System.out.printf("Subtotal: S/ %.2f%n", calcularSubtotal());
        System.out.printf("IGV: S/ %.2f%n", calcularIgv());
        System.out.printf("Total: S/ %.2f%n", calcularTotal());
    }

    // Suscribe observadores interesados en cambios de stock.
    public void agregarObservador(StockObserver observador) {
        if (observador != null) {
            stockObservers.add(observador);
        }
    }

    // Desacopla Venta de las acciones concretas ejecutadas por cada observer.
    private void notificarObservadores(Producto producto) {
        for (StockObserver observador : stockObservers) {
            observador.actualizar(producto);
        }
    }
}
