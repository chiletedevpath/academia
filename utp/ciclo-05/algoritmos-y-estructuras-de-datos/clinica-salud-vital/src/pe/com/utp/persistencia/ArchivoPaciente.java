package pe.com.utp.persistencia;

import pe.com.utp.modelo.persona.Paciente;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ArchivoPaciente {

    // Guarda pacientes en un archivo CSV simple.
    public void guardarPacientes(String rutaArchivo, Paciente[] pacientes, int totalPacientes) {
        crearCarpetaSiNoExiste(rutaArchivo);

        try (PrintWriter escritor = new PrintWriter(new FileWriter(rutaArchivo))) {
            escritor.println("codigo,dni,nombres,apellidos,telefono,correo,edad");

            for (int i = 0; i < totalPacientes; i++) {
                Paciente paciente = pacientes[i];
                escritor.println(
                        paciente.getCodigo() + "," +
                                paciente.getDni() + "," +
                                paciente.getNombres() + "," +
                                paciente.getApellidos() + "," +
                                paciente.getTelefono() + "," +
                                paciente.getCorreo() + "," +
                                paciente.getEdad()
                );
            }
        } catch (IOException e) {
            System.out.println("Error al guardar pacientes: " + e.getMessage());
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
