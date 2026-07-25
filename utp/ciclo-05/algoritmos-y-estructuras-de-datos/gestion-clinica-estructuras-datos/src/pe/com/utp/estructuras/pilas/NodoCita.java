package pe.com.utp.estructuras.pilas;

import pe.com.utp.modelo.cita.Cita;

/*
 * Nodo usado por la pila de citas recientes.
 * Guarda una cita y la referencia al nodo que queda debajo.
 */
public class NodoCita {

    // Cita almacenada dentro del nodo.
    private Cita cita;

    // Enlace hacia el nodo que queda debajo en la pila.
    private NodoCita siguiente;

    // Al crear el nodo, todavia no apunta a otro nodo.
    public NodoCita(Cita cita) {
        this.cita = cita;
        this.siguiente = null;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public NodoCita getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoCita siguiente) {
        this.siguiente = siguiente;
    }
}
