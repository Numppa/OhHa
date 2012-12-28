package chess.board;

import chess.pieces.Side;

public class Square {
    private Side side;
    private int x;
    private int y;
    
    public Square(int x , int y , Side side){
        this.x = x;
        this.y = y;
        this.side = side;
    }
    
    public Side getSide(){
        return side;
    }
    
    public void setSide(Side s){
        side = s;
    }
    
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    
    
}
