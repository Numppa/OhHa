package chess.board;


import chess.pieces.Piece;
import chess.pieces.Side;
import chess.pieces.Type;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Square> squares;
    private List<Piece> pieces;
    
    public Board(){
        this.squares = new ArrayList<Square>();
        this.pieces = new ArrayList<Piece>();
        
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                this.squares.add(new Square(j , i));
            }
        }
        
        setUpPieces();
        
    }
    
    public List<Square> getSquares(){
        return squares;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    private void setUpPieces() {
        for (int i = 8; i < 16; i++) {
            Piece p = new Piece(squares.get(i) , Side.WHITE , Type.PAWN);
            pieces.add(p);
        }
        for (int i = 48; i < 56; i++) {
            Piece p = new Piece(squares.get(i) , Side.BLACK , Type.PAWN);
            pieces.add(p);
        }
        
        pieces.add(new Piece(squares.get(0) , Side.WHITE , Type.ROOK));
        pieces.add(new Piece(squares.get(1) , Side.WHITE , Type.KNIGHT));
        pieces.add(new Piece(squares.get(2) , Side.WHITE , Type.BISHOP));
        pieces.add(new Piece(squares.get(3) , Side.WHITE , Type.QUEEN));
        pieces.add(new Piece(squares.get(4) , Side.WHITE , Type.KING));
        pieces.add(new Piece(squares.get(5) , Side.WHITE , Type.BISHOP));
        pieces.add(new Piece(squares.get(6) , Side.WHITE , Type.KNIGHT));
        pieces.add(new Piece(squares.get(7) , Side.WHITE , Type.ROOK));
        
        pieces.add(new Piece(squares.get(56) , Side.BLACK , Type.ROOK));
        pieces.add(new Piece(squares.get(57) , Side.BLACK , Type.KNIGHT));
        pieces.add(new Piece(squares.get(58) , Side.BLACK , Type.BISHOP));
        pieces.add(new Piece(squares.get(59) , Side.BLACK , Type.QUEEN));
        pieces.add(new Piece(squares.get(60) , Side.BLACK , Type.KING));
        pieces.add(new Piece(squares.get(61) , Side.BLACK , Type.BISHOP));
        pieces.add(new Piece(squares.get(62) , Side.BLACK , Type.KNIGHT));
        pieces.add(new Piece(squares.get(63) , Side.BLACK , Type.ROOK));
    }
}
