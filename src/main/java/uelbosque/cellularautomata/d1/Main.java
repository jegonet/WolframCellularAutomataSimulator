package uelbosque.cellularautomata.d1;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import uelbosque.cellularautomata.d1.params.Constants;
import uelbosque.cellularautomata.d1.ui.ElementalAutomataPanel;
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
            cellularAutomata = new CellularAutomata();
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

        /**
         * Llama método de configuración de interfaz gráfica para presentar el autómata celular
         */
        setupUI();
        
        /**
         * Corre de manera indefinida el autómata celular con cambios de estado cada segundo
         */
        try{
            run();
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
     * @throws InterruptedException Excepción por falla en detención de hilo principal
     */
    public static void run() throws InterruptedException{
        while(true){
            Thread.sleep(100);
            cellularAutomata.runToNextState();
            automataPanel.addNewGraphStateLine(cellularAutomata.getCellSpace().getCells().clone());
            automataPanel.repaint();
        }
    }
}