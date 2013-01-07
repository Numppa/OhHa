package chess.ui;

import chess.logic.Controls;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Käsittelee laudan ulkopuolella olevien nappuloiden painamiset
 * 
 * @author joel
 */

public class ButtonListener implements ActionListener{
    private Selections selections;
    private Controls controls;

    @Override
    public void actionPerformed(ActionEvent arg0) {
        selections.unSelect();
        //if (arg0.getSource() == );
    }
    
}
