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
    private Selections selections;
    private Controls controls;
    private Drawer drawer;
    private JButton newButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton undoButton;

    public ButtonListener(Selections selections , Controls controls, Drawer drawer , JButton newButton , JButton saveButton , JButton loadButton , JButton undoButton) {
        this.selections = selections;
        this.controls = controls;
        this.drawer = drawer;
        this.newButton = newButton;
        this.saveButton = saveButton;
        this.loadButton = loadButton;
        this.undoButton = undoButton;
    }
    
    

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
