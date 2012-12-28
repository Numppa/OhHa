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
    
    
    
}
