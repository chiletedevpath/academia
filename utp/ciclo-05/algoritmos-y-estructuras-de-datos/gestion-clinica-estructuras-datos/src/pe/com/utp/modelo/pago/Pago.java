package pe.com.utp.modelo.pago;

import pe.com.utp.modelo.enums.TipoPago;

import java.time.LocalDate;

public class Pago {

    // Codigo usado para identificar el pago.
    private String codigo;

    // Codigo de la cita asociada al pago.
    private String codigoCita;

    // Importe economico del pago.
    private double monto;

    // Medio usado para realizar el pago.
    private TipoPago tipoPago;

    // Fecha en que se registra el pago.
    private LocalDate fechaPago;

    public Pago(String codigo, String codigoCita, double monto, TipoPago tipoPago, LocalDate fechaPago) {
        this.codigo = codigo;
        this.codigoCita = codigoCita;
        this.monto = monto;
        this.tipoPago = tipoPago;
        this.fechaPago = fechaPago;
    }

    public void mostrarDatos() {
        System.out.println("----- DATOS DEL PAGO -----");
        System.out.println("Codigo: " + codigo);
        System.out.println("Codigo de cita: " + codigoCita);
        System.out.println("Monto: S/ " + monto);
        System.out.println("Tipo de pago: " + tipoPago);
        System.out.println("Fecha de pago: " + fechaPago);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(String codigoCita) {
        this.codigoCita = codigoCita;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }
}
