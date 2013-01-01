package chess.pieces;

import chess.board.Square;

public class Piece {
    private Square square;
    private Side side;
    private Type type;
    
    public Piece(Square square , Side side , Type type){
        this.square = square;
        this.side = side;
        this.type = type;
    }
    
    public Square getSquare(){
        return square;
    }
    
    public void setSquare(Square s){
        square.setSide(Side.NEUTRAL);
        square = s;
        square.setSide(side);
    }
    
    public Side getSide(){
        return side;
    }
    
    public Type getType(){
        return type;
    }
    
    public void setType(Type t){
        type = t;
    }
    
}
