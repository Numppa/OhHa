package chess.logic;

import chess.board.Board;
import chess.board.Square;
import chess.pieces.Piece;
import chess.pieces.Side;
import chess.pieces.Type;
import chess.saving.Moves;

public class Controls {
    private Board board;
    private Logic logic;
    private Moves moves;
    
    
    public Controls(Board board , Logic logic){
        this.board = board;
        this.logic = logic;
        this.moves = new Moves();
    }
    
    public boolean makeAMove(Piece piece , Square square){
        boolean willPromote = false;
        if (piece.getSquare().equals(board.getSquares()[0][0])){
            logic.LockLongCastleWhite();
        }
        if (piece.getSquare().equals(board.getSquares()[7][0])){
            logic.LockShortCastleWhite();
        }
        if (piece.getSquare().equals(board.getSquares()[7][0])){
            logic.LockLongCastleBlack();
        }
        if (piece.getSquare().equals(board.getSquares()[7][7])){
            logic.LockShortCastleBlack();
        }
        if (piece.getSquare().equals(board.getSquares()[4][0])){
            logic.LockLongCastleWhite();
            logic.LockShortCastleWhite();
        }
        if (piece.getSquare().equals(board.getSquares()[4][7])){
            logic.LockLongCastleBlack();
            logic.LockShortCastleBlack();
        }
        
        if (piece.getType() == Type.KING){
            if (piece.getSquare().getX() - square.getX() == 2){
                board.getPiece(board.getSquares()[0][square.getY()]).setSquare(board.getSquares()[3][square.getY()]);
            }
            if (square.getX() - piece.getSquare().getX() == 2){
                board.getPiece(board.getSquares()[7][square.getY()]).setSquare(board.getSquares()[5][square.getY()]);
            }
        }
        
        logic.setEnPassant(null);
        
        if (piece.getType() == Type.PAWN){
            if (square.getSide() == Side.NEUTRAL && square.getX() != piece.getSquare().getX()){
                board.killPiece(board.getPiece(board.getSquares()[square.getX()][piece.getSquare().getY()]));
            }
            if (square.getY() - piece.getSquare().getY() == 2){
                logic.setEnPassant(board.getSquares()[square.getX()][square.getY() - 1]);
            }
            if (square.getY() - piece.getSquare().getY() == - 2){
                logic.setEnPassant(board.getSquares()[square.getX()][square.getY() + 1]);
            }
            if (square.getY() == 0 || square.getY() == 7){
                willPromote = true;
            }
        }
        
        if (square.getSide() != Side.NEUTRAL){
            board.killPiece(board.getPiece(square));
        }
        
        piece.setSquare(square);
        
        return willPromote;
    }
    
    public void promote(Piece piece , Type type){
        piece.setType(type);
    }
    
    
    
}
