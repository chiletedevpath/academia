package pe.edu.utp.gestionventas.adapter;

import pe.edu.utp.gestionventas.modelo.venta.Venta;

// Patron Adapter: contrato esperado por el sistema para exportar ventas.
public interface ExportadorVenta {

    // Exporta una venta usando el formato interno del sistema.
    void exportar(Venta venta);
}
