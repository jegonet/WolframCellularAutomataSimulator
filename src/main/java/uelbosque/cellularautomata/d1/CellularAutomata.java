package uelbosque.cellularautomata.d1;

import java.io.IOException;
import java.util.ArrayList;
import uelbosque.cellularautomata.d1.params.Constants;
import uelbosque.cellularautomata.d1.util.MyFileManager;

/**
 * Clase de autómata celular unidimensional
 * @author Jorge Gantiva Ochoa
 */
public class CellularAutomata {
    /**
     * Espacio de celdas del autómata
     */
    private final CellSpace cellSpace;
    /**
     * Validador de reglas del autómata
     */
    private final RuleValidator ruleValidator;
    
    /**
     * Constructor por defecto del autómata celular
     * @throws IOException Posible error el cargar las reglas del autómata
     */
    public CellularAutomata() throws IOException {
        //Instancia el espacio de celdas de acuerdo al tamaño definido
        cellSpace = new CellSpace(Constants.SPACE_SIZE);
        ruleValidator = new RuleValidator();
    }
    
    /**
     * Carga el estado inicial del autómata celular
     * @throws IOException Posible error al cargar el estado inicial desde un archivo
     * @throws IndexOutOfBoundsException Posible error de índices en el archivo
     * @throws NumberFormatException Posible error de formato en el archivo
     */
    public void loadInitialState() throws IOException, IndexOutOfBoundsException, NumberFormatException{
        //Carga el espacio con nuevas celdas
        for(int i=0; i<Constants.SPACE_SIZE; i++){
            cellSpace.getCells()[i] = new Cell();
            cellSpace.getCellsNext()[i] = new Cell();
        }
        
        //Obtiene de un archivo el estado inicial del autómata
        //Archivo de una sola línea, separa celdas por coma (,), cada celda con índice y valor separado por dos puntos (:)
        String stringInitialState = 
                ((ArrayList<String>)(MyFileManager.readFileAsStringLines("state-ini.txt"))).get(0);
        
        //Asigna el estado inicial al autómata
        String[] states = stringInitialState.split(",");
        for(int i=0; i<states.length; i++){
            int indexState = Integer.parseInt(states[i].split(":")[0]);
            int valueState = Integer.parseInt(states[i].split(":")[1]);            
            cellSpace.getCells()[indexState].setStateValue(valueState);
        }
    }
    
    /**
     * Carga el siguiente estado del autómata por medio del validador de reglas
     */
    public void runToNextState(){
        /**
         * Valida cada regla de manera genérica para cambiar el estado de las celdas
         */
        for(int i=1; i<Constants.SPACE_SIZE -1; i++){
            int nextState = ruleValidator.getNextCellState(
                    cellSpace.getCells()[i-1].getStateValue(), 
                    cellSpace.getCells()[i].getStateValue(), 
                    cellSpace.getCells()[i+1].getStateValue());
            
            cellSpace.getCellsNext()[i].setStateValue(nextState);
        }
        cellSpace.doNextStateTheCurrent();
    }

    /**
     * Obtiene el espacio de celdas del autómata en su estado actual
     * @return Espacio de celdas del autómata en su estado actual
     */
    public CellSpace getCellSpace() {
        return cellSpace;
    }
}