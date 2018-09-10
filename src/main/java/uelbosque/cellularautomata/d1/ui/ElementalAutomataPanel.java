package uelbosque.cellularautomata.d1.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import uelbosque.cellularautomata.d1.Cell;
import uelbosque.cellularautomata.d1.params.Constants;

/**
 * Clase JPanel para mostrar en pantalla representación gráfica del autómata celular
 * @author Jorge Gantiva Ochoa
 */
public class ElementalAutomataPanel extends JPanel{
    /**
     * Listado de espacios de celdas a representar
     */
    private final ArrayList<Cell[]> cellSpaceList;
    /**
     * Ancho del panel dinámico de acuerdo al tamaño del autómata
     */
    private final int panelWidth;
    /**
     * Número máximo de lineas verticales de estados del autómata
     */
    private final static int MAX_GRAPH_LINES = 62;
    
    public ElementalAutomataPanel(Cell[] inicialCells){
        this.cellSpaceList = new ArrayList<Cell[]>();
        this.cellSpaceList.add(inicialCells);
        this.panelWidth = (Constants.SPACE_SIZE - 2) * 10;
    }
    
    /**
     * Pinta el autómata celular en un JPanel de JAVA
     * @param g Componente encargado de pintar el JPanel
     */
    @Override
    protected void paintComponent(Graphics g) {
        //Asigna un tamaño al autómata
        this.setSize(panelWidth + 20, MAX_GRAPH_LINES * 11);
        super.paintComponent(g);
        
        int graphicY = 0;                
        //Pintar cada una de las celdas y líneas verticales
        for(Cell[] cells : cellSpaceList){
            graphicY += 10;
            for(int i=1; i<cells.length-1; i++){
                if(cells[i].getStateValue()==Constants.DEFAULT_CELL_STATE)
                    g.setColor(Color.WHITE);
                else
                    g.setColor(Color.BLACK);
                g.fillRect(i*10, graphicY, 10, 10);

                g.setColor(Color.BLACK);
                g.drawLine(i*10, graphicY, i*10, graphicY + 10);
            }
        
            //Pintar las líneas horizontales y la última vertical
            g.drawLine(10, graphicY, panelWidth + 10, graphicY);
            g.drawLine(10, graphicY+10, panelWidth + 10, graphicY+10);
            g.drawLine(panelWidth + 10, graphicY, panelWidth + 10, graphicY+10);
        }
    }
    
    /**
     * Agrega una nueva línea de estado de celdas a la gráfica
     * @param cells Espacio de estado de celdas a agregar a la gráfica
     */
    public void addNewGraphStateLine(Cell[] cells){
        if(cellSpaceList.size()<MAX_GRAPH_LINES){
            cellSpaceList.add(cells);
            this.repaint();
        }
    }
}