package pe.com.utp.interfaces;

import pe.com.utp.modelo.persona.Paciente;

/*
 * TAD para una lista enlazada simple de pacientes.
 * Define insercion, busqueda, eliminacion, recorrido y conteo de nodos.
 */
public interface TADListaPacientes {

    void insertarPaciente(Paciente paciente);

    Paciente buscarPacientePorDni(String dni);

    void eliminarPaciente(String dni);

    boolean estaVacia();

    int contarPacientes();

    void mostrarPacientes();
}
