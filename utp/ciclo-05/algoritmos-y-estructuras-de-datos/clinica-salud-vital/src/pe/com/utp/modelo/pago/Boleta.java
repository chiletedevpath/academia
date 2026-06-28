package pe.com.utp.modelo.pago;

import pe.com.utp.modelo.enums.TipoPago;

import java.time.LocalDate;

public class Boleta extends Pago {

    // DNI usado para emitir una boleta.
    private String dniCliente;

    public Boleta(String codigo, String codigoCita, double monto, TipoPago tipoPago, LocalDate fechaPago, String dniCliente) {
        super(codigo, codigoCita, monto, tipoPago, fechaPago);
        this.dniCliente = dniCliente;
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("DNI del cliente: " + dniCliente);
    }

    public String getDniCliente() {
        return dniCliente;
    }
}
