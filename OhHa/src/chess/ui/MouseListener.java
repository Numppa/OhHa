package chess.ui;

import chess.board.Board;
import java.awt.event.MouseEvent;

public class MouseListener implements java.awt.event.MouseListener{
    private Selections selections;
    private Board board;
    
    public MouseListener(Selections selections , Board board){
        this.selections = selections;
        this.board = board;
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        if (arg0.getX() <= 600 && arg0.getY() <= 600){
            selections.squareClicked(board.getSquares()[arg0.getX() / 75][(600 - arg0.getY()) / 75]);
        }
        if (true){
            
        }
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        
    }
    
}
