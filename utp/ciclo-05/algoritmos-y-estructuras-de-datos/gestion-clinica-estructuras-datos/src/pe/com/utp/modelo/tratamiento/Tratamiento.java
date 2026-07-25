package pe.com.utp.modelo.tratamiento;

public class Tratamiento {

    // Codigo que identifica el tratamiento dentro del sistema.
    private String codigo;

    // Codigo del paciente al que pertenece la indicacion medica.
    private String codigoPaciente;

    // Descripcion breve del tratamiento indicado.
    private String descripcion;

    // Cantidad de dias que dura el tratamiento.
    private int duracionDias;

    public Tratamiento(String codigo, String codigoPaciente, String descripcion, int duracionDias) {
        this.codigo = codigo;
        this.codigoPaciente = codigoPaciente;
        this.descripcion = descripcion;
        this.duracionDias = duracionDias;
    }

    public void mostrarDatos() {
        System.out.println("----- DATOS DEL TRATAMIENTO -----");
        System.out.println("Codigo: " + codigo);
        System.out.println("Codigo de paciente: " + codigoPaciente);
        System.out.println("Descripcion: " + descripcion);
        System.out.println("Duracion en dias: " + duracionDias);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCodigoPaciente() {
        return codigoPaciente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getDuracionDias() {
        return duracionDias;
    }
}
