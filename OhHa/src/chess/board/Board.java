package chess.board;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Square> squares;
    
    public Board(){
        this.squares = new ArrayList<Square>();
        
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                this.squares.add(new Square(j , i));
            }
        }
    }
    
    public List<Square> getSquares(){
        return squares;
    }
}
