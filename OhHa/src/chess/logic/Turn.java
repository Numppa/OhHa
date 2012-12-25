package chess.logic;

import chess.pieces.Side;

public class Turn {
    private Side side;
    
    
    
    public Turn(){
        this.side = Side.WHITE;
    }
    
    public Side getSide(){
        return side;
    }
    
    public void next(){
        if (side == Side.WHITE){
            side = Side.BLACK;
        } else {
            side = Side.WHITE;
        }
    }
    
}
