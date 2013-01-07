package chess;

import chess.pieces.Piece;
import chess.logic.Logic;
import chess.logic.Controls;
import chess.board.Board;
import chess.pieces.Type;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ControlsTest {
    private Board board;
    private Logic logic;
    private Controls controls;
    
    
    public ControlsTest() {
    }

    
    
    @Before
    public void setUp() throws IOException {
        board = new Board();
        logic = new Logic(board);
        controls = new Controls(board, logic);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void e4d5xd5c5xc6(){
        Piece piece = board.getPiece(board.getSquares()[4][1]);
        controls.makeAMove(piece, board.getSquares()[4][3] , false);
        
        assertEquals(piece, board.getPiece(board.getSquares()[4][3]));
        
        controls.makeAMove(board.getPiece(board.getSquares()[3][6]), board.getSquares()[3][4] , false);
        controls.makeAMove(board.getPiece(board.getSquares()[4][3]), board.getSquares()[3][4] , false);
        
        assertEquals(31, board.getPieces().size());
        
        controls.makeAMove(board.getPiece(board.getSquares()[2][6]), board.getSquares()[2][4] , false);
        controls.makeAMove(board.getPiece(board.getSquares()[3][4]), board.getSquares()[2][5] , false);
        
        assertEquals(30, board.getPieces().size());
    }
    
    @Test
    public void whiteDoesLongCastle(){
        controls.makeAMove(board.getPiece(board.getSquares()[1][0]), board.getSquares()[2][2] , false);
        logic.nextTurn();
        controls.makeAMove(board.getPiece(board.getSquares()[3][1]), board.getSquares()[3][3] , false);
        logic.nextTurn();
        controls.makeAMove(board.getPiece(board.getSquares()[3][0]), board.getSquares()[3][2] , false);
        logic.nextTurn();
        controls.makeAMove(board.getPiece(board.getSquares()[2][0]), board.getSquares()[3][1] , false);
        logic.nextTurn();
        controls.makeAMove(board.getPiece(board.getSquares()[4][0]), board.getSquares()[2][0] , false);
        
        assertEquals(Type.ROOK, board.getPiece(board.getSquares()[3][0]).getType());
        assertEquals(Type.KING, board.getPiece(board.getSquares()[2][0]).getType());
    }
    
    
}
