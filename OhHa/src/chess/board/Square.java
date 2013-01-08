package chess.board;

import chess.pieces.Side;

/**
 * Shakkilaudan ruutu. 
 * 
 * @author joel
 */

public class Square {
    private Side side;
    private int x;
    private int y;
    
    public Square(int x , int y , Side side){
        this.x = x;
        this.y = y;
        this.side = side;
    }

    /**
     * Palauttaa tiedon siitä, minkä värinen nappula on ruudussa tai onko ruudussa nappulaa. 
     * @return Side
     */
    public Side getSide(){
        return side;
    }
    
    /**
     * Asettaa ruudulle tiedon mahdollisesta nappulasta sen päällä. 
     * @param Side 
     */
    public void setSide(Side s){
        side = s;
    }
    
    /**
     * palauttaa ruudun x-koordinaatin. Arvo on välillä 0-7. 
     * @return int x
     */
    public int getX(){
        return x;
    }
    
    /**
     * Palauttaa ruudun y-koordinaatin. Arvo on välillä 0-7. 
     * @return int y
     */
    public int getY(){
        return y;
    }
    
   
    /**
     * Palauttaa koordinaatit merkkijonona. Tätä hyödynnetään tallentamisessa. 
     * @return 
     */
    @Override
    public String toString(){
        return (this.x + "" + this.y);
    }
}
