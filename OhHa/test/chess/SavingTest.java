package chess;

import chess.pieces.Piece;
import chess.logic.Logic;
import chess.board.Board;
import chess.logic.Controls;
import chess.pieces.Side;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SavingTest {
    Board board;
    Logic logic;
    Controls controls;
    
    public SavingTest() {
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
    
    
    @Test
    public void undo(){
        controls.makeAMove(board.getPiece(board.getSquares()[4][1]), board.getSquares()[4][3], false);
        controls.undo();
        
        assertEquals(Side.NEUTRAL, board.getSquares()[4][3].getSide());
        assertEquals(Side.WHITE, board.getSquares()[4][1].getSide());
    }
    
    @Test
    public void saveGame() throws IOException{
        controls.makeAMove(board.getPiece(board.getSquares()[4][1]), board.getSquares()[4][3], false);
        controls.makeAMove(board.getPiece(board.getSquares()[4][6]), board.getSquares()[4][4], false);
        controls.save();
        
        controls.makeAMove(board.getPiece(board.getSquares()[5][1]), board.getSquares()[5][3], false);
        controls.loadGame();
        
        assertEquals(Side.NEUTRAL, board.getSquares()[5][3].getSide());
        assertEquals(Side.BLACK, board.getSquares()[3][6].getSide());
    }
}
