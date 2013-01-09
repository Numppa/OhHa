package chess;

import java.io.IOException;

/**
 * Pääohjelma. 
 * @author joel
 */
public class Main {
    
    /**
     * Luo uuden shakkiolion ja suorittaa sen graafisessa käyttöliittymässä. 
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        Chess chess = new Chess();
        chess.run();
        //chess.runTextMode();
        
    }
}
