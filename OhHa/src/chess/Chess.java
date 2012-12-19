package chess;

import chess.board.Board;
import chess.ui.Ui;
import javax.swing.SwingUtilities;

public class Chess {
    private Board board;
    private Ui ui;
    
    public Chess(){
        this.board = new Board();
        this.ui = new Ui();
    }
    
    public void start(){
        SwingUtilities.invokeLater(ui);
    }
}
