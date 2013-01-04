package chess;

import chess.pieces.Piece;
import chess.board.Board;
import chess.logic.Logic;
import chess.pieces.Side;
import chess.pieces.Type;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LogicTest {
    Board board;
    Logic logic;
    
    public LogicTest() {
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
    
    @Test
    public void itIsntCheckmate(){
        assertFalse(logic.checkmate());
    }
    
    @Test
    public void itIsCheckmate(){
        board.getPiece(board.getSquares()[0][7]).setSquare(board.getSquares()[4][4]);
        board.getPiece(board.getSquares()[7][7]).setSquare(board.getSquares()[4][5]);
        board.getPiece(board.getSquares()[3][7]).setSquare(board.getSquares()[4][3]);
        board.getPiece(board.getSquares()[4][0]).setSquare(board.getSquares()[7][4]);
        assertTrue(logic.checkmate());
    }
    
    @Test
    public  void itIsntStalemate(){
        assertFalse(logic.stalemate());
    }
    
    @Test
    public void itIsStalemate(){
        ArrayList<Piece> willBeKilled = new ArrayList<Piece>();
        for (Piece piece : board.getPieces()) {
            if (piece.getType() != Type.KING && piece.getType() != Type.QUEEN){
                willBeKilled.add(piece);
            } else if (piece.getSide() == Side.WHITE && piece.getType() == Type.QUEEN){
                willBeKilled.add(piece);
            }
        }
        board.getPieces().removeAll(willBeKilled);
        
        board.getPiece(board.getSquares()[4][0]).setSquare(board.getSquares()[0][0]);
        board.getPiece(board.getSquares()[3][7]).setSquare(board.getSquares()[1][3]);
        board.getPiece(board.getSquares()[4][7]).setSquare(board.getSquares()[2][1]);
        
        logic.stalemate();
        
        assertTrue(logic.stalemate());
        assertFalse(logic.checkmate());
    }
    
    @Test
    public void enPassantWorks(){
        logic.setEnPassant(board.getSquares()[1][2]);
        
        assertEquals(3, logic.pieceCanMoveTo(board.getPiece(board.getSquares()[0][1])).size());
    }
}
