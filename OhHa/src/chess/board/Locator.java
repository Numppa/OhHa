package chess.board;

public class Locator {
    private Board board;
    
    public Locator(Board board){
        this.board = board;
    }
    
    
    public Square getSquare(String location){
        Square square = null;
        int y = Integer.parseInt(location.substring(1));
        
        int x = 0;
        
        if (location.charAt(0) == 'a') x = 1;
        if (location.charAt(0) == 'b') x = 2;
        if (location.charAt(0) == 'c') x = 3;
        if (location.charAt(0) == 'd') x = 4;
        if (location.charAt(0) == 'e') x = 5;
        if (location.charAt(0) == 'f') x = 6;
        if (location.charAt(0) == 'g') x = 7;
        if (location.charAt(0) == 'h') x = 8;
        
        for (Square s : board.getSquares()) {
            if (x == s.getX() && y == s.getY()){
                square = s;
                break;
            }
        }
        return square;
    }
    
    public String squareToString(Square square){
        String string = "";
        
        if(square.getX() == 1) string += "a";
        if(square.getX() == 2) string += "b";
        if(square.getX() == 3) string += "c";
        if(square.getX() == 4) string += "d";
        if(square.getX() == 5) string += "e";
        if(square.getX() == 6) string += "f";
        if(square.getX() == 7) string += "g";
        if(square.getX() == 8) string += "h";
        
        string += square.getY();
        
        return string;
    }
    
}