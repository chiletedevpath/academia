package pe.com.utp.servicios;

import pe.com.utp.interfaces.Reportable;

public class ReporteService implements Reportable {

    /*
     * Servicio de reportes generales.
     * Centraliza resumenes simples del sistema para la exposicion.
     */

    public void mostrarResumenGeneral(int totalPacientes, int totalCitas, int totalPagos) {
        System.out.println("\n==== RESUMEN GENERAL DEL SISTEMA ====");
        System.out.println("Total de pacientes: " + totalPacientes);
        System.out.println("Total de citas: " + totalCitas);
        System.out.println("Total de pagos: " + totalPagos);
    }

    public void mostrarAvanceAED() {
        System.out.println("\n==== TEMAS AED APLICADOS ====");
        System.out.println("Arreglos unidimensionales");
        System.out.println("Arreglos bidimensionales");
        System.out.println("Listas enlazadas");
        System.out.println("Pilas y colas");
        System.out.println("Recursividad");
        System.out.println("Ordenamientos BubbleSort, QuickSort y MergeSort");
        System.out.println("Arbol binario de busqueda");
        System.out.println("POO: encapsulamiento, herencia, polimorfismo e interfaces");
        System.out.println("Persistencia simple en archivos CSV");
    }

    @Override
    public void generarReporte() {
        // POO: Reportable permite llamar el reporte mediante un contrato comun.
        mostrarAvanceAED();
    }
}
