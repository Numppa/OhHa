package chess.saving;

import chess.board.Square;
import chess.pieces.Piece;
import java.util.ArrayList;
import java.util.List;

public class Moves {
    ArrayList<String> log;
    
    public Moves(){
        this.log = new ArrayList<String>();
    }
    
    public void addMove(Piece piece , Square square){
        log.add(piece.getSquare().toString() + "" + square.toString());
    }
    
    public void removeLast(){
        if (log.size() > 0){
            log.remove(log.size() - 1);
        }
    }
    
    public List<String> getLog(){
        return log;
    }
    
    
    
}
