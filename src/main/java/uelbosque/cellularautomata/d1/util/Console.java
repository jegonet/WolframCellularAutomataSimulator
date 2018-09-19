package uelbosque.cellularautomata.d1.util;

import java.util.Scanner;

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
    
    /**
     * Solicita capturar por teclado un número entre un rango
     * @param message Mensaje del número a capturar
     * @param min Valor mínimo permitido a capturar
     * @param max Valor máximo permitido a capturar
     * @return Valor capturado por teclado que se encuentra dentro del rango señalado
     */
    public static int captureNumber(String message, int min, int max){
        Scanner scanner = new Scanner(System.in);
        int n = min - 1;
        while(n<min || n>max) {
            log(message);
            n = scanner.nextInt();
        }
        return n;
    }
}