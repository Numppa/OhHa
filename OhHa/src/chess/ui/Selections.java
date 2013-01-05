package chess.ui;

import chess.board.Board;
import chess.board.Square;
import chess.logic.Controls;
import chess.logic.Logic;
import chess.pieces.Piece;
import chess.pieces.Side;

public class Selections {
    private boolean squareSelected;
    private Square square;
    private Logic logic;
    private Controls controls;
    private Board board;
    
    public Selections(Logic logic , Controls controls , Board board){
        this.logic = logic;
        this.controls = controls;
        this.board = board;
        this.squareSelected = false;
        this.square = new Square(-1, -1, Side.NEUTRAL);
    }
    
    public void squareClicked(Square sq){
        if (squareSelected){
            if (sq.equals(square)){
                squareSelected = false;
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
            System.out.println("jee2");
            if (promotion){
                promotePawn(piece);
            }
        }
    }

    private void promotePawn(Piece piece) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void trySelecting(Square sq) {
        if (sq.getSide() == logic.getTurn().getSide() && logic.pieceCanMoveTo(board.getPiece(sq)).size() > 0){
            square = sq;
            squareSelected = true;
            System.out.println("jee");
        }
    }
    
    
}
