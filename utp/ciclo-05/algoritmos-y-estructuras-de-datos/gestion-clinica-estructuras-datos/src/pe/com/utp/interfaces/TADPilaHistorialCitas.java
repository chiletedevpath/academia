package pe.com.utp.interfaces;

import pe.com.utp.modelo.cita.Cita;

/*
 * TAD para una pila dinamica de citas recientes.
 * Define operaciones LIFO: apilar, desapilar, consultar cima y recorrer.
 * Se usa como historial tecnico, no como orden de atencion de pacientes.
 */
public interface TADPilaHistorialCitas {

    void apilar(Cita cita);

    Cita desapilar();

    Cita verCima();

    boolean estaVacia();

    void mostrarPila();

}
