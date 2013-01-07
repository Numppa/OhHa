package chess.ui;

import chess.board.Board;
import chess.board.Square;
import chess.logic.Controls;
import chess.logic.Logic;
import chess.pieces.Piece;
import chess.pieces.Side;
import java.util.ArrayList;

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
    private boolean promotion;
    private Piece willBePromoted;
    
    public Selections(Logic logic , Controls controls , Board board , Drawer drawer){
        this.logic = logic;
        this.controls = controls;
        this.board = board;
        this.squareSelected = false;
        this.drawer = drawer;
        this.square = new Square(-1, -1, Side.NEUTRAL);
        this.promotion = false;
        this.willBePromoted = null;
    }
    
    public void squareClicked(Square sq){
        if (promotion){
            return;
        }
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
            promotion = controls.makeAMove(piece, sq, false);
            squareSelected = false;
            if (promotion){
                willBePromoted = piece;
            }
            drawer.setSquares(new ArrayList<Square>());
            drawer.repaint();
        }
    }

    private void promotePawn(Piece piece) {
        throw new UnsupportedOperationException("Not yet implemented");
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
    
    
}
