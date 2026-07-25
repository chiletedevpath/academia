package pe.com.utp.estructuras.listas;

import pe.com.utp.modelo.persona.Paciente;

/*
 * Nodo usado para estructuras enlazadas de pacientes.
 * Guarda el paciente y la referencia al siguiente nodo.
 */
public class NodoPaciente {

    // Dato principal guardado en el nodo.
    private Paciente paciente;

    // Referencia al siguiente nodo enlazado.
    private NodoPaciente siguiente;

    // Al crearse, el nodo todavia no apunta a otro nodo.
    public NodoPaciente(Paciente paciente) {
        this.paciente = paciente;
        this.siguiente = null;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public NodoPaciente getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoPaciente siguiente) {
        this.siguiente = siguiente;
    }
}
