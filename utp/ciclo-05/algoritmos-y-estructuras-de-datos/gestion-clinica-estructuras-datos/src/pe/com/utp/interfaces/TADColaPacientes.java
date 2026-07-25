package pe.com.utp.interfaces;

import pe.com.utp.modelo.persona.Paciente;

/*
 * TAD para una cola dinamica de pacientes.
 * Define operaciones FIFO: encolar, desencolar, consultar frente y recorrer.
 */
public interface TADColaPacientes {

    void encolar(Paciente paciente);

    Paciente desencolar();

    Paciente verFrente();

    boolean estaVacia();

    void mostrarCola();

}
