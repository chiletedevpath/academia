package pe.com.utp.modelo.tratamiento;

public class Receta {

    // Codigo que identifica la receta emitida.
    private String codigo;

    // Codigo del tratamiento al que pertenece la receta.
    private String codigoTratamiento;

    // Medicamento indicado al paciente.
    private String medicamento;

    // Forma de uso o indicacion medica.
    private String indicacion;

    public Receta(String codigo, String codigoTratamiento, String medicamento, String indicacion) {
        this.codigo = codigo;
        this.codigoTratamiento = codigoTratamiento;
        this.medicamento = medicamento;
        this.indicacion = indicacion;
    }

    public void mostrarDatos() {
        System.out.println("----- DATOS DE LA RECETA -----");
        System.out.println("Codigo: " + codigo);
        System.out.println("Codigo de tratamiento: " + codigoTratamiento);
        System.out.println("Medicamento: " + medicamento);
        System.out.println("Indicacion: " + indicacion);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCodigoTratamiento() {
        return codigoTratamiento;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public String getIndicacion() {
        return indicacion;
    }
}
