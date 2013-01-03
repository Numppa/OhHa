package chess;

import chess.ui.TextUi;
import chess.ui.Ui;
import javax.swing.SwingUtilities;

public class Chess {
    private Ui ui;
    private TextUi textUi;
    
    
    public Chess(){
        this.ui = new Ui();
        this.textUi = new TextUi();
    }
    
    public void run(){
        SwingUtilities.invokeLater(ui);
    }
    
    public void runTextMode(){
        textUi.run();
    }
}
