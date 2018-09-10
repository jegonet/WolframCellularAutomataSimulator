package uelbosque.cellularautomata.d1;

/**
 * Clase de modelo de regla de Wolfram (ACU)
 * @author Jorge Gantiva Ochoa
 */
public class WolframRule {
    /**
     * Estado de la celda a la izquierda
     */
    private final int leftState;
    /**
     * Estado actual de la celda
     */
    private final int currentState;
    /**
     * Estado de la celda a la derecha
     */
    private final int rightState;
    /**
     * Nuevo estado de la celda
     */
    private final int nextState;

    /**
     * Constructor por parámetros de la regla
     * @param leftState Estado de la celda a la izquierda
     * @param currentState Estado actual de la celda
     * @param rightState Estado de la celda a la derecha
     * @param nextState Nuevo estado de la celda
     */
    public WolframRule(int leftState, int currentState, int rightState, int nextState) {
        this.leftState = leftState;
        this.currentState = currentState;
        this.rightState = rightState;
        this.nextState = nextState;
    }
    
    /**
     * Retorna el estado de la celda a la izquierda según la regla
     * @return Estado de la celda a la izquierda
     */
    public int getLeftState() {
        return leftState;
    }

    /**
     * Retorna el estado de la celda a la derecha según la regla
     * @return Estado de la celda a la derecha
     */
    public int getRightState() {
        return rightState;
    }
    
    /**
     * Obtiene el estado actual de la celda según la regla
     * @return Estado actual de la celda
     */
    public int getCurrentState() {
        return currentState;
    }

    /**
     * Obtiene el estado nuevo de la celda según la regla
     * @return Nuevo estado de la celda
     */
    public int getNextState() {
        return nextState;
    }
}