package chess;

import chess.ui.TextUi;
import chess.ui.Ui;
import java.io.IOException;
import javax.swing.SwingUtilities;

public class Chess {
    private Ui ui;
    private TextUi textUi;
    
    
    public Chess() throws IOException{
        this.ui = new Ui();
       // this.textUi = new TextUi();
    }
    
    public void run(){
        SwingUtilities.invokeLater(ui);
    }
    
    public void runTextMode() throws IOException{
        textUi.run();
    }
}
