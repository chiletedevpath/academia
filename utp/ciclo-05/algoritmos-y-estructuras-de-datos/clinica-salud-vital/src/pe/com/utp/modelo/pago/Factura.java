package pe.com.utp.modelo.pago;

import pe.com.utp.modelo.enums.TipoPago;

import java.time.LocalDate;

public class Factura extends Pago {

    // RUC usado para emitir una factura.
    private String rucCliente;

    public Factura(String codigo, String codigoCita, double monto, TipoPago tipoPago, LocalDate fechaPago, String rucCliente) {
        super(codigo, codigoCita, monto, tipoPago, fechaPago);
        this.rucCliente = rucCliente;
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("RUC del cliente: " + rucCliente);
    }

    public String getRucCliente() {
        return rucCliente;
    }
}
