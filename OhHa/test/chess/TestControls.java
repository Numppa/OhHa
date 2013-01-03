package chess;

import chess.pieces.Piece;
import chess.logic.Logic;
import chess.logic.Controls;
import chess.board.Board;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestControls {
    private Board board;
    private Logic logic;
    private Controls controls;
    
    
    public TestControls() {
    }

    
    
    @Before
    public void setUp() {
        board = new Board();
        logic = new Logic(board);
        controls = new Controls(board, logic);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void e4d5exd5c5exc6(){
        Piece piece = board.getPiece(board.getSquares()[4][1]);
        controls.makeAMove(piece, board.getSquares()[4][3]);
        
        assertEquals(piece, board.getPiece(board.getSquares()[4][3]));
        
        logic.startOfTurn();
        controls.makeAMove(board.getPiece(board.getSquares()[3][6]), board.getSquares()[3][4]);
        logic.startOfTurn();
        controls.makeAMove(board.getPiece(board.getSquares()[4][3]), board.getSquares()[3][4]);
        
        assertEquals(31, board.getPieces().size());
        
        logic.startOfTurn();
        controls.makeAMove(board.getPiece(board.getSquares()[2][6]), board.getSquares()[2][4]);
        logic.startOfTurn();
        controls.makeAMove(board.getPiece(board.getSquares()[3][4]), board.getSquares()[2][5]);
        
        assertEquals(30, board.getPieces().size());
    }
    
    
}
