package chess.board;

import chess.pieces.Side;

/**
 * Shakkilaudan ruutu. 
 * 
 * @author joel
 */

public class Square {
    /**
     * Ruudun miehitys (musta , valkoinen tai neutraali). 
     */
    private Side side;
    /**
     * Ruudun x-koordinaatti (välillä 0-7).
     */
    private int x;
    /**
     * Ruudun y-koordinaatti (välillä 0-7). 
     */
    private int y;
    
    /**
     * Asettaa ruudulle koordinaatit sekä tiedon mahdollisesta miehityksestä. 
     * @param x
     * @param y
     * @param side 
     */
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
     * @param side 
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
     * @return xy
     */
    @Override
    public String toString(){
        return (this.x + "" + this.y);
    }
}
