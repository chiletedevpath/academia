package pe.edu.utp.fersys.observer;

import pe.edu.utp.fersys.modelo.producto.Producto;

// Observer concreto: emite alerta cuando un producto requiere reposicion.
public class AlertaStockObserver implements StockObserver {

    // Reacciona solo si el producto notificado llego al stock minimo.
    @Override
    public void actualizar(Producto producto) {
        if (producto.estaBajoStock()) {
            System.out.printf("ALERTA: %s llego al stock minimo. Stock actual: %d%n",
                    producto.obtenerNombre(),
                    producto.obtenerStock());
        }
    }
}
