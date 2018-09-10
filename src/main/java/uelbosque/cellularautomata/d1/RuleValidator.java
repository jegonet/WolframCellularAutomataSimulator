package uelbosque.cellularautomata.d1;

import java.io.IOException;
import java.util.ArrayList;
import uelbosque.cellularautomata.d1.util.MyFileManager;

/**
 * Validador genérico de reglas para ACU
 * @author Jorge Gantiva Ochoa
 */
public class RuleValidator {
    /**
     * Listado de reglas del autómata
     */
    private ArrayList<WolframRule> ruleList;
    
    /**
     * Constructor por defecto del validador de reglas
     * @throws IOException Posible error al leer el archivo de reglas
     */
    public RuleValidator() throws IOException {
        //Carga las reglas del autómata
        loadRules();
    }
    
    /**
     * Carga reglas del autómata
     * @throws IOException Posible error al leer el archivo de reglas
     * @throws IndexOutOfBoundsException Posible error de índices al leer el archivo de reglas
     * @throws NumberFormatException Posible error en el formato del archivo de reglas
     */
    private void loadRules() throws IOException, IndexOutOfBoundsException, NumberFormatException{
        ruleList = new ArrayList<>();
        
        //Carga reglas del autómata desde un archivo
        //Cada línea del archivo es una regla
        //Cada regla tiene 4 números
        //Primer número: estado de celda a la izquierda
        //Segundo número: estado actual de la celda
        //Tercer número: estado de celda a la derecha
        //Cuarto número: nuevo estado de la celda
        ArrayList<String> stringRules = MyFileManager.readFileAsStringLines("rules.txt");
        
        for(String stringRule : stringRules){
            int leftState = Integer.parseInt(String.valueOf(stringRule.charAt(0)));
            int currentState = Integer.parseInt(String.valueOf(stringRule.charAt(1)));
            int rightState = Integer.parseInt(String.valueOf(stringRule.charAt(2)));
            int nextState = Integer.parseInt(String.valueOf(stringRule.charAt(3)));
            
            WolframRule rule = new WolframRule(leftState, currentState, rightState, nextState);
            ruleList.add(rule);
        }
    }
    
    /**
     * Obtiene el nuevo estado de un celda
     * @param leftState Estado de la celda de la izquierda
     * @param currentState Estado actual de la celda
     * @param rightState Estado de la celda de la derecha
     * @return Nuevo estado de la celda
     */
    public int getNextCellState(int leftState, int currentState, int rightState){
        //Valida cada regla
        for(WolframRule rule : ruleList){
            if( rule.getLeftState() == leftState && 
                rule.getCurrentState() == currentState &&
                rule.getRightState() == rightState)
                return rule.getNextState();
        }
        return currentState; //Mantener estado actual si no aplica ninguna regla
    }
}