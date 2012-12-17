package chess;

import chess.ui.Ui;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        Ui ui = new Ui();
        SwingUtilities.invokeLater(ui);
    }
}
