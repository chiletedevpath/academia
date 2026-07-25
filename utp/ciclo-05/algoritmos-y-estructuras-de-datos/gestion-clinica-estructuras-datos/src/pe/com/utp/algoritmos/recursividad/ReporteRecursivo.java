package pe.com.utp.algoritmos.recursividad;

import pe.com.utp.modelo.cita.Cita;
import pe.com.utp.modelo.persona.Paciente;

public class ReporteRecursivo {

    /*
     * Reporte recursivo sobre arreglos.
     * Cada metodo tiene caso base y llamada recursiva para avanzar de posicion.
     */

    // Recorre pacientes sin usar estructuras repetitivas.
    public void mostrarPacientes(Paciente[] pacientes, int posicion, int totalPacientes) {
        if (posicion >= totalPacientes) {
            // Caso base: detiene la recursion cuando ya no quedan posiciones validas.
            return;
        }

        System.out.println();
        pacientes[posicion].mostrarDatos();

        // Llamada recursiva: avanza una posicion y repite el mismo proceso.
        mostrarPacientes(pacientes, posicion + 1, totalPacientes);
    }

    // Recorre citas sin usar estructuras repetitivas.
    public void mostrarCitas(Cita[] citas, int posicion, int totalCitas) {
        if (posicion >= totalCitas) {
            // Caso base: evita llamadas infinitas al llegar al total de citas.
            return;
        }

        System.out.println();
        citas[posicion].mostrarDatos();

        // La posicion cambia; el arreglo y el total se mantienen iguales.
        mostrarCitas(citas, posicion + 1, totalCitas);
    }

    // Cuenta elementos avanzando hasta el caso base.
    public int contarElementos(int posicion, int totalElementos) {
        if (posicion >= totalElementos) {
            // Al no quedar elementos, se retorna cero para cerrar la suma recursiva.
            return 0;
        }

        // Cada llamada cuenta el elemento actual y delega el resto al siguiente nivel.
        return 1 + contarElementos(posicion + 1, totalElementos);
    }
}
