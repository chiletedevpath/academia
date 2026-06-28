package pe.com.utp.modelo.cita;

public class HistorialCita {

    // Codigo del historial clinico asociado a un paciente.
    private String codigo;

    // Codigo del paciente propietario del historial.
    private String codigoPaciente;

    // Observacion general del historial.
    private String observacion;

    public HistorialCita(String codigo, String codigoPaciente, String observacion) {
        this.codigo = codigo;
        this.codigoPaciente = codigoPaciente;
        this.observacion = observacion;
    }

    public void mostrarDatos() {
        System.out.println("----- HISTORIAL DE CITA -----");
        System.out.println("Codigo: " + codigo);
        System.out.println("Codigo de paciente: " + codigoPaciente);
        System.out.println("Observacion: " + observacion);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCodigoPaciente() {
        return codigoPaciente;
    }

    public String getObservacion() {
        return observacion;
    }
}
