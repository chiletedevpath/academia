package pe.com.utp.estructuras.listas;

import pe.com.utp.estructuras.pilas.NodoCita;
import pe.com.utp.interfaces.TADListaCitas;
import pe.com.utp.modelo.cita.Cita;

public class ListaCitas implements TADListaCitas {

    /*
     * Lista enlazada simple de citas.
     * Reutiliza nodos de cita para insertar, buscar y recorrer citas.
     */

    private NodoCita inicio;
    private int totalCitas;

    public ListaCitas() {
        this.inicio = null;
        this.totalCitas = 0;
    }

    // Inserta una cita al final de la lista.
    @Override
    public void insertarCita(Cita cita) {
        NodoCita nuevoNodo = new NodoCita(cita);

        if (inicio == null) {
            inicio = nuevoNodo;
        } else {
            NodoCita actual = inicio;

            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }

            actual.setSiguiente(nuevoNodo);
        }

        totalCitas++;
    }

    // Busca una cita por codigo mediante recorrido lineal de nodos.
    @Override
    public Cita buscarCitaPorCodigo(String codigo) {
        NodoCita actual = inicio;

        while (actual != null) {
            if (actual.getCita().getCodigo().equalsIgnoreCase(codigo)) {
                return actual.getCita();
            }

            actual = actual.getSiguiente();
        }

        return null;
    }

    @Override
    public void mostrarCitas() {
        if (inicio == null) {
            System.out.println("La lista de citas esta vacia");
            return;
        }

        NodoCita actual = inicio;

        while (actual != null) {
            System.out.println();
            actual.getCita().mostrarDatos();
            actual = actual.getSiguiente();
        }
    }

    @Override
    public int contarCitas() {
        return totalCitas;
    }
}
