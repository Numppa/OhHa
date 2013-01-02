package board;

import chess.board.Board;
import chess.logic.Logic;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestLogic {
    Board board;
    Logic logic;
    
    public TestLogic() {
    }

    
    @Before
    public void setUp() {
        board = new Board();
        logic = new Logic(board);
    }
    
    @Test
    public void canStartWithE2Pawn(){
        assertEquals(2 , logic.pieceCanMoveTo(board.getPiece(board.getSquares()[4][1])).size());
    }
    
    @Test
    public void kingCantMoveOnFirstTurn(){
        assertEquals(0, logic.pieceCanMoveTo(board.getPiece(board.getSquares()[4][0])).size());
    }
    
    @Test
    public void kingCanMoveIfF1BishopIsAway(){
        board.killPiece(board.getPiece(board.getSquares()[5][0]));
        
        assertEquals(1, logic.pieceCanMoveTo(board.getPiece(board.getSquares()[4][0])).size());
    }
    
    @Test
    public void kingCanDoShortCastle(){
        board.killPiece(board.getPiece(board.getSquares()[5][0]));
        board.killPiece(board.getPiece(board.getSquares()[6][0]));
        
        assertEquals(2, logic.pieceCanMoveTo(board.getPiece(board.getSquares()[4][0])).size());
    }
    
    @Test
    public void cantCastleIfChecked(){
        board.killPiece(board.getPiece(board.getSquares()[5][0]));
        board.killPiece(board.getPiece(board.getSquares()[6][0]));
        board.killPiece(board.getPiece(board.getSquares()[4][1]));
        board.getPiece(board.getSquares()[3][7]).setSquare(board.getSquares()[4][3]);
        
        assertEquals(1, logic.pieceCanMoveTo(board.getPiece(board.getSquares()[4][0])).size());
        assertEquals(board.getSquares()[5][0] , logic.pieceCanMoveTo(board.getPiece(board.getSquares()[4][0])).get(0));
        
    }
    
    @Test
    public void pawnPreventsCheck(){
        board.getPiece(board.getSquares()[4][0]).setSquare(board.getSquares()[1][2]);
        board.getPiece(board.getSquares()[3][7]).setSquare(board.getSquares()[6][2]);
        
        assertEquals(1, logic.pieceCanMoveTo(board.getPiece(board.getSquares()[2][1])).size());
        assertEquals(2, logic.pieceCanMoveTo(board.getPiece(board.getSquares()[5][1])).size());
    }
    
    @Test
    public void knightCanMake2DifferentOpeningMoves(){
        assertEquals(2, logic.pieceCanMoveTo(board.getPiece(board.getSquares()[1][0])).size());
    }
    
    @Test
    public void cBishopMovesRightIfDpawnIsAway(){
        board.killPiece(board.getPiece(board.getSquares()[3][1]));
        assertEquals(5, logic.pieceCanMoveTo(board.getPiece(board.getSquares()[2][0])).size());
    }
    
    @Test
    public void queenCanMoveTo19DifferentSquares(){
        board.getPiece(board.getSquares()[3][0]).setSquare(board.getSquares()[4][3]);
        assertEquals(19, logic.pieceCanMoveTo(board.getPiece(board.getSquares()[4][3])).size());
    }
    
    
}
