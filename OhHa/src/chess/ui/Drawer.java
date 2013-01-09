package chess.ui;

import chess.board.Board;
import chess.board.Square;
import chess.pieces.Piece;
import chess.ui.graphics.BoardGraphics;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * piirtää laudan ja nappulat
 * 
 * @author joel
 */

public class Drawer extends JPanel{
    /**
     * Shakkilauta. 
     */
    Board board;
    /**
     * Lista korostettavista ruuduista. 
     */
    ArrayList<Square> squares;
    
    /**
     * Asettaa laudaksi parametrina annetun laudan sekä Tyhjän listan. 
     * @param board 
     */
    public Drawer(Board board){
        this.board = board;
        this.squares = new ArrayList<Square>();
        this.setMinimumSize(new Dimension(600, 600));
        this.setMaximumSize(new Dimension(600, 600));
    }
    
    /**
     * Piirtää laudan ja nappulat. 
     * @param graphics 
     */
    @Override
    protected void paintComponent(Graphics graphics){
        
        new BoardGraphics().paintComponent(graphics , squares);
        
        for (Piece piece : board.getPieces()) {
            piece.paintThis(graphics);
        }

    }
    /**
     * Asettaa korostettavat ruudut. 
     * @param list 
     */
    public void setSquares(ArrayList<Square> list){
        squares = list;
    }
    
}
    

