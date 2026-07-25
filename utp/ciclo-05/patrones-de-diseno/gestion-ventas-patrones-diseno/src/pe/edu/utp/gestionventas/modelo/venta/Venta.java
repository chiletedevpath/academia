package pe.edu.utp.gestionventas.modelo.venta;

import pe.edu.utp.gestionventas.config.ConfiguracionSistema;
import pe.edu.utp.gestionventas.modelo.cliente.Cliente;
import pe.edu.utp.gestionventas.modelo.producto.Producto;
import pe.edu.utp.gestionventas.modelo.usuario.Usuario;
import pe.edu.utp.gestionventas.observer.StockObserver;
import pe.edu.utp.gestionventas.state.EstadoVenta;
import pe.edu.utp.gestionventas.state.EstadoVentaCreada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// GRASP Experto: Venta calcula importes porque conoce todos sus detalles.
public class Venta {
    private String idVenta;
    private Cliente cliente;
    private Usuario usuario;
    private List<DetalleVenta> detalles;
    private List<StockObserver> stockObservers;
    private EstadoVenta estadoVenta;
    private boolean stockRestaurado;

    // Crea una venta vacia asociada a un cliente y al usuario vendedor.
    public Venta(String idVenta, Cliente cliente, Usuario usuario) {
        if (idVenta == null || idVenta.isBlank()) {
            throw new IllegalArgumentException("El id de venta es obligatorio.");
        }

        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }

        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo.");
        }

        this.idVenta = idVenta;
        this.cliente = cliente;
        this.usuario = usuario;
        this.detalles = new ArrayList<>();
        this.stockObservers = new ArrayList<>();
        this.estadoVenta = new EstadoVentaCreada();
        this.stockRestaurado = false;
    }

    // State: reemplaza el comportamiento actual de la venta por otro estado.
    public void cambiarEstado(EstadoVenta nuevoEstado) {
        if (nuevoEstado == null) {
            throw new IllegalArgumentException("El nuevo estado no puede ser nulo.");
        }

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

    // Indica si la venta posee productos registrados para poder confirmarse.
    public boolean tieneDetalles() {
        return !detalles.isEmpty();
    }

    // State: consulta al estado si la venta puede recibir productos.
    public boolean permiteAgregarProductos() {
        return estadoVenta.permiteAgregarProductos();
    }

    // State: consulta al estado si la venta puede exportarse.
    public boolean permiteExportar() {
        return estadoVenta.permiteExportar();
    }

    // Registra un detalle, descuenta stock y dispara notificaciones Observer.
    public boolean agregarDetalle(Producto producto, int cantidad) {
        if (!permiteAgregarProductos()) {
            System.out.println("No se pueden agregar productos a una venta en estado " + obtenerEstado() + ".");
            return false;
        }

        if (producto == null || !producto.tieneStockDisponible(cantidad)) {
            return false;
        }

        producto.reducirStock(cantidad);
        notificarObservadores(producto);
        detalles.add(new DetalleVenta(producto, cantidad));
        return true;
    }

    // Revierte el stock descontado por los detalles registrados en la venta.
    public void restaurarStock() {
        if (stockRestaurado) {
            return;
        }

        for (DetalleVenta detalle : detalles) {
            detalle.obtenerProducto().aumentarStock(detalle.obtenerCantidad());
        }

        stockRestaurado = true;
    }

    // Calcula subtotal a partir de los detalles que componen la venta.
    public double calcularSubtotal() {
        double subtotal = 0;

        for (DetalleVenta detalle : detalles) {
            subtotal += detalle.calcularSubtotal();
        }

        return subtotal;
    }

    // Calcula el IGV usando la configuracion central del sistema.
    public double calcularIgv() {
        return calcularSubtotal() * ConfiguracionSistema.obtenerInstancia().obtenerIgv();
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
                    "%s x %d = %s %.2f%n", detalle.obtenerProducto().obtenerNombre(),
                    detalle.obtenerCantidad(), ConfiguracionSistema.obtenerInstancia().obtenerMoneda(),
                    detalle.calcularSubtotal());
        }

        System.out.printf("Subtotal: %s %.2f%n",
                ConfiguracionSistema.obtenerInstancia().obtenerMoneda(), calcularSubtotal());
        System.out.printf("IGV: %s %.2f%n",
                ConfiguracionSistema.obtenerInstancia().obtenerMoneda(), calcularIgv());
        System.out.printf("Total: %s %.2f%n",
                ConfiguracionSistema.obtenerInstancia().obtenerMoneda(), calcularTotal());
    }

    // Suscribe observadores interesados en cambios de stock.
    public void agregarObservador(StockObserver observador) {
        if (observador != null && !stockObservers.contains(observador)) {
            stockObservers.add(observador);
        }
    }

    // Retira un observador para evitar notificaciones posteriores.
    public void quitarObservador(StockObserver observador) {
        stockObservers.remove(observador);
    }

    // Desacopla Venta de las acciones concretas ejecutadas por cada observer.
    private void notificarObservadores(Producto producto) {
        for (StockObserver observador : stockObservers) {
            observador.actualizar(producto);
        }
    }
}
