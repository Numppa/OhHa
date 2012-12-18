package chess;

import chess.board.Board;
import chess.board.Locator;
import chess.board.Square;
import chess.ui.Ui;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        Ui ui = new Ui();
        SwingUtilities.invokeLater(ui);
        

    }
}
