package chess.ui;

import chess.board.Board;
import java.awt.event.MouseEvent;

/**
 * Kuuntelee lautaan kohdistuvia klikkauksia. 
 * 
 * @author joel
 */

public class MouseListener implements java.awt.event.MouseListener{
    /**
     * käsittelee lautaan kohdistuneet klikkaukset. 
     */
    private Selections selections;
    /**
     * Shakkilauta. 
     */
    private Board board;
    
    /**
     * Asettaa selecions ja board -olioksi parametreina annetut oliot. 
     * @param selections
     * @param board 
     */
    public MouseListener(Selections selections , Board board){
        this.selections = selections;
        this.board = board;
    }

    /**
     * Käsittelee hiiren klikkaukset. Kutsuu selecions-oliota, mikäli klikkaus kohdistuu lautaan. 
     * @param arg0 
     */
    @Override
    public void mouseClicked(MouseEvent arg0) {
        if (arg0.getX() < 600 && arg0.getY() < 600){
            selections.squareClicked(board.getSquares()[arg0.getX() / 75][(600 - arg0.getY()) / 75]);
        }
    }
    
    /**
     * Kun hiirtä painetaan pohjassa. Ei tee mitään, mutta voi olla hyödyllinen, jos ohjelmaa laajentaa. 
     * @param arg0 
     */
    @Override
    public void mousePressed(MouseEvent arg0) {
    }
    /**
     * Kun Hiiren painike vapautetaan. Ei tee Mitään.
     * @param arg0 
     */
    @Override
    public void mouseReleased(MouseEvent arg0) {
        
    }
    /**
     * Ei tee mitään. 
     * @param arg0 
     */
    @Override
    public void mouseEntered(MouseEvent arg0) {
    }
    /**
     * Ei Tee mitään. 
     * @param arg0 
     */
    @Override
    public void mouseExited(MouseEvent arg0) {
    }
}
