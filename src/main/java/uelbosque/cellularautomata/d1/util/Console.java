package uelbosque.cellularautomata.d1.util;

/**
 * Clase genérica para imprimir por consola
 * @author Jorge Gantiva Ochoa
 */
public class Console {
    /**
     * Mostrar mensaje por consola en una línea separada
     * @param message Mensaje a mostrar en consola
     */
    public static void log(String message){
        System.out.println(message);
    }
    
    /**
     * Mostrar mensaje por consola en la misma línea
     * @param message Mensaje a mostrar por consola
     */
    public static void logAppend(String message){
        System.out.print(message);
    }
}