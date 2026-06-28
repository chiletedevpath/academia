package pe.edu.utp.fersys.adapter;

import pe.edu.utp.fersys.modelo.venta.Venta;

// Patron Adapter: contrato esperado por FerreSys para exportar ventas.
public interface ExportadorVenta {

    // Exporta una venta usando el formato interno del sistema.
    void exportar(Venta venta);
}
