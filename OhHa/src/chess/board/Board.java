package chess.board;

import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;
import chess.pieces.Side;
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
            Pawn p = new Pawn(squares.get(i) , Side.WHITE);
            pieces.add(p);
        }
        for (int i = 48; i < 56; i++) {
            Pawn p = new Pawn(squares.get(i) , Side.BLACK);
            pieces.add(p);
        }
        
        pieces.add(new Rook(squares.get(0) , Side.WHITE));
        pieces.add(new Knight(squares.get(1) , Side.WHITE));
        pieces.add(new Bishop(squares.get(2) , Side.WHITE));
        pieces.add(new Queen(squares.get(3) , Side.WHITE));
        pieces.add(new King(squares.get(4) , Side.WHITE));
        pieces.add(new Bishop(squares.get(5) , Side.WHITE));
        pieces.add(new Knight(squares.get(6) , Side.WHITE));
        pieces.add(new Rook(squares.get(7) , Side.WHITE));
        
        pieces.add(new Rook(squares.get(56) , Side.BLACK));
        pieces.add(new Knight(squares.get(57) , Side.BLACK));
        pieces.add(new Bishop(squares.get(58) , Side.BLACK));
        pieces.add(new Queen(squares.get(59) , Side.BLACK));
        pieces.add(new King(squares.get(60) , Side.BLACK));
        pieces.add(new Bishop(squares.get(61) , Side.BLACK));
        pieces.add(new Knight(squares.get(62) , Side.BLACK));
        pieces.add(new Rook(squares.get(63) , Side.BLACK));
    }
}
