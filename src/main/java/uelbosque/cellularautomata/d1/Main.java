package uelbosque.cellularautomata.d1;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import uelbosque.cellularautomata.d1.params.Constants;
import uelbosque.cellularautomata.d1.ui.ElementalAutomataPanel;
import uelbosque.cellularautomata.d1.util.BinaryConverter;
import uelbosque.cellularautomata.d1.util.Console;

/**
 * Clase de inicio de la aplicación
 * @author Jorge Gantiva Ochoa
 */
public class Main {
    
    /**
     * Objeto autótama celular unidimensional
     */
    private static CellularAutomata cellularAutomata;
    /**
     * Objeto de ventana principal para mostrar el autómata
     */
    private static JFrame mainWindow;
    /**
     * Objeto panel para dibujar el autómata
     */
    private static ElementalAutomataPanel automataPanel;
    
    /**
     * Método de arranca del programa
     * @param args argumentos de arranque (no en uso)
     */
    public static void main(String[] args){

        /**
         * Instanciación de autómata celular 
         */
        try{
            if(Constants.DEFAULT_RULE_LOADER_MODE == Constants.RULE_LOADER_MODE.FILE)
                //Crea el autómata celular desde un archivo
                cellularAutomata = new CellularAutomata();
            else{
                //Crea el autómata celular solitando un número de regla de Wolfram por teclado
                int wolframRuleNumber = Console.captureNumber("Por favor indique el número de la regla de Wolfram a simular. Debe ser un valor entre 0 y 255:", 0, 255);
                cellularAutomata = new CellularAutomata(wolframRuleNumber);
            }
        }
        catch(IOException ex){
            Console.log("Error al tratar de acceder y leer el archivo de reglas");
            Console.log(ex.getMessage());
            System.exit(0);
        }
        catch(NumberFormatException|IndexOutOfBoundsException ex){
            Console.log("Error en el formato del archivo de reglas, sólo debe contener números");
            Console.log(ex.getMessage());
            System.exit(0);
        }
        
        /**
         * Cargue del estado inicial del autómata (desde un archivo plano)
         */
        try{
            cellularAutomata.loadInitialState();
        }
        catch(IOException ex){
            Console.log("Error al tratar de acceder y leer el archivo del estado inicial del autómata celular");
            Console.log(ex.getMessage());
            System.exit(0);
        }
        catch(NumberFormatException ex){
            Console.log("Error en el formato del archivo del estado inicial del autómata celular");
            Console.log(ex.getMessage());
            System.exit(0);
        }
        catch(IndexOutOfBoundsException ex){
            Console.log("Error en los datos del archivo del estado inicial del autómata celular, hay datos por fuera del espacio finito creado");
            Console.log(ex.getMessage());
            System.exit(0);
        }
        
        int evolutionCounter = Console.captureNumber("Por favor indique el número de evoluciones del autómata. Debe ser un valor entre 1 y 61 por restricción de espacio físico en la pantalla:", 1, 61);;
        /**
         * Llama método de configuración de interfaz gráfica para presentar el autómata celular
         */
        setupUI();
        
        /**
         * Corre de manera indefinida el autómata celular con cambios de estado cada segundo
         */
        try{
            run(evolutionCounter);
        }
        catch(InterruptedException ex){
            Console.log("Falla en la activación de sleep en el hilo principal del programa");
            Console.log(ex.getMessage());
            System.exit(0);
        }
    }
    
    /**
     * Método de configuración de la interfaz gráfica para mostrar el autómata celular
     */
    public static void setupUI(){
        automataPanel = new 
            ElementalAutomataPanel(cellularAutomata.getCellSpace().getCells().clone());
        
        mainWindow = new JFrame();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }
        
        mainWindow.setTitle("Autómata Celular Elemental");
        mainWindow.setSize((Constants.SPACE_SIZE-2)*10+40, 680);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.add(automataPanel);
        mainWindow.setVisible(true);
    }
    
    /**
     * Método que realiza el cambio de estado del autómata cada segundo
     * @param evolutionCounter Número de evoluciones del autómata
     * @throws InterruptedException Excepción por falla en detención de hilo principal
     */
    public static void run(int evolutionCounter) throws InterruptedException{
        for(int i=1; i<=evolutionCounter; i++){
            Thread.sleep(100);
            cellularAutomata.runToNextState();
            automataPanel.addNewGraphStateLine(cellularAutomata.getCellSpace().getCells().clone());
            automataPanel.repaint();
        }
    }
}