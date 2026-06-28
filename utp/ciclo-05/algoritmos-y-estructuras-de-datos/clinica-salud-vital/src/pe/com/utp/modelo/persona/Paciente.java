package pe.com.utp.modelo.persona;

/*
 * Representa a un paciente de la clinica.
 * Hereda los datos generales de Persona y agrega la edad.
 */
public class Paciente extends Persona {

    // Dato propio del paciente.
    private int edad;

    /*
     * super() inicializa los datos heredados de Persona.
     * edad se inicializa aqui porque pertenece solo a Paciente.
     */
    public Paciente(String codigo, String dni, String nombres, String apellidos, String telefono, String correo,
                    int edad) {
        super(codigo, dni, nombres, apellidos, telefono, correo);
        this.edad = edad;
    }

    /*
     * Implementacion del metodo abstracto.
     * Muestra los datos generales y el dato propio del paciente.
     */
    @Override
    public void mostrarDatos() {
        System.out.println("----- DATOS DEL PACIENTE -----");
        System.out.println("Codigo: " + getCodigo());
        System.out.println("DNI: " + getDni());
        System.out.println("Nombres: " + getNombres());
        System.out.println("Apellidos: " + getApellidos());
        System.out.println("Telefono: " + getTelefono());
        System.out.println("Correo: " + getCorreo());
        System.out.println("Edad: " + edad);
    }

    // Metodos de acceso del atributo propio.
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
