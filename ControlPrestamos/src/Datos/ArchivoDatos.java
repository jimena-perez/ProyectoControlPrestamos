package datos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ArchivoDatos {

    private static final String CARPETA = "datos";

    public ArchivoDatos() {
        crearCarpeta();
    }

    private void crearCarpeta() {
        File carpeta = new File(CARPETA);

        if (!carpeta.exists()) {
            carpeta.mkdir();
        }
    }

    public void guardarLineas(String nombreArchivo, ArrayList<String> lineas) {
        try {
            PrintWriter escritor = new PrintWriter(CARPETA + "/" + nombreArchivo);

            for (String linea : lineas) {
                escritor.println(linea);
            }

            escritor.close();

        } catch (Exception e) {
            System.out.println("Error al guardar el archivo: " + nombreArchivo);
        }
    }

    public ArrayList<String> leerLineas(String nombreArchivo) {
        ArrayList<String> lineas = new ArrayList<String>();

        try {
            File archivo = new File(CARPETA + "/" + nombreArchivo);

            if (!archivo.exists()) {
                return lineas;
            }

            BufferedReader lector = new BufferedReader(new FileReader(archivo));

            String linea = lector.readLine();

            while (linea != null) {
                lineas.add(linea);
                linea = lector.readLine();
            }

            lector.close();

        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + nombreArchivo);
        }

        return lineas;
    }
}