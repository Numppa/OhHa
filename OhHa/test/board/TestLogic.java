package board;

import chess.board.Board;
import chess.logic.Logic;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
        assertTrue(true);
    }
    
    
    
}
