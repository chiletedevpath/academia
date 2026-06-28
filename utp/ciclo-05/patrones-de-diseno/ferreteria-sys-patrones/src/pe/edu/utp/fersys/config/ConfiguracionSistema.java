package pe.edu.utp.fersys.config;

// Patron Singleton: centraliza la configuracion general del sistema.
public class ConfiguracionSistema {
    private static ConfiguracionSistema instancia;

    private String nombreSistema;
    private String version;
    private String moneda;
    private double igv;

    // Constructor privado para evitar instancias externas con new.
    private ConfiguracionSistema() {
        this.nombreSistema = "FerreSys";
        this.version = "1.0";
        this.moneda = "S/";
        this.igv = 0.18;
    }

    // Devuelve la unica instancia compartida de configuracion.
    public static ConfiguracionSistema obtenerInstancia() {
        if (instancia == null) {
            instancia = new ConfiguracionSistema();
        }

        return instancia;
    }

    // Devuelve el nombre oficial del sistema.
    public String obtenerNombreSistema() {
        return nombreSistema;
    }

    // Devuelve la version configurada del sistema.
    public String obtenerVersion() {
        return version;
    }

    // Devuelve la moneda usada para importes comerciales.
    public String obtenerMoneda() {
        return moneda;
    }

    // Devuelve el porcentaje de IGV configurado.
    public double obtenerIgv() {
        return igv;
    }
}
