package pe.com.utp.interfaces;

import pe.com.utp.modelo.cita.Cita;

/*
 * TAD para una lista enlazada simple de citas.
 * Define insercion, busqueda, recorrido y conteo de nodos.
 */
public interface TADListaCitas {

    void insertarCita(Cita cita);

    Cita buscarCitaPorCodigo(String codigo);

    void mostrarCitas();

    int contarCitas();
}
