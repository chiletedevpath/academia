package pe.com.utp.algoritmos.ordenamiento;

import pe.com.utp.modelo.persona.Paciente;

public class BubbleSortPacientes {

    /*
     * Ordenamiento Bubble Sort aplicado a un arreglo de pacientes.
     * Compara elementos vecinos e intercambia cuando el apellido esta fuera de orden.
     */
    public void ordenarPorApellido(Paciente[] pacientes, int totalPacientes) {

        for (int i = 0; i < totalPacientes - 1; i++) {

            // En cada pasada, el apellido mayor queda al final del tramo evaluado.
            for (int j = 0; j < totalPacientes - 1 - i; j++) {

                // Se comparan dos posiciones vecinas del arreglo.
                String apellidoPacienteActual = pacientes[j].getApellidos();
                String apellidoPacienteSiguiente = pacientes[j + 1].getApellidos();

                // Si el actual debe ir despues del siguiente, se intercambian.
                if (apellidoPacienteActual.compareToIgnoreCase(apellidoPacienteSiguiente) > 0) {
                    Paciente auxiliar = pacientes[j];

                    pacientes[j] = pacientes[j + 1];
                    pacientes[j + 1] = auxiliar;
                }
            }
        }
    }
}
