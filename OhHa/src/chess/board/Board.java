package chess.board;

import java.util.HashSet;
import java.util.Set;

public class Board {
    private HashSet<Square> squares;
    
    public Board(){
        this.squares = new HashSet<Square>();
        
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                this.squares.add(new Square(j , i));
            }
        }
    }
    
    public Set getSquares(){
        return squares;
    }
}
