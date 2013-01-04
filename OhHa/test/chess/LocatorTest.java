package chess;



import chess.board.Square;
import chess.board.Board;
import chess.board.Locator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class LocatorTest {    
    private Board board;
    private Locator locator;


    
    @Before
    public void setUp() {
        board = new Board();
        locator = new Locator(board);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void returnsRightSquare(){
        Square square = locator.getSquare("a1");
        assertEquals(square , board.getSquares()[0][0]);
    }
    
    @Test
    public void returnsRightSquare2(){
        Square square = locator.getSquare("e4");
        assertEquals(square , board.getSquares()[4][3]);
    }
    
}
