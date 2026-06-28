package pe.com.utp.algoritmos.busqueda;

import pe.com.utp.modelo.persona.Paciente;

public class BusquedaPaciente {

    // Busqueda lineal de pacientes usando el DNI como criterio.
    public Paciente buscarPorDni(Paciente[] pacientes, int totalPacientes, String dni) {
        for (int i = 0; i < totalPacientes; i++) {
            // AED: se revisa una posicion a la vez hasta encontrar coincidencia.
            if (pacientes[i].getDni().equals(dni)) {
                return pacientes[i];
            }
        }

        // return null indica que se recorrio todo el arreglo sin encontrar el DNI.
        return null;
    }
}
