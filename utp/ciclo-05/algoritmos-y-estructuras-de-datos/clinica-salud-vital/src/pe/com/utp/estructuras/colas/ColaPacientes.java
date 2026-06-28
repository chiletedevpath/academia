package pe.com.utp.estructuras.colas;

import pe.com.utp.estructuras.listas.NodoPaciente;
import pe.com.utp.interfaces.TADColaPacientes;
import pe.com.utp.modelo.persona.Paciente;

public class ColaPacientes implements TADColaPacientes {

    /*
     * Cola dinamica de pacientes implementada con nodos enlazados.
     * Se aplica FIFO: el primer paciente que ingresa es el primero en salir.
     */

    // Primer nodo de la cola. Representa al paciente que sera atendido primero.
    private NodoPaciente frente;

    // Ultimo nodo de la cola. Permite agregar pacientes sin recorrer toda la estructura.
    private NodoPaciente fin;

    // Cantidad real de pacientes dentro de la cola.
    private int tamanio;

    // La cola inicia vacia: no hay frente, no hay fin y el contador empieza en cero.
    public ColaPacientes() {
        this.frente = null;
        this.fin = null;
        this.tamanio = 0;
    }

    // Enqueue: agrega un paciente al final de la cola.
    @Override
    public void encolar(Paciente paciente) {
        // Si no llega un paciente valido, no se modifica la estructura.
        if (paciente == null) {
            return;
        }

        // Cada paciente se guarda dentro de un nodo para poder enlazarlo.
        NodoPaciente nodoPaciente = new NodoPaciente(paciente);

        if (estaVacia()) {
            // Caso 1: primer paciente. Frente y fin apuntan al mismo nodo.
            frente = nodoPaciente;
            fin = nodoPaciente;
        } else {
            // Caso 2: cola con datos. El ultimo nodo apunta al nuevo nodo.
            fin.setSiguiente(nodoPaciente);

            // El nuevo nodo pasa a ser el ultimo de la cola.
            fin = nodoPaciente;
        }

        tamanio++;
        System.out.println("Paciente " + paciente.getNombres() + " en cola de espera");
    }

    // Dequeue: retira al paciente ubicado en el frente de la cola.
    @Override
    public Paciente desencolar() {
        if (estaVacia()) {
            System.out.println("Cola de espera vacia.");
            return null;
        }

        // Se guarda el paciente antes de mover el frente al siguiente nodo.
        Paciente pacienteAtendido = frente.getPaciente();

        // El frente avanza. Asi se pierde el acceso directo al nodo atendido.
        frente = frente.getSiguiente();

        // Si ya no queda ningun nodo, fin tambien debe quedar en null.
        if (frente == null) {
            fin = null;
        }

        tamanio--;
        System.out.println("Paciente " + pacienteAtendido.getNombres() + " ha salido de la cola");
        return pacienteAtendido;
    }

    // Consulta el paciente del frente sin retirarlo de la cola.
    @Override
    public Paciente verFrente() {
        if (estaVacia()) {
            return null;
        }
        return frente.getPaciente();
    }

    // La cola esta vacia cuando no existe nodo al frente.
    @Override
    public boolean estaVacia() {
        return frente == null;
    }


    // Recorre la cola desde el frente hasta llegar al ultimo nodo.
    @Override
    public void mostrarCola() {
        if (estaVacia()) {
            System.out.println("Cola de espera vacia");
            return;
        }

        // Nodo auxiliar usado para recorrer sin mover el frente real de la cola.
        NodoPaciente actual = frente;
        int posicion = 1;

        while (actual != null) {
            // Se muestra el paciente almacenado en el nodo actual.
            String nombre = actual.getPaciente().getNombres();
            System.out.println(posicion + ". " + nombre);

            // Avanza al siguiente nodo enlazado.
            actual = actual.getSiguiente();
            posicion++;
        }
    }
}
