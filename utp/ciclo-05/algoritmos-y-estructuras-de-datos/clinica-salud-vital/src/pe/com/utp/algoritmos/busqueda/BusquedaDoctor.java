package pe.com.utp.algoritmos.busqueda;

import pe.com.utp.modelo.persona.Doctor;

public class BusquedaDoctor {

    // Busqueda lineal de doctores usando el codigo como criterio.
    public Doctor buscarPorCodigo(Doctor[] doctores, int totalDoctores, String codigoDoctor) {
        for (int i = 0; i < totalDoctores; i++) {
            // compareToIgnoreCase no se usa aqui porque solo se necesita igualdad del codigo.
            if (doctores[i].getCodigo().equalsIgnoreCase(codigoDoctor)) {
                return doctores[i];
            }
        }

        // null permite que el Main controle el caso no encontrado sin detener el programa.
        return null;
    }
}
