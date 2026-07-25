package pe.edu.utp.gestionventas.builder;

// Patron Builder: construye un comprobante de venta por partes.
public class ComprobanteVentaBuilder {
    private String encabezado;
    private String detalle;
    private String totales;
    private String mensajeFinal;

    // Define la cabecera del comprobante.
    public ComprobanteVentaBuilder conEncabezado(String encabezado) {
        this.encabezado = encabezado;
        return this;
    }

    // Define el detalle de productos vendidos.
    public ComprobanteVentaBuilder conDetalle(String detalle) {
        this.detalle = detalle;
        return this;
    }

    // Define subtotal, IGV y total del comprobante.
    public ComprobanteVentaBuilder conTotales(String totales) {
        this.totales = totales;
        return this;
    }

    // Define el mensaje final para el cliente.
    public ComprobanteVentaBuilder conMensajeFinal(String mensajeFinal) {
        this.mensajeFinal = mensajeFinal;
        return this;
    }

    // Devuelve el comprobante final usando las partes configuradas.
    public ComprobanteVenta construir() {
        validarCampoObligatorio(encabezado, "El encabezado del comprobante es obligatorio.");
        validarCampoObligatorio(detalle, "El detalle del comprobante es obligatorio.");
        validarCampoObligatorio(totales, "Los totales del comprobante son obligatorios.");

        return new ComprobanteVenta(
                encabezado,
                detalle,
                totales,
                mensajeFinal
        );
    }

    // Protege la construccion para evitar comprobantes incompletos.
    private void validarCampoObligatorio(String valor, String mensaje) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalStateException(mensaje);
        }
    }
}
