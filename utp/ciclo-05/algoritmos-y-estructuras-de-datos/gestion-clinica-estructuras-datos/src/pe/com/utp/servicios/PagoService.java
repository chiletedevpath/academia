package pe.com.utp.servicios;

import pe.com.utp.interfaces.Pagable;
import pe.com.utp.modelo.pago.Pago;

public class PagoService implements Pagable {

    // Capacidad estatica del arreglo unidimensional de pagos.
    private static final int CAPACIDAD_MAXIMA_PAGOS = 50;

    // Arreglo usado para almacenar pagos en memoria principal.
    private Pago[] pagos;

    // Cantidad real de pagos registrados.
    private int totalPagos;

    public PagoService() {
        this.pagos = new Pago[CAPACIDAD_MAXIMA_PAGOS];
        this.totalPagos = 0;
    }

    // Inserta un pago en la siguiente posicion libre del arreglo.
    public void registrarPago(Pago pago) {
        if (totalPagos >= CAPACIDAD_MAXIMA_PAGOS) {
            System.out.println("No hay espacio disponible para registrar mas pagos");
            return;
        }

        // POO: polimorfismo, aqui puede llegar Pago, Boleta o Factura.
        pagos[totalPagos] = pago;
        totalPagos++;

        System.out.println("Pago registrado correctamente");
    }

    @Override
    public void procesarPago(Pago pago) {
        // POO: Pagable define el contrato y PagoService decide como procesarlo.
        registrarPago(pago);
    }

    // Muestra solo las posiciones ocupadas del arreglo.
    public void mostrarPagos() {
        if (totalPagos == 0) {
            System.out.println("No existen pagos registrados");
            return;
        }

        for (int i = 0; i < totalPagos; i++) {
            System.out.println();
            // POO: se ejecuta el mostrarDatos real del objeto guardado.
            pagos[i].mostrarDatos();
        }
    }

    public Pago[] getPagos() {
        return pagos;
    }

    // Permite trabajar solo con las posiciones ocupadas del arreglo.
    public int getTotalPagos() {
        return totalPagos;
    }
}
