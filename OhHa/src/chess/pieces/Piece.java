package chess.pieces;

import chess.board.Square;
import chess.ui.graphics.PieceGraphics;
import java.awt.Graphics;
import java.io.IOException;

/**
 * Shakkinappula
 * 
 * @author joel
 */

public class Piece {
    /**
     * Ruutu, jossa nappula on. 
     */
    private Square square;
    /**
     * Nappulan väri. 
     */
    private Side side;
    /**
     * Nappulan tyyppi (sotilas, ratsu, lähetti, torni, kuningatar tai kuningas).
     */
    private Type type;
    /**
     * Olio, joka osaa piirtää nappulan oikeaan ruutuun. 
     */
    private PieceGraphics pieceGraphics;
    
    /**
     * Asettaa nappulalle ruudun, värin ja tyypin. Lisäksi luo nappulan piirtävän olion. 
     * @param square
     * @param side
     * @param type
     * @throws IOException 
     */
    public Piece(Square square , Side side , Type type) throws IOException{
        this.square = square;
        this.side = side;
        this.type = type;
        this.pieceGraphics = new PieceGraphics();
    }
    
    /**
     * Palauttaa ruudun, jonka päällä nappula on. 
     * @return square
     */
    public Square getSquare(){
        return square;
    }
    
    /**
     * Asettaa nappulalle uuden ruudun. 
     * Lisäksi asettaa vanhan ruudun miehityksen neutraaliksi ja uuden ruudun oman väriseksi. 
     * @param s 
     */
    public void setSquare(Square s){
        square.setSide(Side.NEUTRAL);
        square = s;
        square.setSide(side);
    }
    
    /**
     * Palauttaa nappulan värin. 
     * @return side
     */
    public Side getSide(){
        return side;
    }
    
    /**
     * Palauttaa nappulan tyypin. 
     * @return type
     */
    public Type getType(){
        return type;
    }
    
    /**
     * Asettaa nappulalle uuden tyypin. Käytetään ainoastaa sotilaan korottamiseen. 
     * @param type
     */
    public void setType(Type t){
        type = t;
    }
    
    /**
     * Nappula piirtää itsensä laudalle. 
     * 
     * @param graphics 
     */
    public void paintThis(Graphics graphics){
        pieceGraphics.paintComponent(graphics, this);
    }
}
