package chess;

import chess.board.Board;
import chess.logic.Logic;
import chess.ui.TextUi;
import chess.ui.Ui;
import javax.swing.SwingUtilities;

public class Chess {
    private Ui ui;
    private TextUi textUi;
    private Board board;
    
    
    public Chess(){
        this.board = new Board(); 
        this.ui = new Ui();
        this.textUi = new TextUi(board);
    }
    
    public void run(){
        SwingUtilities.invokeLater(ui);
    }
    
    public void runTextMode(){
        textUi.run();
    }
}
