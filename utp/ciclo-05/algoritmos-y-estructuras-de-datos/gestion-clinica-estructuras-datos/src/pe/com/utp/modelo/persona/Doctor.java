package pe.com.utp.modelo.persona;

import pe.com.utp.modelo.enums.Especialidad;
import pe.com.utp.modelo.enums.TurnoConsultaAmbulatoria;

/*
 * Representa a un doctor de la clinica.
 * Hereda Persona y agrega CMP, especialidad y horario.
 */
public class Doctor extends Persona {

    // Datos propios del doctor.
    private String cmp;
    private Especialidad especialidad;
    private TurnoConsultaAmbulatoria horario;

    /*
     * super() inicializa los datos generales.
     * Los demas atributos pertenecen al perfil medico.
     */
    public Doctor(String codigo, String dni, String nombres, String apellidos, String telefono, String cmp, String correo, Especialidad especialidad, TurnoConsultaAmbulatoria horario) {
        super(codigo, dni, nombres, apellidos, telefono, correo);
        this.especialidad = especialidad;
        this.horario = horario;
        this.cmp = cmp;
    }

    /*
     * Implementacion del metodo abstracto.
     * Incluye los datos medicos que no existen en Persona.
     */
    @Override
    public void mostrarDatos() {
        System.out.println("----- DATOS DEL DOCTOR -----");
        System.out.println("Codigo: " + getCodigo());
        System.out.println("DNI: " + getDni());
        System.out.println("Nombres: " + getNombres());
        System.out.println("Apellidos: " + getApellidos());
        System.out.println("Telefono: " + getTelefono());
        System.out.println("CMP: " + cmp);
        System.out.println("Correo: " + getCorreo());
        System.out.println("Especialidad: " + especialidad);
        System.out.println("Horario: " + horario);
    }

    // Metodos de acceso de los atributos propios del doctor.
    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public String getCmp() {
        return cmp;
    }

    public void setCmp(String cmp) {
        this.cmp = cmp;
    }

    public TurnoConsultaAmbulatoria getHorario() {
        return horario;
    }

    public void setHorario(TurnoConsultaAmbulatoria horario) {
        this.horario = horario;
    }
}
