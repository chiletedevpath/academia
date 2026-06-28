package pe.com.utp.estructuras.pilas;

import pe.com.utp.interfaces.TADPilaHistorialCitas;
import pe.com.utp.modelo.cita.Cita;

public class PilaHistorialCitas implements TADPilaHistorialCitas {

    /*
     * Pila dinamica de citas recientes implementada con nodos enlazados.
     * No representa orden de atencion; para eso se usa la cola de pacientes.
     * Se aplica LIFO: la ultima cita apilada queda disponible en la cima.
     */

    // Nodo superior de la pila.
    private NodoCita cima;

    // Cantidad real de citas dentro de la pila.
    private int tamanio;

    // La pila inicia vacia: no hay cima y el contador empieza en cero.
    public PilaHistorialCitas() {
        cima = null;
        tamanio = 0;
    }

    // Push: agrega una cita a la cima del historial reciente.
    @Override
    public void apilar(Cita cita) {
        if (cita == null) {
            return;
        }

        // La nueva cita queda envuelta en un nodo.
        NodoCita nodo = new NodoCita(cita);

        // El nuevo nodo apunta a la cima anterior.
        nodo.setSiguiente(cima);

        // La cima pasa a ser la cita mas reciente.
        cima = nodo;

        tamanio++;
        System.out.println("Cita " + cita.getCodigo() + " agregada al historial reciente");
    }

    // Pop: retira la cita ubicada en la cima de la pila.
    @Override
    public Cita desapilar() {
        if (estaVacia()) {
            System.out.println("La pila esta vacia");
            return null;
        }

        Cita citaRemovida = cima.getCita();

        // La cima baja al siguiente nodo enlazado.
        cima = cima.getSiguiente();
        tamanio--;

        System.out.println("Cita " + citaRemovida.getCodigo() + " retirada del historial reciente");
        return citaRemovida;
    }

    // Peek: consulta la cita mas reciente sin retirarla.
    @Override
    public Cita verCima() {
        if (estaVacia()) {
            System.out.println("La pila esta vacia");
            return null;
        }
        return cima.getCita();
    }

    // La pila esta vacia cuando no existe nodo en la cima.
    @Override
    public boolean estaVacia() {
        return cima == null;
    }

    // Recorre el historial reciente desde la cima hasta llegar a null.
    @Override
    public void mostrarPila() {
        if (estaVacia()) {
            System.out.println("La pila esta vacia");
            return;
        }

        NodoCita actual = cima;
        int posicion = 1;

        while (actual != null) {
            // Se muestra cada cita desde la mas reciente hacia la mas antigua.
            String codigo = actual.getCita().getCodigo();
            String estado = actual.getCita().getEstado().toString();
            System.out.println(posicion + ". " + codigo + " - " + estado);
            actual = actual.getSiguiente();
            posicion++;
        }
    }
}
