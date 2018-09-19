package uelbosque.cellularautomata.d1.util;

/**
 * Clase utilitaria para convertir un número decimal a un arreglo de binarios
 * @author Jorge Gantiva Ochoa
 */
public class BinaryConverter {
    /**
     * Convertir un número decimal a un arreglo de números binarios
     * @param decimalNumber Número decimal a convertir
     * @param binaryLength Tamaño del arreglo de binarios
     * @return Arreglo de números binarios
     */
    public static int[] convertDecimalToBinaryArray(int decimalNumber, int binaryLength){
        int container[] = new int[binaryLength];
        
        int i = 0;
        while (decimalNumber > 0 && i<binaryLength){
            container[i] = decimalNumber%2;
            i++;
            decimalNumber = decimalNumber/2;
        }
        return container;
    }
}