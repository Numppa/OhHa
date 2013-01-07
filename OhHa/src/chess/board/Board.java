package chess.board;


import chess.pieces.Piece;
import chess.pieces.Side;
import chess.pieces.Type;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Shakkilauta
 * 
 * @author joel
 */

public class Board {
    private Square[][] squares;
    private List<Piece> pieces;
    
    public Board() throws IOException{
        this.squares = new Square[8][8];
        this.pieces = new ArrayList<Piece>();
                   
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[j][i] = new Square(j, i, Side.NEUTRAL);
            }
        }
        
        setUpPieces();    
    }
    
    /**
     * Palauttaa ruudut taulukkona
     * @return Square[][]
     */
    public Square[][] getSquares(){
        return squares;
    }
    
    /**
     * Palauttaa nappulat listana
     * @return List<Piece>
     */
    public List<Piece> getPieces() {
        return pieces;
    }
    
    /**
     * Hakee parametrina annetussa ruudussa olevan nappulan.
     * @param Square
     * @return Piece
     */
    public Piece getPiece(Square s){
        Piece p = null;
        
        for (Piece p1 : pieces) {
            if (p1.getSquare() == s){
                p = p1;
                break;
            }
        }
        return p;
    }

    private void setUpPieces() throws IOException {
        for (int i = 0; i < 8; i++) {
            Piece p = new Piece(squares[i][1] , Side.WHITE , Type.PAWN);
            pieces.add(p);
        }
        for (int i = 0; i < 8; i++) {
            Piece p = new Piece(squares[i][6] , Side.BLACK , Type.PAWN);
            pieces.add(p);
        }
        
        
        
        pieces.add(new Piece(squares[0][0] , Side.WHITE , Type.ROOK));
        pieces.add(new Piece(squares[1][0] , Side.WHITE , Type.KNIGHT));
        pieces.add(new Piece(squares[2][0] , Side.WHITE , Type.BISHOP));
        pieces.add(new Piece(squares[3][0] , Side.WHITE , Type.QUEEN));
        pieces.add(new Piece(squares[4][0] , Side.WHITE , Type.KING));
        pieces.add(new Piece(squares[5][0] , Side.WHITE , Type.BISHOP));
        pieces.add(new Piece(squares[6][0] , Side.WHITE , Type.KNIGHT));
        pieces.add(new Piece(squares[7][0] , Side.WHITE , Type.ROOK));
        
        pieces.add(new Piece(squares[0][7] , Side.BLACK , Type.ROOK));
        pieces.add(new Piece(squares[1][7] , Side.BLACK , Type.KNIGHT));
        pieces.add(new Piece(squares[2][7] , Side.BLACK , Type.BISHOP));
        pieces.add(new Piece(squares[3][7] , Side.BLACK , Type.QUEEN));
        pieces.add(new Piece(squares[4][7] , Side.BLACK , Type.KING));
        pieces.add(new Piece(squares[5][7] , Side.BLACK , Type.BISHOP));
        pieces.add(new Piece(squares[6][7] , Side.BLACK , Type.KNIGHT));
        pieces.add(new Piece(squares[7][7] , Side.BLACK , Type.ROOK));
        
        for (Piece piece : pieces) {
            piece.setSquare(piece.getSquare());
        }
    }
    
    /**
     * Poistaa nappulan pelistä
     * @param piece 
     */
    public void killPiece(Piece piece){
        piece.getSquare().setSide(Side.NEUTRAL);
        pieces.remove(piece);
    }
    
    
    /**
     * Palauttaa alkuaseman laudalle.
     * @throws IOException 
     */
    public void StartingPosition() throws IOException{
        pieces.removeAll(pieces);
        for (Square[] squares1 : squares) {
            for (Square square : squares1) {
                square.setSide(Side.NEUTRAL);
            }
        }
        setUpPieces();
    }
    

}
