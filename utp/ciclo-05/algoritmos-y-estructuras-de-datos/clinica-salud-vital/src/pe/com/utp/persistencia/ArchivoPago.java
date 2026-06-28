package pe.com.utp.persistencia;

import pe.com.utp.modelo.pago.Pago;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ArchivoPago {

    // Guarda pagos en un archivo CSV simple.
    public void guardarPagos(String rutaArchivo, Pago[] pagos, int totalPagos) {
        crearCarpetaSiNoExiste(rutaArchivo);

        try (PrintWriter escritor = new PrintWriter(new FileWriter(rutaArchivo))) {
            escritor.println("codigo,codigoCita,monto,tipoPago,fechaPago");

            for (int i = 0; i < totalPagos; i++) {
                Pago pago = pagos[i];
                escritor.println(
                        pago.getCodigo() + "," +
                                pago.getCodigoCita() + "," +
                                pago.getMonto() + "," +
                                pago.getTipoPago() + "," +
                                pago.getFechaPago()
                );
            }
        } catch (IOException e) {
            System.out.println("Error al guardar pagos: " + e.getMessage());
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
