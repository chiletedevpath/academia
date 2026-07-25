package pe.com.utp.persistencia;

import pe.com.utp.modelo.cita.Cita;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ArchivoCita {

    // Guarda citas en un archivo CSV simple.
    public void guardarCitas(String rutaArchivo, Cita[] citas, int totalCitas) {
        crearCarpetaSiNoExiste(rutaArchivo);

        try (PrintWriter escritor = new PrintWriter(new FileWriter(rutaArchivo))) {
            escritor.println("codigo,paciente,doctor,fecha,hora,estado,motivo");

            for (int i = 0; i < totalCitas; i++) {
                Cita cita = citas[i];
                escritor.println(
                        cita.getCodigo() + "," +
                                cita.getPaciente().getCodigo() + "," +
                                cita.getDoctor().getCodigo() + "," +
                                cita.getFecha() + "," +
                                cita.getHora() + "," +
                                cita.getEstado() + "," +
                                cita.getMotivo()
                );
            }
        } catch (IOException e) {
            System.out.println("Error al guardar citas: " + e.getMessage());
        }
    }

    private void crearCarpetaSiNoExiste(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        File carpeta = archivo.getParentFile();

        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }
    }
}
