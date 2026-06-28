package pe.com.utp.interfaces;

import pe.com.utp.modelo.persona.Doctor;

/*
 * TAD para un arbol binario de busqueda de doctores.
 * Define insercion, busqueda, eliminacion y recorridos de arbol.
 */
public interface TADArbolDoctores {

    void insertarDoctor(Doctor doctor);

    Doctor buscarDoctorPorCodigo(String codigoDoctor);

    void eliminarDoctorPorCodigo(String codigoDoctor);

    void recorrerPreOrden();

    void recorrerInOrden();

    void recorrerPostOrden();

    boolean estaVacio();
}
