

import chess.board.Square;
import chess.board.Board;
import chess.board.Location;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestLocation {
    
        Location location;


    
    @Before
    public void setUp() {
        location = new Location(new Board());
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void returnsRightCordinates1(){
        Square square = location.getSquare("a1");
        assertEquals(square.getX() , 1);
        assertEquals(square.getY() , 1);
    }
    
    @Test
    public void returnsRightCordinates2(){
        Square square = location.getSquare("e4");
        assertEquals(square.getX() , 5);
        assertEquals(square.getY() , 4);
    }
}
