package chess.ui;

import chess.board.Board;
import chess.board.Square;
import chess.logic.Controls;
import chess.logic.Logic;
import chess.pieces.Piece;
import chess.pieces.Side;
import chess.pieces.Type;
import java.awt.Frame;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Käsittelee tapahtumankuuntelijoilta saadun infon
 * 
 * @author joel
 */

public class Selections {
    /**
     * Kertoo onko ruutu valittu. 
     */
    private boolean squareSelected;
    /**
     * Ruutu-olio, joka on viimeisin valittu ruutu. 
     */
    private Square square;
    /**
     * Sovelluslogiikka. 
     */
    private Logic logic;
    /**
     * Siirtotoiminnallisuus.
     */
    private Controls controls;
    /**
     * Shakkilauta
     */
    private Board board;
    /**
     * Laudan ja nappuloiden piirtäjä. 
     */
    private Drawer drawer;
    
    /**
     * Asettaa sovelluslogiikaksi, siirtotoiminnallisuudeksi , laudaksi ja piirtäjäksi parametrina annetut oliot. 
     * @param logic
     * @param controls
     * @param board
     * @param drawer 
     */
    public Selections(Logic logic , Controls controls , Board board , Drawer drawer){
        this.logic = logic;
        this.controls = controls;
        this.board = board;
        this.squareSelected = false;
        this.drawer = drawer;
        this.square = new Square(-1, -1, Side.NEUTRAL);
    }
    
    /**
     * Toteuttaa mahdolliset tapahtumat, mitä seuraa ruudun klikkaamisesta. 
     * @param square 
     */
    public void squareClicked(Square sq){
        if (squareSelected){
            if (sq.equals(square)){
                squareSelected = false;
                drawer.setSquares(new ArrayList<Square>());
                drawer.repaint();
                return;
            }
            tryMoving(sq);
        } else {
            trySelecting(sq);
        }
    }
    
    /**
     * Siirtää valitun nappulan annettuun ruutuun,  jos tämä on mahdollista.
     * @param Square sq 
     */
    private void tryMoving(Square sq) {
        Piece piece = board.getPiece(square);
        if (logic.pieceCanMoveTo(piece).contains(sq)){
            boolean promotion = controls.makeAMove(piece, sq, false);
            squareSelected = false;
            if (promotion){
                drawer.repaint();
                promotePawn(piece);
            }
            drawer.setSquares(new ArrayList<Square>());
            drawer.repaint();
            if (logic.checkmate()){
                logic.nextTurn();
                JOptionPane.showMessageDialog(new Frame(), "Game over " + logic.getTurn().getSide() + " wins", "Checkmate" , JOptionPane.YES_OPTION);
                logic.nextTurn();
            }
            if (logic.stalemate()){
                JOptionPane.showMessageDialog(new Frame(), "Tie", "Stalemate", JOptionPane.YES_OPTION);
            }
        }
    }


    /**
     * Valitsee ruudun, jos siinä on oma nappula ja se pystyy liikkumaan. 
     * @param Square sq 
     */
    private void trySelecting(Square sq) {
        if (sq.getSide() == logic.getTurn().getSide() && logic.pieceCanMoveTo(board.getPiece(sq)).size() > 0){
            square = sq;
            squareSelected = true;
            ArrayList<Square> squares = logic.pieceCanMoveTo(board.getPiece(sq));
            squares.add(sq);
            drawer.setSquares(squares);
            drawer.repaint();
        }
    }
    
    /**
     * Poistaa ruudun valinnan. Käytetään silloin, kun oikean paneelin painikkeita klikataan. 
     */
    public void unSelect(){
        squareSelected = false;
    }

    /**
     * Korotetaan sotilas, kun se saapuu viimeiselle riville. 
     * @param Piece piece 
     */
    private void promotePawn(Piece piece) {
        int n = -1;
        Object[] options = {"Queen" , "Rook" , "Knight" , "Bishop"};
        while (n == -1){
            n = JOptionPane.showOptionDialog(new Frame(), "Promote pawn to", "Promotion", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);
        }
        if (n == 0){
            controls.promote(piece , Type.QUEEN);
        }
        if (n == 1){
            controls.promote(piece , Type.ROOK);
        }
        if (n == 2){
            controls.promote(piece , Type.KNIGHT);
        }
        if (n == 3){
            controls.promote(piece , Type.BISHOP);
        }
        drawer.repaint();
    }
}
