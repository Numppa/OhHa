package chess.logic;

import chess.pieces.Side;

/**
 * Luokka, jonka tarkoitus on tietää vuoro. 
 * 
 * @author joel
 */

public class Turn {
    private Side side;
    
    /**
     * Asettaa side-muuuttujan arvoksi WHITE. 
     */
    public Turn(){
        this.side = Side.WHITE;
    }
    
    /**
     * Palauttaa tiedon vuorossa olevasta pelaajsta. 
     * @return 
     */
    public Side getSide(){
        return side;
    }
    
    /**
     * Asettaa vuoron toiselle pelaajalle. 
     */
    public void next(){
        if (side == Side.WHITE){
            side = Side.BLACK;
        } else {
            side = Side.WHITE;
        }
    }
    
}
