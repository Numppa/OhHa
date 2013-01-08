package chess.logic;

import chess.board.Board;
import chess.board.Square;
import chess.pieces.Piece;
import chess.pieces.Side;
import chess.pieces.Type;
import chess.saving.Moves;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Huolehtii siirron suorittamiseen liittyvistä toimenpiteistä. 
 * 
 * @author joel
 */

public class Controls {
    private Board board;
    private Logic logic;
    private Moves moves;
    
    
    public Controls(Board board , Logic logic){
        this.board = board;
        this.logic = logic;
        this.moves = new Moves();
    }
    
    public void save() throws IOException{
        moves.save();
    }
    
    public void loadGame() throws FileNotFoundException, IOException{
        moves.load();
        loadPosition();
    }
    
    public boolean makeAMove(Piece piece , Square square , boolean loading){
        boolean willPromote = false;
        blockCastlingIfNeeded(piece);
        ifMoveDoesCastle(piece, square);
        
        logic.setEnPassant(null);
        
        if (piece.getType() == Type.PAWN){
            willPromote = monitorEnPassantLongMoveAndPromotion(square, piece, willPromote);
        }
        
        if (square.getSide() != Side.NEUTRAL){
            board.killPiece(board.getPiece(square));
        }
        
        if (!loading){
            moves.addMove(piece, square);
        }
        
        piece.setSquare(square);
        logic.nextTurn();
        
        return willPromote;
    }
    
    public void newGame() throws IOException{
        moves = new Moves();
        board.StartingPosition();
        logic.setUp();
    }
    
    public void loadPosition() throws IOException{
        board.StartingPosition();
        logic.setUp();
        
        for (String string : moves.getLog()) {
            Piece piece = board.getPiece(board.getSquares()[Integer.parseInt(string.substring(0, 1))][Integer.parseInt(string.substring(1, 2))]);
            Square square = board.getSquares()[Integer.parseInt(string.substring(2, 3))][Integer.parseInt(string.substring(3, 4))];
            makeAMove(piece, square, true);
            if (string.charAt(string.length() - 1) == 'q'){
                piece.setType(Type.QUEEN);
            }
            if (string.charAt(string.length() - 1) == 'r'){
                piece.setType(Type.ROOK);
            }
            if (string.charAt(string.length() - 1) == 'k'){
                piece.setType(Type.KNIGHT);
            }
            if (string.charAt(string.length() - 1) == 'b'){
                piece.setType(Type.BISHOP);
            }
        }
    }
    
    public void undo() throws IOException{
        moves.removeLast();
        loadPosition();
    }
    
    
    

    private boolean monitorEnPassantLongMoveAndPromotion(Square square, Piece piece, boolean willPromote) {
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
        return willPromote;
    }

    private void ifMoveDoesCastle(Piece piece, Square square) {
        if (piece.getType() == Type.KING){
            if (piece.getSquare().getX() - square.getX() == 2){
                board.getPiece(board.getSquares()[0][square.getY()]).setSquare(board.getSquares()[3][square.getY()]);
            }
            if (square.getX() - piece.getSquare().getX() == 2){
                board.getPiece(board.getSquares()[7][square.getY()]).setSquare(board.getSquares()[5][square.getY()]);
            }
        }
    }

    private void blockCastlingIfNeeded(Piece piece) {
        if (piece.getSquare().equals(board.getSquares()[0][0])){
            logic.blockLongCastleWhite();
        }
        if (piece.getSquare().equals(board.getSquares()[7][0])){
            logic.blockShortCastleWhite();
        }
        if (piece.getSquare().equals(board.getSquares()[7][0])){
            logic.blockLongCastleBlack();
        }
        if (piece.getSquare().equals(board.getSquares()[7][7])){
            logic.blockShortCastleBlack();
        }
        if (piece.getSquare().equals(board.getSquares()[4][0])){
            logic.blockLongCastleWhite();
            logic.blockShortCastleWhite();
        }
        if (piece.getSquare().equals(board.getSquares()[4][7])){
            logic.blockLongCastleBlack();
            logic.blockShortCastleBlack();
        }
    }
    
    public void promote(Piece piece , Type type){
        piece.setType(type);
        String lastMove = moves.getLog().get(moves.getLog().size() - 1);
        moves.removeLast();
        if (type == Type.QUEEN){
            lastMove += "q";
        }
        if (type == Type.ROOK){
            lastMove += "r";
        }
        if (type == Type.KNIGHT){
            lastMove += "k";
        }
        if (type == Type.BISHOP){
            lastMove += "b";
        }
        moves.addString(lastMove);
    }
    
    
    
}
