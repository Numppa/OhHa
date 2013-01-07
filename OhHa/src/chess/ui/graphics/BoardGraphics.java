package chess.ui.graphics;

import chess.board.Square;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

/**
 * Laudan ja ruutujen korostukset piirtävä luokka. 
 * 
 * @author joel
 */

public class BoardGraphics {
    
    
    public BoardGraphics(){
    }
    
    
    
    public void paintComponent(Graphics graphics , List<Square> squares){
        int counter = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (counter % 2 == 0){
                    graphics.setColor(Color.WHITE);
                } else {
                    graphics.setColor(Color.gray);
                }
                graphics.fillRect(j * 75, i * 75, 75, 75);
                counter++;
            }
            counter++;
        }
        graphics.setColor(Color.CYAN);
        for (Square square : squares) {
            graphics.fillRect(square.getX() * 75, 525 - (square.getY() * 75), 75, 75);
        }
    }
}
