package pe.com.utp.servicios;

import pe.com.utp.estructuras.matrices.MatrizHorarios;
import pe.com.utp.modelo.cita.Cita;

public class CitaService {

    // Capacidad estatica del arreglo unidimensional de citas.
    private static final int CAPACIDAD_MAXIMA_CITAS = 20;

    // Arreglo usado para almacenar citas en memoria principal.
    private Cita[] citas;

    // Matriz bidimensional usada para contar citas por doctor y dia.
    private MatrizHorarios matrizHorarios;

    // Cantidad real de citas registradas.
    private int totalCitas;

    /*
     * La matriz llega configurada con sus doctores.
     * Asi las citas y los reportes de horarios trabajan sobre la misma agenda.
     */
    public CitaService(MatrizHorarios matrizHorarios) {
        this.citas = new Cita[CAPACIDAD_MAXIMA_CITAS];
        this.matrizHorarios = matrizHorarios;
        this.totalCitas = 0;
    }

    // Registra una cita en el arreglo y actualiza su posicion en la matriz.
    public void registrarCita(Cita nuevaCita, int columnaDia) {

        // El doctor de la cita determina la fila dentro de la matriz.
        String codigoDoctor = nuevaCita.getDoctor().getCodigo();
        int filaDoctor = matrizHorarios.buscarFilaPorCodigoDoctor(codigoDoctor);

        // Si el doctor no esta en la matriz, la cita no se registra.
        if (filaDoctor == -1) {
            System.out.println("\nMedico no asignado");
            return;
        }

        // Primero se valida que el arreglo todavia tenga espacio.
        if (totalCitas < CAPACIDAD_MAXIMA_CITAS) {

            if (matrizHorarios.esPosicionValida(filaDoctor, columnaDia - 1)) {
                citas[totalCitas] = nuevaCita;
                totalCitas++;

                // La matriz cuenta la cita en la fila del doctor y columna del dia.
                matrizHorarios.registrarCitaEnMatriz(filaDoctor, columnaDia - 1);
                System.out.println("\nCita registrada correctamente");
            } else {
                System.out.println("\nPosicion incorrecta");
            }
        } else {
            System.out.println("\nNo hay espacio para mas citas");
        }
    }

    public Cita buscarCitaPorCodigo(String codigoBuscado) {
        // Busqueda lineal sobre las citas realmente registradas.
        for (int i = 0; i < totalCitas; i++) {
            if (citas[i].getCodigo().equalsIgnoreCase(codigoBuscado)) {
                return citas[i];
            }
        }
        System.out.println("\nLa cita con codigo " + codigoBuscado + " no ha sido encontrada");
        return null;
    }

    /*
     * Cancela la cita encontrada cambiando solo su estado.
     * Por ahora no descuenta la cita dentro de la matriz de horarios.
     */
    public Cita cancelarCita(String codigoBuscado) {
        for (int i = 0; i < totalCitas; i++) {
            if (citas[i].getCodigo().equalsIgnoreCase(codigoBuscado)) {
                citas[i].cancelar();
                System.out.println("Su cita ha sido cancelada");
                return citas[i];
            }
        }

        System.out.println("No se encontro la cita con codigo: " + codigoBuscado);
        return null;
    }

    public void mostrarCitas() {
        if (totalCitas == 0) {
            System.out.println("No hay citas registradas");
            return;
        }

        for (int i = 0; i < totalCitas; i++) {
            citas[i].mostrarDatos();
            System.out.println("-------------------");
        }
    }

    // Reportes delegados a la matriz de horarios.
    public void mostrarMatrizHorarios() {
        matrizHorarios.mostrarMatriz();
    }

    public void mostrarTotalCitasPorDoctor() {
        matrizHorarios.mostrarTotalPorDoctor();
    }

    public void mostrarTotalCitasPorDia() {
        matrizHorarios.mostrarTotalPorDia();
    }

    public Cita[] getCitas() {
        return citas;
    }

    // Permite trabajar solo con las posiciones ocupadas del arreglo.
    public int getTotalCitas() {
        return totalCitas;
    }
}
