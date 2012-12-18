package board;


import chess.board.Board;
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
        assertEquals(board.getSquares().size(), 64);
    }
}
