package uelbosque.cellularautomata.d1;

import uelbosque.cellularautomata.d1.params.Constants;

/**
 * Clase de modelo de una celda del autómata
 * @author Jorge Gantiva Ochoa
 */
public class Cell {
    /**
     * Valor del estado de la celda
     */
    private int stateValue;

    /**
     * Constructor por defecto de la celda
     */
    public Cell(){
        stateValue = Constants.DEFAULT_CELL_STATE;
    }
        
    /**
     * Obtiene el valor del estado de la celda
     * @return Valor del estado de la celda
     */
    public int getStateValue() {
        return stateValue;
    }
    
    /**
     * Asigna el valor del estado de la celda
     * @param value Nuevo valor del estado de la celda
     */
    public void setStateValue(int value) {
        this.stateValue = value;
    }
    
    /**
     * Obtiene una representación en forma de una cadena de texto del estado de la celda
     * @return Representación en cadena de texto del estado de la celda
     */
    @Override
    public String toString(){
        return "|" + String.valueOf(stateValue);
    }
}