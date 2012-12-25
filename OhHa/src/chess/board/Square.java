package chess.board;

import chess.pieces.Side;

public class Square {
    private Side side;
    
    public Square(Side side){
        this.side = side;
    }
    
    public Side getSide(){
        return side;
    }
    
    public void setSide(Side s){
        side = s;
    }
    
    
}
