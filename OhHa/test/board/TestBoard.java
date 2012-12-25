package board;


import chess.pieces.Type;
import chess.board.Locator;
import chess.pieces.Piece;
import chess.board.Board;
import chess.pieces.Side;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestBoard {
    Board board;
    
    @Before
    public void setUp() {
        board = new Board();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void thereAre64Squares(){
        assertEquals(64 , board.getSquares().length * board.getSquares().length);
    }
    
    @Test
    public void thereAre32Pieces(){
        assertEquals(32 , board.getPieces().size());
    }
    
    @Test
    public void whiteKingIsInE1(){
        Piece piece = null;
        
        for (Piece p : board.getPieces()) {
            if(p.getType() == Type.KING && p.getSide() == Side.WHITE){
                piece = p;
            }
        }
        
        
        assertEquals(piece.getSquare() , board.getSquares()[4][0]);
    }
    
    @Test
    public void blackQueenIsInD8(){
        Piece piece = null;
        
        for (Piece p : board.getPieces()) {
            if(p.getType() == Type.QUEEN && p.getSide() == Side.BLACK){
                piece = p;
            }
        }
        
        assertEquals(piece.getSquare() , board.getSquares()[3][7]);
    }
    
    @Test
    public void thereAre16Pawns(){
        int sum = 0;
        for (Piece p : board.getPieces()) {
            if (p.getType() == Type.PAWN){
                sum++;
            }
        }
        assertEquals(16 , sum);
    }
    
}
