package pe.edu.utp.fersys.builder;

// Producto final del Builder: representa el comprobante ya construido.
public class ComprobanteVenta {
    private String encabezado;
    private String detalle;
    private String totales;
    private String mensajeFinal;

    // Recibe las partes armadas por el Builder y crea el comprobante final.
    public ComprobanteVenta(String encabezado, String detalle, String totales, String mensajeFinal) {
        this.encabezado = encabezado;
        this.detalle = detalle;
        this.totales = totales;
        this.mensajeFinal = mensajeFinal;
    }

    // Imprime el comprobante completo en el orden definido por el Builder.
    public void mostrar() {
        System.out.println(encabezado);
        System.out.println(detalle);
        System.out.println(totales);
        System.out.println(mensajeFinal);
    }
}
