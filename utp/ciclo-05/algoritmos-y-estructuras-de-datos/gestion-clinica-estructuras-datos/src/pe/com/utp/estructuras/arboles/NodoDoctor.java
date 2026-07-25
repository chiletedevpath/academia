package pe.com.utp.estructuras.arboles;

import pe.com.utp.modelo.persona.Doctor;

/*
 * Nodo usado por el arbol binario de busqueda.
 * Guarda un doctor y las referencias a sus hijos izquierdo y derecho.
 */
public class NodoDoctor {

    private Doctor doctor;
    private NodoDoctor izquierdo;
    private NodoDoctor derecho;

    public NodoDoctor(Doctor doctor) {
        this.doctor = doctor;
        this.izquierdo = null;
        this.derecho = null;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public NodoDoctor getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoDoctor izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoDoctor getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoDoctor derecho) {
        this.derecho = derecho;
    }
}
