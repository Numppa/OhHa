package chess.logic;

import chess.board.Board;
import chess.board.Square;
import chess.pieces.Piece;
import chess.pieces.Type;
import java.util.ArrayList;
import java.util.List;

public class Logic {
    private Board board;
    private List<Piece> checkers;
    private Turn turn;
    private boolean shortCastle;
    private boolean longCastle;
    
    public Logic(Board board){
        this.board = board;
        this.checkers = new ArrayList<Piece>();
        this.turn = new Turn();
        this.longCastle = true;
        this.shortCastle = true;
    }
    
    public void startOfTurn(){
        turn.next();
        checkers = new ArrayList<Piece>();
        
        //update checkers
        //is it checkmate
        
    }
    
    
    

    public List<Square> pieceCanMove(Piece piece){
        ArrayList<Square> canMove = new ArrayList<Square>();
        
        if (piece.getType() == Type.KNIGHT){
            canMove = (ArrayList<Square>) knightCanMove(piece);
        }
        if (piece.getType() == Type.BISHOP){
            canMove = (ArrayList<Square>) bishopCanMove(piece);
        }
        if (piece.getType() == Type.ROOK){
            canMove = (ArrayList<Square>) rookCanMove(piece);
        }
        if (piece.getType() == Type.QUEEN){
            canMove = (ArrayList<Square>) queenCanMove(piece);
        }
        if (piece.getType() == Type.KING){
           canMove = (ArrayList<Square>) kingCanMove(piece);
        }
        if (piece.getType() == Type.PAWN){
            canMove = (ArrayList<Square>) pawnCanMove(piece);
        }
        
        return canMove;
    }

    private List<Square> knightCanMove(Piece piece) {
        return new ArrayList<Square>();
    }

    private List<Square> bishopCanMove(Piece piece) {
        return new ArrayList<Square>();
    }

    private List<Square> rookCanMove(Piece piece) {
        return new ArrayList<Square>();
    }

    private List<Square> queenCanMove(Piece piece) {
        return new ArrayList<Square>();
    }

    private List<Square> kingCanMove(Piece piece) {
        return new ArrayList<Square>();
    }

    private List<Square> pawnCanMove(Piece piece) {
        return new ArrayList<Square>();
    }
    
    private List<Square> canDoEnPassant(Piece piece){
        return new ArrayList<Square>();
    }
    
    private List<Piece> getCheckingPieces(){
        return null;
    }
    
    
    
}
