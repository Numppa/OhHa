package chess;

import chess.ui.TextUi;
import chess.ui.Ui;
import java.io.IOException;
import javax.swing.SwingUtilities;

/**
 * Luo uuden shakkipelin
 * 
 * @author Joel
 */

public class Chess {
    /**
     * Graafinen käyttöliittymä. 
     */
    private Ui ui;
    /**
     * Tekstikäyttöliittymä. 
     */
    private TextUi textUi;
    
    /**
     * Luo uuden graafisen ja tekstikäyttöliittymän. 
     * @throws IOException 
     */
    public Chess() throws IOException{
        this.ui = new Ui();
        this.textUi = new TextUi();
    }
    
    /**
     * Suorittaa graafisen käyttöliittymän
     */
    public void run(){
        SwingUtilities.invokeLater(ui);
    }
    
    /**
     * Suorittaa tekstikäyttöliittymän
     * @throws IOException 
     */
    public void runTextMode() throws IOException{
        textUi.run();
    }
}
