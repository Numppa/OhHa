package chess.logic;

import chess.board.Board;
import chess.board.Locator;
import chess.board.Square;

public class Logic {
    private Board board;
    private Turn turn;
    
    
    public Logic(Board board , Turn turn){
        this.board = board;
        this.turn = turn;
    }
    
    public boolean ownPieceExists(String string){
        return false;
    }
    
    
}
