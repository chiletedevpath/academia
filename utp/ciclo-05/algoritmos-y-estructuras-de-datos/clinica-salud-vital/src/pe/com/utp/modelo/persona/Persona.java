package pe.com.utp.modelo.persona;

/*
 * Clase base para las personas del sistema.
 * Centraliza los datos comunes de pacientes, doctores y administradores.
 * POO: se usa abstraccion para evitar repetir atributos en cada clase hija.
 */
public abstract class Persona {

    // Datos comunes que comparten las clases hijas.
    private String codigo;
    private String dni;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correo;

    /*
     * Constructor usado por las clases hijas mediante super().
     * Recibe los datos generales de cualquier persona registrada.
     */
    public Persona(String codigo, String dni, String nombres, String apellidos, String telefono, String correo) {

        this.codigo = codigo;
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
    }

    /*
     * Metodo obligatorio para las clases hijas.
     * Cada tipo de persona muestra sus datos segun su propio contexto.
     * POO: este metodo permite polimorfismo porque cada subclase lo implementa diferente.
     */
    public abstract void mostrarDatos();

    // POO: encapsulamiento, los atributos son privados y se accede mediante metodos.
    public String getCodigo() {
        return codigo;
    }

    public String getDni() {
        return dni;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
