package pe.com.utp.interfaces;

import pe.com.utp.modelo.pago.Pago;

public interface Pagable {

    // Contrato base para clases que procesan pagos.
    void procesarPago(Pago pago);
}
