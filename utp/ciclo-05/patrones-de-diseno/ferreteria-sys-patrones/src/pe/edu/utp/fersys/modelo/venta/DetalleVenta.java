package pe.edu.utp.fersys.modelo.venta;

import pe.edu.utp.fersys.modelo.producto.Producto;

// GRASP Experto: DetalleVenta calcula su subtotal con cantidad y precio.
public class DetalleVenta {
    private Producto producto;
    private int cantidad;
    private double precioUnitario;

    // Conserva el precio unitario usado en el momento de la venta.
    public DetalleVenta(Producto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }

        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        }

        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.obtenerPrecio();
    }

    // Calcula el importe parcial de una sola linea del comprobante.
    public double calcularSubtotal() {
        return this.cantidad * this.precioUnitario;
    }

    // Devuelve el producto asociado al detalle.
    public Producto obtenerProducto() {
        return this.producto;
    }

    // Devuelve la cantidad vendida del producto.
    public int obtenerCantidad() {
        return this.cantidad;
    }

    // Devuelve el precio congelado al registrar la linea de venta.
    public double obtenerPrecioUnitario() {
        return this.precioUnitario;
    }
}
