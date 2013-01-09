package chess.ui;

import chess.logic.Controls;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;

/**
 * Käsittelee laudan ulkopuolella olevien nappuloiden painamiset
 * 
 * @author joel
 */

public class ButtonListener implements ActionListener{
    /**
     * selections-oliota kutsutaan silloin, kun hiirtä klikataan laudan alueella
     * tai sivupaneelin painikkeita painetaan. 
     */
    private Selections selections;
    /**
     * Suorittaa painikkeista seuraavat tapahtumat.
     */
    private Controls controls;
    /**
     * Piirtää koko laudan. 
     */
    private Drawer drawer;
    /**
     * Uusi peli -nappi
     */
    private JButton newButton;
    /**
     * Tallenna -nappi
     */
    private JButton saveButton;
    /**
     * Lataa tallennettu peli -nappi
     */
    private JButton loadButton;
    /**
     * peru siirto -nappi
     */
    private JButton undoButton;
    
    /**
     * Asettaa atribuuteiksi parametreina annetut oliot. 
     * @param selections
     * @param controls
     * @param drawer
     * @param newButton
     * @param saveButton
     * @param loadButton
     * @param undoButton 
     */
    public ButtonListener(Selections selections , Controls controls, Drawer drawer , JButton newButton , JButton saveButton , JButton loadButton , JButton undoButton) {
        this.selections = selections;
        this.controls = controls;
        this.drawer = drawer;
        this.newButton = newButton;
        this.saveButton = saveButton;
        this.loadButton = loadButton;
        this.undoButton = undoButton;
    }
    
    
    /**
     * Käsittelee oikean paneelin nappien painamiset. 
     * @param arg0 
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        selections.unSelect();
        try {
            if (arg0.getSource() == newButton){
                controls.newGame();
                drawer.repaint();
            }
            if (arg0.getSource() == saveButton){
                controls.save();
            }
            if (arg0.getSource() == loadButton){
                controls.loadGame();
                drawer.repaint();
            }
            if (arg0.getSource() == undoButton){
                controls.undo();
                drawer.repaint();
            }
        } catch (IOException ex) {
        }
    }
}
