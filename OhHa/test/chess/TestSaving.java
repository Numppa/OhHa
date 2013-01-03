package chess;

import chess.logic.Logic;
import chess.board.Board;
import chess.logic.Controls;
import chess.pieces.Side;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestSaving {
    Board board;
    Logic logic;
    Controls controls;
    
    public TestSaving() {
    }

    
    @Before
    public void setUp() {
        board = new Board();
        logic = new Logic(board);
        controls = new Controls(board, logic);
    }
    
    
    @Test
    public void oneMove(){
        controls.makeAMove(board.getPiece(board.getSquares()[4][1]), board.getSquares()[4][3], false);
        controls.loadPosition();
        
        assertEquals(Side.WHITE, board.getSquares()[4][3].getSide());
    }
    
    public void undo(){
        oneMove();
        controls.undo();
        
        assertEquals(Side.NEUTRAL, board.getSquares()[4][3].getSide());
        assertEquals(Side.WHITE, board.getSquares()[4][1].getSide());
    }
    
    
}
