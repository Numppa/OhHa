package chess.ui;

import chess.board.Board;
import chess.pieces.Piece;
import chess.ui.graphics.BoardGraphics;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Drawer extends JPanel{
    Board board;
    
    public Drawer(Board board){
        this.board = board;
        
    }
    
    
    @Override
    protected void paintComponent(Graphics graphics){
        
        new BoardGraphics().paintComponent(graphics);
        
        for (Piece piece : board.getPieces()) {
            piece.paintThis(graphics);
        }

    }
}
    

