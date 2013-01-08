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
    private boolean squareSelected;
    private Square square;
    private Logic logic;
    private Controls controls;
    private Board board;
    private Drawer drawer;
    
    public Selections(Logic logic , Controls controls , Board board , Drawer drawer){
        this.logic = logic;
        this.controls = controls;
        this.board = board;
        this.squareSelected = false;
        this.drawer = drawer;
        this.square = new Square(-1, -1, Side.NEUTRAL);
    }
    
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
        }
    }


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
    
    public void unSelect(){
        squareSelected = false;
    }

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
