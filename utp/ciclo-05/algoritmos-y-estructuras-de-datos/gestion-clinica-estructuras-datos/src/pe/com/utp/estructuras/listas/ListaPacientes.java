package pe.com.utp.estructuras.listas;

import pe.com.utp.interfaces.TADListaPacientes;
import pe.com.utp.modelo.persona.Paciente;

public class ListaPacientes implements TADListaPacientes {

    /*
     * Lista enlazada simple de pacientes.
     * Cada nodo guarda un paciente y una referencia al siguiente nodo.
     */

    // Primer nodo de la lista. Desde aqui empieza el recorrido.
    private NodoPaciente inicio;

    // Cantidad real de nodos guardados en la lista.
    private int tamanioListaPacientes;

    // La lista inicia sin nodos y con contador en cero.
    public ListaPacientes() {
        inicio = null;
        this.tamanioListaPacientes = 0;
    }

    // Inserta un paciente al final de la lista enlazada simple.
    @Override
    public void insertarPaciente(Paciente paciente) {

        // El paciente se guarda dentro de un nodo para poder enlazarlo.
        NodoPaciente nuevoNodoPaciente = new NodoPaciente(paciente);

        // Caso 1: si la lista esta vacia, el nuevo nodo sera el inicio.
        if (inicio == null) {
            inicio = nuevoNodoPaciente;
        } else {
            // Caso 2: si hay nodos, se avanza hasta el ultimo.
            NodoPaciente actual = inicio;

            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }

            // El ultimo nodo queda enlazado con el nuevo.
            actual.setSiguiente(nuevoNodoPaciente);
        }
        tamanioListaPacientes++;
        System.out.println("Paciente insertado correctamente en la lista");
    }

    // Busca un paciente por DNI mediante recorrido secuencial de nodos.
    @Override
    public Paciente buscarPacientePorDni(String dni) {

        // actual representa el nodo que se revisa en cada vuelta.
        NodoPaciente actual = inicio;

        while (actual != null) {

            // El DNI es el dato usado para comparar.
            if (actual.getPaciente().getDni().equals(dni)) {
                return actual.getPaciente();
            }

            // Si no coincide, se pasa al siguiente nodo.
            actual = actual.getSiguiente();
        }

        // Llegar a null significa que se recorrio toda la lista sin encontrarlo.
        return null;
    }

    // Elimina un paciente por DNI ajustando los enlaces de la lista.
    @Override
    public void eliminarPaciente(String dni) {

        // Sin nodos no existe nada que eliminar.
        if (estaVacia()) {
            System.out.println("La lista esta vacia");
            return;
        }

        // Caso especial: el paciente esta en el primer nodo.
        if (inicio.getPaciente().getDni().equals(dni)) {
            inicio = inicio.getSiguiente();
            tamanioListaPacientes--;
            System.out.println("Paciente eliminado correctamente");
            return;
        }

        // anterior queda detras de actual para poder cambiar el enlace.
        NodoPaciente actual = inicio;
        NodoPaciente anterior = null;

        // Se avanza hasta encontrar el DNI o terminar la lista.
        while (actual != null && !actual.getPaciente().getDni().equals(dni)) {
            anterior = actual;
            actual = actual.getSiguiente();
        }

        // Si actual es null, no hubo coincidencia.
        if (actual == null) {
            System.out.println("Paciente no encontrado");
            return;
        }

        // anterior salta al nodo eliminado y apunta al siguiente.
        anterior.setSiguiente(actual.getSiguiente());
        tamanioListaPacientes--;
        System.out.println("Paciente eliminado correctamente");
    }

    @Override
    public boolean estaVacia() {
        return inicio == null;
    }

    @Override
    public int contarPacientes() {
        return tamanioListaPacientes;
    }

    // Muestra los pacientes recorriendo la lista desde el inicio.
    @Override
    public void mostrarPacientes() {

        // Si no hay inicio, la lista esta vacia.
        if (estaVacia()) {
            System.out.println("La lista de pacientes esta vacia");
            return;
        }

        // Se avanza nodo por nodo hasta llegar a null.
        NodoPaciente actual = inicio;

        while (actual != null) {
            System.out.println();

            // Cada nodo contiene un paciente que sabe mostrar sus datos.
            actual.getPaciente().mostrarDatos();

            actual = actual.getSiguiente();
        }
    }
}
