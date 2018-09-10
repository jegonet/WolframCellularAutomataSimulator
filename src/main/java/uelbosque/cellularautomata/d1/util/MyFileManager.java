package uelbosque.cellularautomata.d1.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Clase genérica de manejo de archivos
 * @author Jorge Gantiva Ochoa
 */
public class MyFileManager {
    /**
     * Lee un archivo de texto y lo convierte en una lista (ArrayList) cuyos ítems son cada linea de texto.
     * @param fileName Nombre del archivo a ser leido (incluye la ruta donde está ubicado).
     * @return Lista de lineas de texto del archivo seleccionado.
     * @throws FileNotFoundException Error de archivo no encontrado.
     * @throws IOException Error de lectura de archivo.
     */
    public static ArrayList<String> readFileAsStringLines(String fileName) throws FileNotFoundException, IOException{
        ArrayList<String> lineas = (ArrayList<String>)Files.readAllLines(Paths.get(fileName), Charset.forName("UTF-8"));        
        return lineas;
    }
}