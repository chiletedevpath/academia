package pe.com.utp.estructuras.matrices;

import pe.com.utp.modelo.persona.Doctor;

/*
 * Arreglo bidimensional para horarios de la clinica.
 * Cada fila representa un doctor y cada columna representa un dia laboral.
 */
public class MatrizHorarios {

    private static final int TOTAL_DOCTORES = 3;        // Tres doctores para la prueba.
    private static final int TOTAL_DIAS = 5;             // Cinco dias laborales.

    private int[][] matrizCitas;
    private Doctor[] doctoresAsignados;

    /*
     * Cada posicion de doctoresAsignados corresponde a una fila de matrizCitas.
     * Ejemplo: doctoresAsignados[1] trabaja con matrizCitas[1][...].
     */
    public MatrizHorarios(Doctor doctorFila1, Doctor doctorFila2, Doctor doctorFila3) {
        matrizCitas = new int[TOTAL_DOCTORES][TOTAL_DIAS];
        this.doctoresAsignados = new Doctor[TOTAL_DOCTORES];
        doctoresAsignados[0] = doctorFila1;
        doctoresAsignados[1] = doctorFila2;
        doctoresAsignados[2] = doctorFila3;
    }

    public int buscarFilaPorCodigoDoctor(String codigoDoctor) {
        // Se busca al doctor para conocer que fila ocupa en la matriz.
        for (int filaDoctor = 0; filaDoctor < doctoresAsignados.length; filaDoctor++) {
            if (doctoresAsignados[filaDoctor].getCodigo().equalsIgnoreCase(codigoDoctor)) {
                return filaDoctor;
            }
        }

        // -1 indica que el doctor no pertenece a esta agenda.
        return -1;
    }

    // Recorre la matriz por filas y columnas para mostrar sus valores.
    public void mostrarMatriz() {
        System.out.println("\n MATRIZ DE CITAS MEDICAS ");
        for (int filaDoctor = 0; filaDoctor < TOTAL_DOCTORES; filaDoctor++) {
            for (int columnaDia = 0; columnaDia < TOTAL_DIAS; columnaDia++) {
                System.out.print(matrizCitas[filaDoctor][columnaDia] + "\t");
            }
            System.out.println();
        }
    }

    // Recorre cada fila para calcular el total de citas por doctor.
    public void mostrarTotalPorDoctor() {
        System.out.println("\n TOTAL DE CITAS POR DOCTOR ");
        for (int filaDoctor = 0; filaDoctor < TOTAL_DOCTORES; filaDoctor++) {
            int totalPorDoctor = 0;
            for (int columnaDia = 0; columnaDia < TOTAL_DIAS; columnaDia++) {
                totalPorDoctor += matrizCitas[filaDoctor][columnaDia];
            }
            System.out.println("Doctor " + (filaDoctor + 1) + ": " + totalPorDoctor + " citas");
        }
    }

    // Recorre cada columna para calcular el total de citas por dia.
    public void mostrarTotalPorDia() {
        System.out.println("\n TOTAL DE CITAS POR DIA ");
        for (int columnaDia = 0; columnaDia < TOTAL_DIAS; columnaDia++) {
            int totalPorDia = 0;

            for (int filaDoctor = 0; filaDoctor < TOTAL_DOCTORES; filaDoctor++) {
                totalPorDia += matrizCitas[filaDoctor][columnaDia];
            }
            System.out.println("Dia " + (columnaDia + 1) + ": " + totalPorDia + " citas");
        }
    }

    public void registrarCitaEnMatriz(int filaDoctor, int columnaDia) {
        // Se valida la posicion antes de modificar una celda de la matriz.
        if (filaDoctor >= 0 && filaDoctor < TOTAL_DOCTORES && columnaDia >= 0 && columnaDia < TOTAL_DIAS) {
            matrizCitas[filaDoctor][columnaDia]++;
        } else {
            System.out.println("\n Error: indices de matriz fuera de rango.");
        }
    }

    public boolean esPosicionValida(int filaDoctor, int columnaDia) {
        return filaDoctor >= 0 && filaDoctor < TOTAL_DOCTORES && columnaDia >= 0 && columnaDia < TOTAL_DIAS;
    }
}
