package board;



import chess.board.Square;
import chess.board.Board;
import chess.board.Locator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestLocator {
    
        Locator locator;


    
    @Before
    public void setUp() {
        locator = new Locator(new Board());
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void returnsRightCordinates1(){
        Square square = locator.getSquare("a1");
        assertEquals(square.getX() , 1);
        assertEquals(square.getY() , 1);
    }
    
    @Test
    public void returnsRightCordinates2(){
        Square square = locator.getSquare("e4");
        assertEquals(square.getX() , 5);
        assertEquals(square.getY() , 4);
    }
    
    @Test
    public void returnsRightString(){
        Board board = new Board();
        Locator l = new Locator(board);
        
        String string = l.squareToString(board.getSquares().get(17));
        
        assertEquals("b3", string);
    }
}
