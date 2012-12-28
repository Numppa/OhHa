package chess.board;

public class Locator {
    private Board board;
    
    public Locator(Board board){
        this.board = board;
    }
    
    
    public Square getSquare(String location){
        int y = Integer.parseInt(location.substring(1)) - 1;
        
        int x = -1;
        
        if (location.charAt(0) == 'a') x = 0;
        if (location.charAt(0) == 'b') x = 1;
        if (location.charAt(0) == 'c') x = 2;
        if (location.charAt(0) == 'd') x = 3;
        if (location.charAt(0) == 'e') x = 4;
        if (location.charAt(0) == 'f') x = 5;
        if (location.charAt(0) == 'g') x = 6;
        if (location.charAt(0) == 'h') x = 7;
        
        try{
            return board.getSquares()[x][y];
        } catch (Exception e){
            return null;
        }
        
        
        
    }
    
}
