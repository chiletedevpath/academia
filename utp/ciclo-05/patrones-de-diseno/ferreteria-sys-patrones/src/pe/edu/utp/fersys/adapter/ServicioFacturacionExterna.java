package pe.edu.utp.fersys.adapter;

// Servicio externo simulado con una firma distinta a la usada por FerreSys.
public class ServicioFacturacionExterna {

    // Recibe datos planos como lo haria un sistema externo de facturacion.
    public void enviarDocumento(String codigoVenta, double total, String estado) {
        System.out.printf("Documento externo enviado: %s | Total: S/ %.2f | Estado: %s%n",
                codigoVenta, total, estado);
    }
}
