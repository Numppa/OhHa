package chess.ui.graphics;

import java.awt.Color;
import java.awt.Graphics;

public class BoardGraphics {
    
    
    public BoardGraphics(){
    }
    
    
    
    public void paintComponent(Graphics graphics){
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
    }
}
