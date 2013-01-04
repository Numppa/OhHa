package chess.ui.graphics;

import chess.pieces.Piece;
import chess.pieces.Side;
import chess.pieces.Type;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PieceGraphics {
    private BufferedImage blackPawn;
    private BufferedImage whitePawn;
    private BufferedImage blackRook;
    private BufferedImage whiteRook;
    private BufferedImage blackKnight;
    private BufferedImage whiteKnight;
    private BufferedImage blackBishop;
    private BufferedImage whiteBishop;
    private BufferedImage blackKing;
    private BufferedImage whiteKing;
    private BufferedImage blackQueen;
    private BufferedImage whiteQueen;
    
    public PieceGraphics() throws IOException{
        this.blackPawn = ImageIO.read(new File("src/chess/ui/graphics/blackpawn.png")); 
        this.whitePawn = ImageIO.read(new File("src/chess/ui/graphics/whitepawn.png"));
        this.blackRook = ImageIO.read(new File("src/chess/ui/graphics/blackrook.png")); 
        this.whiteRook = ImageIO.read(new File("src/chess/ui/graphics/whiterook.png"));
        this.blackKnight = ImageIO.read(new File("src/chess/ui/graphics/blackknight.png")); 
        this.whiteKnight = ImageIO.read(new File("src/chess/ui/graphics/whiteknight.png"));
        this.blackBishop = ImageIO.read(new File("src/chess/ui/graphics/blackbishop.png")); 
        this.whiteBishop = ImageIO.read(new File("src/chess/ui/graphics/whitebishop.png"));
        this.blackKing = ImageIO.read(new File("src/chess/ui/graphics/blackking.png")); 
        this.whiteKing = ImageIO.read(new File("src/chess/ui/graphics/whiteking.png"));
        this.blackQueen = ImageIO.read(new File("src/chess/ui/graphics/blackqueen.png")); 
        this.whiteQueen = ImageIO.read(new File("src/chess/ui/graphics/whitequeen.png"));
    }
    
    
    public void paintComponent(Graphics graphics , Piece piece){
        if (piece.getSide() == Side.WHITE){
            paintWhites(graphics , piece);
        } else {
            paintBlacks(graphics , piece);
        }
    }

    private void paintWhites(Graphics graphics, Piece piece) {
        if (piece.getType() == Type.PAWN){
            graphics.drawImage(whitePawn, piece.getSquare().getX() * 75, 525 - (piece.getSquare().getY() * 75) , null);
        }
        if (piece.getType() == Type.ROOK){
            graphics.drawImage(whiteRook, piece.getSquare().getX() * 75, 525 - (piece.getSquare().getY() * 75) , null);
        }
        if (piece.getType() == Type.KNIGHT){
            graphics.drawImage(whiteKnight, piece.getSquare().getX() * 75, 525 - (piece.getSquare().getY() * 75) , null);
        }
        if (piece.getType() == Type.BISHOP){
            graphics.drawImage(whiteBishop, piece.getSquare().getX() * 75, 525 - (piece.getSquare().getY() * 75) , null);
        }
        if (piece.getType() == Type.KING){
            graphics.drawImage(whiteKing, piece.getSquare().getX() * 75, 525 - (piece.getSquare().getY() * 75) , null);
        }
        if (piece.getType() == Type.QUEEN){
            graphics.drawImage(whiteQueen, piece.getSquare().getX() * 75, 525 - (piece.getSquare().getY() * 75) , null);
        }
    }

    private void paintBlacks(Graphics graphics, Piece piece) {
        if (piece.getType() == Type.PAWN){
            graphics.drawImage(blackPawn, piece.getSquare().getX() * 75, 525 - (piece.getSquare().getY() * 75) , null);
        }
        if (piece.getType() == Type.ROOK){
            graphics.drawImage(blackRook, piece.getSquare().getX() * 75, 525 - (piece.getSquare().getY() * 75) , null);
        }
        if (piece.getType() == Type.KNIGHT){
            graphics.drawImage(blackKnight, piece.getSquare().getX() * 75, 525 - (piece.getSquare().getY() * 75) , null);
        }
        if (piece.getType() == Type.BISHOP){
            graphics.drawImage(blackBishop, piece.getSquare().getX() * 75, 525 - (piece.getSquare().getY() * 75) , null);
        }
        if (piece.getType() == Type.KING){
            graphics.drawImage(blackKing, piece.getSquare().getX() * 75, 525 - (piece.getSquare().getY() * 75) , null);
        }
        if (piece.getType() == Type.QUEEN){
            graphics.drawImage(blackQueen, piece.getSquare().getX() * 75, 525 - (piece.getSquare().getY() * 75) , null);
        }
    }
}
