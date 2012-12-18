package chess.pieces;

import chess.board.Square;

public abstract class Piece {
    private Square square;
    private Side side;
    
    public Piece(Square square , Side side){
        this.square = square;
        this.side = side;
    }
    
    public Square getSquare(){
        return square;
    }
    
    public void setSquare(Square s){
        square = s;
    }
    
    public Side getSide(){
        return side;
    }
    
}
