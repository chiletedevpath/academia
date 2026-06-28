package pe.com.utp.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UtilidadesFecha {

    // Formatea fechas usando el patron dia/mes/anio.
    public String formatearFecha(LocalDate fecha) {
        if (fecha == null) {
            return "";
        }

        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
