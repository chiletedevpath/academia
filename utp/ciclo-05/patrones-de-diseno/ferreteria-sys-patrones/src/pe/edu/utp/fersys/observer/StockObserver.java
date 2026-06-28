package pe.edu.utp.fersys.observer;

import pe.edu.utp.fersys.modelo.producto.Producto;

// Patron Observer: contrato para reaccionar ante cambios de stock.
public interface StockObserver {

    // Notifica al observador el producto cuyo stock fue modificado.
    void actualizar(Producto producto);
}
