package chess.pieces;

import chess.board.Square;
import chess.ui.graphics.PieceGraphics;
import java.awt.Graphics;
import java.io.IOException;

public class Piece {
    private Square square;
    private Side side;
    private Type type;
    private PieceGraphics pieceGraphics;
    
    public Piece(Square square , Side side , Type type) throws IOException{
        this.square = square;
        this.side = side;
        this.type = type;
        this.pieceGraphics = new PieceGraphics();
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
    
    public void paintThis(Graphics graphics){
        pieceGraphics.paintComponent(graphics, this);
    }
}
