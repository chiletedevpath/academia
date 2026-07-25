package pe.com.utp.estructuras.arboles;

import pe.com.utp.interfaces.TADArbolDoctores;
import pe.com.utp.modelo.persona.Doctor;

public class ArbolDoctoresBusqueda implements TADArbolDoctores {

    /*
     * Arbol binario de busqueda aplicado a doctores.
     * El codigo del doctor define la ubicacion de cada nodo.
     * Valores menores van a la izquierda y mayores a la derecha.
     */

    // Nodo raiz desde donde inicia toda operacion del arbol.
    private NodoDoctor raiz;

    public ArbolDoctoresBusqueda() {
        this.raiz = null;
    }

    // Inserta un doctor aplicando recursion sobre subarboles.
    @Override
    public void insertarDoctor(Doctor doctor) {
        if (doctor == null) {
            return;
        }

        raiz = insertarRecursivo(raiz, doctor);
        System.out.println("Doctor " + doctor.getCodigo() + " insertado en el arbol");
    }

    private NodoDoctor insertarRecursivo(NodoDoctor actual, Doctor doctor) {
        if (actual == null) {
            // Caso base: se encontro el lugar donde debe crearse el nuevo nodo.
            return new NodoDoctor(doctor);
        }

        // compareToIgnoreCase compara codigos sin distinguir mayusculas y minusculas.
        int comparacion = doctor.getCodigo().compareToIgnoreCase(actual.getDoctor().getCodigo());

        if (comparacion < 0) {
            // Codigo menor: el doctor pertenece al subarbol izquierdo.
            actual.setIzquierdo(insertarRecursivo(actual.getIzquierdo(), doctor));
        } else if (comparacion > 0) {
            // Codigo mayor: el doctor pertenece al subarbol derecho.
            actual.setDerecho(insertarRecursivo(actual.getDerecho(), doctor));
        }

        return actual;
    }

    // Busca un doctor reduciendo el recorrido segun el codigo comparado.
    @Override
    public Doctor buscarDoctorPorCodigo(String codigoDoctor) {
        NodoDoctor nodoEncontrado = buscarRecursivo(raiz, codigoDoctor);

        if (nodoEncontrado == null) {
            return null;
        }

        return nodoEncontrado.getDoctor();
    }

    private NodoDoctor buscarRecursivo(NodoDoctor actual, String codigoDoctor) {
        if (actual == null || codigoDoctor == null) {
            // return null indica que no existe coincidencia en esta rama.
            return null;
        }

        int comparacion = codigoDoctor.compareToIgnoreCase(actual.getDoctor().getCodigo());

        if (comparacion == 0) {
            return actual;
        }

        if (comparacion < 0) {
            // Si el codigo buscado es menor, no hace falta revisar el lado derecho.
            return buscarRecursivo(actual.getIzquierdo(), codigoDoctor);
        }

        // Si el codigo buscado es mayor, se continua por el subarbol derecho.
        return buscarRecursivo(actual.getDerecho(), codigoDoctor);
    }

    // Elimina un doctor manteniendo la propiedad del arbol de busqueda.
    @Override
    public void eliminarDoctorPorCodigo(String codigoDoctor) {
        if (buscarDoctorPorCodigo(codigoDoctor) == null) {
            System.out.println("Doctor no encontrado en el arbol");
            return;
        }

        raiz = eliminarRecursivo(raiz, codigoDoctor);
        System.out.println("Doctor " + codigoDoctor + " eliminado del arbol");
    }

    private NodoDoctor eliminarRecursivo(NodoDoctor actual, String codigoDoctor) {
        if (actual == null) {
            return null;
        }

        int comparacion = codigoDoctor.compareToIgnoreCase(actual.getDoctor().getCodigo());

        if (comparacion < 0) {
            actual.setIzquierdo(eliminarRecursivo(actual.getIzquierdo(), codigoDoctor));
        } else if (comparacion > 0) {
            actual.setDerecho(eliminarRecursivo(actual.getDerecho(), codigoDoctor));
        } else {
            if (actual.getIzquierdo() == null) {
                // Caso 1: sin hijo izquierdo, el hijo derecho reemplaza al nodo.
                return actual.getDerecho();
            }

            if (actual.getDerecho() == null) {
                // Caso 2: sin hijo derecho, el hijo izquierdo reemplaza al nodo.
                return actual.getIzquierdo();
            }

            // Caso 3: dos hijos, se reemplaza por el menor del subarbol derecho.
            NodoDoctor sucesor = buscarMenor(actual.getDerecho());
            actual.setDoctor(sucesor.getDoctor());
            actual.setDerecho(eliminarRecursivo(actual.getDerecho(), sucesor.getDoctor().getCodigo()));
        }

        return actual;
    }

    private NodoDoctor buscarMenor(NodoDoctor actual) {
        while (actual.getIzquierdo() != null) {
            actual = actual.getIzquierdo();
        }

        return actual;
    }

    // Recorrido preorden: raiz, izquierda y derecha.
    @Override
    public void recorrerPreOrden() {
        if (estaVacio()) {
            System.out.println("El arbol de doctores esta vacio");
            return;
        }

        recorrerPreOrdenRecursivo(raiz);
    }

    private void recorrerPreOrdenRecursivo(NodoDoctor actual) {
        if (actual == null) {
            return;
        }

        mostrarDoctorEnRecorrido(actual.getDoctor());
        recorrerPreOrdenRecursivo(actual.getIzquierdo());
        recorrerPreOrdenRecursivo(actual.getDerecho());
    }

    // Recorrido inorden: izquierda, raiz y derecha.
    @Override
    public void recorrerInOrden() {
        if (estaVacio()) {
            System.out.println("El arbol de doctores esta vacio");
            return;
        }

        recorrerInOrdenRecursivo(raiz);
    }

    private void recorrerInOrdenRecursivo(NodoDoctor actual) {
        if (actual == null) {
            return;
        }

        recorrerInOrdenRecursivo(actual.getIzquierdo());
        mostrarDoctorEnRecorrido(actual.getDoctor());
        recorrerInOrdenRecursivo(actual.getDerecho());
    }

    // Recorrido postorden: izquierda, derecha y raiz.
    @Override
    public void recorrerPostOrden() {
        if (estaVacio()) {
            System.out.println("El arbol de doctores esta vacio");
            return;
        }

        recorrerPostOrdenRecursivo(raiz);
    }

    private void recorrerPostOrdenRecursivo(NodoDoctor actual) {
        if (actual == null) {
            return;
        }

        recorrerPostOrdenRecursivo(actual.getIzquierdo());
        recorrerPostOrdenRecursivo(actual.getDerecho());
        mostrarDoctorEnRecorrido(actual.getDoctor());
    }

    private void mostrarDoctorEnRecorrido(Doctor doctor) {
        System.out.println(doctor.getCodigo() + " - " + doctor.getNombres() + " " + doctor.getApellidos());
    }

    @Override
    public boolean estaVacio() {
        return raiz == null;
    }
}
