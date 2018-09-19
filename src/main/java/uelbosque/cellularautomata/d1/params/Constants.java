package uelbosque.cellularautomata.d1.params;

/**
 * Constantes del sistema
 * @author Jorge Gantiva Ochoa
 */
public class Constants {
    /**
     * Valor por defecto de las celdas del autómata
     */
    public static final int DEFAULT_CELL_STATE = 0;
    /**
     * Espacio en celdas del autómata. En esta versión los límites a la izquierda y a la derecha estarán siempre en estado 0 (no se mostrarán en la interfaz)
     */
    public final static int SPACE_SIZE = 102;
    /**
     * Modos de funcionamiento de carga de reglas del autómata. Mediante un archivo o por captura de teclado
     */
    public enum RULE_LOADER_MODE{
        FILE, KEYBOARD
    }
    /**
     * Modo de funcionamiento con captura de regla desde teclado, de lo contrario carga desde archivo
     */
    public final static RULE_LOADER_MODE DEFAULT_RULE_LOADER_MODE = RULE_LOADER_MODE.KEYBOARD;
}