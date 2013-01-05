package chess.ui;

import chess.board.Board;
import chess.board.Square;
import chess.pieces.Piece;
import chess.ui.graphics.BoardGraphics;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Drawer extends JPanel{
    Board board;
    ArrayList<Square> squares;
    
    public Drawer(Board board){
        this.board = board;
        this.squares = new ArrayList<Square>();
        
    }
    
    
    @Override
    protected void paintComponent(Graphics graphics){
        
        new BoardGraphics().paintComponent(graphics , squares);
        
        for (Piece piece : board.getPieces()) {
            piece.paintThis(graphics);
        }

    }
    
    public void setSquares(ArrayList<Square> list){
        squares = list;
    }
    
}
    

