package uelbosque.cellularautomata.d1;

/**
 * Clase del espacio de celdas del autómata
 * @author Jorge Gantiva Ochoa
 */
public class CellSpace {
    /**
     * Espacio de celdas en el estado actual
     */
    private Cell[] cells;
    /**
     * Espacio de celdas en el estado futuro (siguiente)
     */
    private final Cell[] cellsNext;

    /**
     * Constructor del espacio de celdas del autómata
     * @param dimensionalSize Tamaño del espacio en celdas
     */
    public CellSpace(int dimensionalSize) {
        cells = new Cell[dimensionalSize];
        cellsNext = new Cell[dimensionalSize];
    }
    
    /**
     * Obtiene el espacio de celdas en el estado actual
     * @return Espacio de celdas en el estado actual
     */
    public Cell[] getCells() {
        return cells;
    }
    
    /**
     * Obtiene el espacio de celdas en el estado futuro (siguiente)
     * @return Espacio de celdas en el estado futuro (siguiente)
     */
    public Cell[] getCellsNext() {
        return cellsNext;
    }
    
    /**
     * Pasa en "paralelo" todas las celdas del estado actual al estado futuro (siguiente)
     */
    public void doNextStateTheCurrent(){
        for(int i=0; i<cells.length; i++){
            cells[i] = new Cell();
            cells[i].setStateValue(cellsNext[i].getStateValue());
        }
    }
}