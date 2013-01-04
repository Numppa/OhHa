package chess.ui.graphics;

import chess.pieces.Side;
import java.awt.Color;
import java.awt.Graphics;

public class PawnGraphics extends PieceGraphics{
    Side side;
    
    public PawnGraphics(Side side){
        this.side = side;
    }
    
    @Override
    public void paintComponent(Graphics graphics){
        if (side == Side.WHITE){
            graphics.setColor(Color.LIGHT_GRAY);
        } else {
            graphics.setColor(Color.BLACK);
        }
        graphics.fillRect(0, 0, 100, 100);
    }
}
