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
   
    /**
     * Tallentaa pelin tiedostoon. 
     * @throws IOException 
     */
    public void save() throws IOException{
        moves.save();
    }
    
    /**
     * Lataa pelin tiedostosta ja asettaa aseman laudalle. 
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void loadGame() throws FileNotFoundException, IOException{
        moves.load();
        loadPosition();
    }
    
    /**
     * Suorittaa nappulan siirtämisen. Palauttaa true, jos siirretty nappula on korottuva sotilas. Muuten palauttaa false. Loading-parametri on true, kun tallennettua peliä ladataan. Muulloin se on false. 
     * @param Piece piece
     * @param Square square
     * @param Boolean loading
     * @return boolean willPromote
     */
    public boolean makeAMove(Piece piece , Square square , boolean loading){
        boolean willPromote = false;
        blockCastlingIfNeeded(piece);
        ifMoveDoesCastle(piece, square);
        
        logic.setEnPassant(null);
        
        if (piece.getType() == Type.PAWN){
            willPromote = monitorEnPassantLongMoveAndPromotion(square, piece);
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
    
    /**
     * Aloittaa uuden pelin alkuasemasta. 
     * @throws IOException 
     */
    public void newGame() throws IOException{
        moves = new Moves();
        board.StartingPosition();
        logic.setUp();
    }
    
    /**
     * Asettaa laudan aseman siirtolokin mukaiseksi. 
     * @throws IOException 
     */
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
    
    /**
     * Peruu siirron. Siirtoja voi perua alkuasemaan asti. 
     * @throws IOException 
     */
    public void undo() throws IOException{
        moves.removeLast();
        loadPosition();
    }
    
    
    
    
    
    /**
     * Hoitaa Sotilaan siirtämiseen liittyvät poikkeustilanteet: 
     * pitkän siirron jälkeinen ohestalyöntimahdollisuus, 
     * ohestalyönti ja korottaminen. 
     * Palauttaa true, jos sotilas korottuu. Muulloin palauttaa false. 
     * @param Square square
     * @param Piece piece
     * @return boolean willPromote
     */
    private boolean monitorEnPassantLongMoveAndPromotion(Square square, Piece piece) {
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
            return true;
        }
        return false;
    }

    /**
     * Mikäli kuningas menee linnaan, niin metodi asettaa tornin viereen. 
     * @param Piece piece
     * @param Square square 
     */
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

    /**
     * Poistaa tornittamismahdollisuuden lopullisesti silloin, 
     * kun torni tai kuningas liikkuua alkuruudustaan. 
     * @param Piece piece
     */
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
    
    /**
     * Korottaa sotilaan. 
     * @param Piece piece
     * @param Type type 
     */
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
