package pe.edu.utp.gestionventas.adapter;

import pe.edu.utp.gestionventas.config.ConfiguracionSistema;

// Servicio externo simulado con una firma distinta a la usada por el sistema.
public class ServicioFacturacionExterna {

    // Recibe datos planos como lo haria un sistema externo de facturacion.
    public void enviarDocumento(String codigoVenta, double total, String estado) {
        System.out.printf("Documento externo enviado: %s | Total: %s %.2f | Estado: %s%n",
                codigoVenta, ConfiguracionSistema.obtenerInstancia().obtenerMoneda(), total, estado);
    }
}
