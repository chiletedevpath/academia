package pe.com.utp.modelo.persona;

/*
 * Representa a un administrador del sistema.
 * Hereda los datos generales de Persona y agrega el cargo.
 */
public class Administrador extends Persona {

    // Dato propio del administrador.
    private String cargo;

    /*
     * super() inicializa los datos generales.
     * cargo se asigna aqui porque solo pertenece al administrador.
     */
    public Administrador(String codigo, String dni, String nombres, String apellidos, String telefono, String correo, String cargo) {
        super(codigo, dni, nombres, apellidos, telefono, correo);
        this.cargo = cargo;
    }

    /*
     * Implementacion del metodo abstracto.
     * Muestra los datos comunes y el cargo administrativo.
     */
    @Override
    public void mostrarDatos() {

        System.out.println("----- DATOS DEL ADMINISTRADOR -----");
        System.out.println("Codigo: " + getCodigo());
        System.out.println("DNI: " + getDni());
        System.out.println("Nombres: " + getNombres());
        System.out.println("Apellidos: " + getApellidos());
        System.out.println("Telefono: " + getTelefono());
        System.out.println("Correo: " + getCorreo());
        System.out.println("Cargo: " + cargo);
    }

    // Metodos de acceso del atributo propio.
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
