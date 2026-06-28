package pe.com.utp.interfaces;

public interface Registrable<T> {

    // Contrato base para clases que registran datos en memoria.
    void registrar(T elemento);
}
