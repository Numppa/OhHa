package chess.logic;

import chess.board.Board;
import chess.board.Square;
import chess.pieces.Piece;
import chess.pieces.Side;
import chess.pieces.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Sovelluslogiikka
 * 
 * @author joel
 */

public class Logic {
    /**
     * Shakkilauta. 
     */
    private Board board;
    /**
     * Vuoro-olio, jonka ainut tehtävä on muistaa kumman vuoro on. 
     */
    private Turn turn;
    /**
     * Kertoo, voiko valkoinen mennä lyhyeeseen linnaan pelin aikana. 
     */
    private boolean shortCastleWhite;
    /**
     * Kertoo, voiko valkoinen mennä pitkään linnaan pelin aikana. 
     */
    private boolean longCastleWhite;
    /**
     * Kertoo, voiko musta mennä lyhyeeseen linnaan pelin aikana. 
     */
    private boolean shortCastleBlack;
    /**
     * Kertoo, voiko musta mennä pitkään linnaan pelin aikana. 
     */
    private boolean longCastleBlack;
    /**
     * Ruutu johon siirtymällä sotilas lyö ohesta. Null, jos ruutua ei ole. 
     */
    private Square enPassant;
    
    /**
     * Asettaa logiikan laudaksi parametrina annetun laudan sekä luo muut logiikan tarvitsemat muuttujat. 
     * @param board 
     */
    public Logic(Board board){
        this.board = board;
        this.turn = new Turn();
        this.longCastleWhite = true;
        this.shortCastleWhite = true;
        this.longCastleBlack = true;
        this.shortCastleBlack = true;
        this.enPassant = null;
    }
    /**
     * Antaa vuoron toiselle pelaajalle. 
     */
    public void nextTurn(){
        turn.next();
    }
    
    /**
     * Asettaa logiikan alkutilanteeseen. 
     */
    public void setUp(){
        enPassant = null;
        longCastleBlack = true;
        longCastleWhite = true;
        shortCastleBlack = true;
        shortCastleWhite = true;
        if (turn.getSide() == Side.BLACK){
            turn.next();
        }
    }
    
    /**
     * Asettaa ruudun, josta voidaan lyödä ohesta. 
     * @param square 
     */
    public void setEnPassant(Square square){
        enPassant = square;
    }
    
    /**
     * Palauttaa Vuoro-olion. 
     * @return turn
     */
    public Turn getTurn(){
        return turn;
    }
    
    /**
     * Estää valkoista pelaamasta lyhyttä linnaa pelin aikana. 
     */
    public void blockShortCastleWhite(){
        shortCastleWhite = false;
    }
    /**
     * Estää valkoista pelaamasta pitkää linnaa pelin aikana. 
     */
    public void blockLongCastleWhite(){
        longCastleWhite = false;
    }
    /**
     * Estää mustaa pelaamasta lyhyttä linnaa pelin aikana. 
     */
    public void blockShortCastleBlack(){
        shortCastleBlack = false;
    }
    /**
     * Estää mustaa pelaamasta lyhyttä linnaa pelin aikana. 
     */
    public void blockLongCastleBlack(){
        longCastleBlack = false;
    }
    
    /**
     * Palauttaa true, jos vuorossa oleva pelaaja on matissa. Muulloin palauttaa false.
     * @return boolean checkmate
     */
    public boolean checkmate(){
        
        if (getCheckingPieces().isEmpty()){
            return false;
        }
        
        for (Piece piece : board.getPieces()) {
            if (piece.getSide() == turn.getSide() && pieceCanMoveTo(piece).size() > 0){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Palauttaa true, jos vuorossa oleva pelaaja on patissa 
     * tai jos nappuloita on liian vähän matin tekemiseen.
     * @return stalemate
     */
    public boolean stalemate(){
        if (tooFewPieces()){
            return true;
        }
        if (getCheckingPieces().size() > 0){
            return false;
        }
        for (Piece piece : board.getPieces()) {
            if (piece.getSide() == turn.getSide() && pieceCanMoveTo(piece).size() > 0){
                return false;
            }
        }
        return true;
    } 
/**
     * Palauttaa lista ruuduista, mihin parametrina annettu nappula saa siirtyä. 
     * @param piece
     * @return squares
     */
    public ArrayList<Square> pieceCanMoveTo(Piece piece){
        ArrayList<Square> canMoveTo = new ArrayList<Square>();
        
        if (getCheckingPieces().size() == 2 && piece.getType() != Type.KING){
            return canMoveTo;
        }
        
        if (piece.getType() == Type.KING){
            canMoveTo = kingCanMoveTo(piece);
        } else if (piece.getType() == Type.PAWN){
            canMoveTo = pawnCanMoveTo(piece);
        } else {
            canMoveTo = XCanMoveTo(piece);
        }
        
        return canMoveTo;
    }

    /**
     * Palauttaa listan ruuduista, mitä parametrina annettu nappula uhkaa. 
     * @param Piece piece
     * @return ArrayList<Square> squares
     */
    private ArrayList<Square> piecesInfluence(Piece piece){
        ArrayList<Square> squares = new ArrayList<Square>();
        
        if (piece.getType() == Type.KNIGHT){
            squares = knightsInfluence(piece);
        }
        if (piece.getType() == Type.BISHOP){
            squares = bishopsInfluence(piece);
        }
        if (piece.getType() == Type.ROOK){
            squares = rooksInfluence(piece);
        }
        if (piece.getType() == Type.QUEEN){
            squares = queensInfluence(piece);
        }
        if (piece.getType() == Type.KING){
           squares = kingsInfluence(piece);
        }
        if (piece.getType() == Type.PAWN){
            squares = pawnsInfluence(piece);
        }
        
        return squares;
    }
    
    /**
     * Palauttaa Listan ruuduista, mitä ratsu uhkaa. 
     * @param Piece knight
     * @return ArrayList<Square> squares
     */
    private ArrayList<Square> knightsInfluence(Piece piece){
        ArrayList<Square> squares = new ArrayList<Square>();
        try {
            squares.add(board.getSquares()[piece.getSquare().getX() + 1][piece.getSquare().getY() + 2]);
        } catch (Exception e){
        }
        try {
            squares.add(board.getSquares()[piece.getSquare().getX() + 2][piece.getSquare().getY() + 1]);
        } catch (Exception e){
        }
        try {
            squares.add(board.getSquares()[piece.getSquare().getX() - 1][piece.getSquare().getY() - 2]);
        } catch (Exception e){
        }
        try {
            squares.add(board.getSquares()[piece.getSquare().getX() - 2][piece.getSquare().getY() - 1]);
        } catch (Exception e){
        }
        try {
            squares.add(board.getSquares()[piece.getSquare().getX() + 1][piece.getSquare().getY() - 2]);
        } catch (Exception e){
        }
        try {
            squares.add(board.getSquares()[piece.getSquare().getX() - 1][piece.getSquare().getY() + 2]);
        } catch (Exception e){
        }
        try {
            squares.add(board.getSquares()[piece.getSquare().getX() + 2][piece.getSquare().getY() - 1]);
        } catch (Exception e){
        }
        try {
            squares.add(board.getSquares()[piece.getSquare().getX() - 2][piece.getSquare().getY() + 1]);
        } catch (Exception e){
        }
        
        return squares;
    }
    
    /**
     * Palauttaa lista ruuduista, mitä lähetti uhkaa. 
     * @param Piece bishop
     * @return ArrayList<Square> squares
     */
    private ArrayList<Square> bishopsInfluence(Piece piece){
        ArrayList<Square> squares = new ArrayList<Square>();
        
        int x = piece.getSquare().getX();
        int y = piece.getSquare().getY();
        while (true){
            x++;
            y++;
            try {
                squares.add(board.getSquares()[x][y]);
                if (board.getSquares()[x][y].getSide() != Side.NEUTRAL){
                    break;
                }
            } catch (Exception e){
                break;
            }
        }
        
        x = piece.getSquare().getX();
        y = piece.getSquare().getY();
        while (true){
            x++;
            y--;
            try {
                squares.add(board.getSquares()[x][y]);
                if (board.getSquares()[x][y].getSide() != Side.NEUTRAL){
                    break;
                }
            } catch (Exception e){
                break;
            }
        }
        
        x = piece.getSquare().getX();
        y = piece.getSquare().getY();
        while (true){
            x--;
            y++;
            try {
                squares.add(board.getSquares()[x][y]);
                if (board.getSquares()[x][y].getSide() != Side.NEUTRAL){
                    break;
                }
            } catch (Exception e){
                break;
            }
        }
        
        x = piece.getSquare().getX();
        y = piece.getSquare().getY();
        while (true){
            x--;
            y--;
            try {
                squares.add(board.getSquares()[x][y]);
                if (board.getSquares()[x][y].getSide() != Side.NEUTRAL){
                    break;
                }
            } catch (Exception e){
                break;
            }
        }
        
        return squares;
    }
    
    /**
     * Palauttaa listan ruuduista, mitä torni uhkaa.
     * @param piece rook
     * @return ArrayList<Square> squares
     */
    private ArrayList<Square> rooksInfluence(Piece piece){
        ArrayList<Square> squares = new ArrayList<Square>();
        int x = piece.getSquare().getX();
        int y = piece.getSquare().getY();
        
        while (true){
            x++;
            
            try {
                squares.add(board.getSquares()[x][y]);
                if (board.getSquares()[x][y].getSide() != Side.NEUTRAL){
                    break;
                }
            } catch (Exception e){
                break;
            }
        }
        x = piece.getSquare().getX();
        while (true){
            x--;
            
            try {
                squares.add(board.getSquares()[x][y]);
                if (board.getSquares()[x][y].getSide() != Side.NEUTRAL){
                    break;
                }
            } catch (Exception e){
                break;
            }
        }
        x = piece.getSquare().getX();
        while (true){
            y++;
            
            try {
                squares.add(board.getSquares()[x][y]);
                if (board.getSquares()[x][y].getSide() != Side.NEUTRAL){
                    break;
                }
            } catch (Exception e){
                break;
            }
        }
        y = piece.getSquare().getY();
        while (true){
            y--;
            
            try {
                squares.add(board.getSquares()[x][y]);
                if (board.getSquares()[x][y].getSide() != Side.NEUTRAL){
                    break;
                }
            } catch (Exception e){
                break;
            }
        }
        
        return squares;
    }
    
    /**
     * Palauttaa lista ruuduista, mitä kuningatar uhkaa. 
     * @param Piece queen
     * @return ArrayList<Square> squares
     */
    private ArrayList<Square> queensInfluence(Piece piece){
        ArrayList<Square> squares = bishopsInfluence(piece);
        squares.addAll(rooksInfluence(piece));
        return squares;
    }
    
    /**
     * Palauttaa listan ruuduista, mitä kuningas uhkaa.
     * @param Piece King
     * @return ArrayList<Square> squares
     */
    private ArrayList<Square> kingsInfluence(Piece piece){
        ArrayList<Square> squares = new ArrayList<Square>();
        
        try{
            squares.add(board.getSquares()[piece.getSquare().getX() - 1][piece.getSquare().getY() - 1]);
        } catch (Exception e){
        }
        try{
            squares.add(board.getSquares()[piece.getSquare().getX()][piece.getSquare().getY() - 1]);
        } catch (Exception e){
        }
        try{
            squares.add(board.getSquares()[piece.getSquare().getX() + 1][piece.getSquare().getY() - 1]);
        } catch (Exception e){
        }
        try{
            squares.add(board.getSquares()[piece.getSquare().getX() - 1][piece.getSquare().getY()]);
        } catch (Exception e){
        }
        try{
            squares.add(board.getSquares()[piece.getSquare().getX() + 1][piece.getSquare().getY()]);
        } catch (Exception e){
        }
        try{
            squares.add(board.getSquares()[piece.getSquare().getX() - 1][piece.getSquare().getY() + 1]);
        } catch (Exception e){
        }
        try{
            squares.add(board.getSquares()[piece.getSquare().getX()][piece.getSquare().getY() + 1]);
        } catch (Exception e){
        }
        try{
            squares.add(board.getSquares()[piece.getSquare().getX() + 1][piece.getSquare().getY() + 1]);
        } catch (Exception e){
        }
        
        return squares;
    }
    
    /**
     * Palauttaa lista ruuduista, mitä sotilas uhkaa. 
     * @param Piece Pawn
     * @return ArrayList<Square> squares
     */
    private ArrayList<Square> pawnsInfluence(Piece piece){
        ArrayList<Square> squares = new ArrayList<Square>();
        
        if (piece.getSide() == Side.WHITE){
            try{
                squares.add(board.getSquares()[piece.getSquare().getX() - 1][piece.getSquare().getY() + 1]);
            } catch (Exception e){
            }
            try{
                squares.add(board.getSquares()[piece.getSquare().getX() + 1][piece.getSquare().getY() + 1]);
            } catch (Exception e){
            }
        } else {
            try{
                squares.add(board.getSquares()[piece.getSquare().getX() - 1][piece.getSquare().getY() - 1]);
            } catch (Exception e){
            }
            try{
                squares.add(board.getSquares()[piece.getSquare().getX() + 1][piece.getSquare().getY() - 1]);
            } catch (Exception e){
            }
        }
        return squares;
    }
    
    /**
     * Palauttaa listan shakkaavista nappuloista. 
     * @return ArrayList<Piece> pieces
     */
    private List<Piece> getCheckingPieces(){
        List<Piece> pieces = new ArrayList<Piece>();
        Piece king = null;
        
        for (Piece piece : board.getPieces()) {
            if (piece.getSide() == turn.getSide() && piece.getType() == Type.KING){
                king = piece;
            }
        }
        
        for (Piece piece : board.getPieces()) {
            if (piece.getSide() != turn.getSide()){
                List<Square> squares = piecesInfluence(piece);
                for (Square square : squares) {
                    if (square == king.getSquare()){
                        pieces.add(piece);
                    }
                }
            }
        }
        
        
        return pieces;
    }

    /**
     * Palauttaa listan ruuduista, mihin kunginas saa siirtyä. 
     * @param Piece king
     * @return ArrayList<Square> squares
     */
    private ArrayList<Square> kingCanMoveTo(Piece piece) {
        ArrayList<Square> squares = kingsInfluence(piece);
        Square start = piece.getSquare();
        
        ArrayList<Square> cantMoveTo = new ArrayList<Square>();
        
        for (Square square : squares) {
            if (square.getSide() == turn.getSide()){
                cantMoveTo.add(square);
            }
        }
        squares.removeAll(cantMoveTo);
        
        boolean occupiedByOpponent = false;
        
        for (Square square : squares) {
            if (square.getSide() == Side.NEUTRAL){
                occupiedByOpponent = false;
            } else {
                occupiedByOpponent = true;
            }
            
            piece.setSquare(square);
            
            if (getCheckingPieces().size() > 0){
                cantMoveTo.add(square);
            }
            
            piece.setSquare(start);
            if (occupiedByOpponent){
                turn.next();
                square.setSide(turn.getSide());
                turn.next();
            }
        }
        
        squares.removeAll(cantMoveTo);
        
        if (getCheckingPieces().isEmpty()){
            if (turn.getSide() == Side.WHITE){
                if (shortCastleWhite && squares.contains(board.getSquares()[5][0])){
                    if (board.getSquares()[6][0].getSide() == Side.NEUTRAL){
                        piece.setSquare(board.getSquares()[6][0]);
                        if (getCheckingPieces().isEmpty()){
                            squares.add(board.getSquares()[6][0]);
                        }
                        piece.setSquare(start);
                    }
                }
                if (longCastleWhite && squares.contains(board.getSquares()[3][0])){
                    if (board.getSquares()[2][0].getSide() == Side.NEUTRAL && board.getSquares()[1][0].getSide() == Side.NEUTRAL){
                        piece.setSquare(board.getSquares()[2][0]);
                        if (getCheckingPieces().isEmpty()){
                            squares.add(board.getSquares()[2][0]);
                        }
                        piece.setSquare(start);
                    }
                }
            }
            if (turn.getSide() == Side.BLACK){
                if (shortCastleBlack && squares.contains(board.getSquares()[5][7])){
                    if (board.getSquares()[6][7].getSide() == Side.NEUTRAL){
                        piece.setSquare(board.getSquares()[6][7]);
                        if (getCheckingPieces().isEmpty()){
                            squares.add(board.getSquares()[6][7]);
                        }
                        piece.setSquare(start);
                    }
                }
                if (longCastleBlack && squares.contains(board.getSquares()[3][7])){
                    if (board.getSquares()[2][7].getSide() == Side.NEUTRAL && board.getSquares()[1][7].getSide() == Side.NEUTRAL){
                        piece.setSquare(board.getSquares()[2][7]);
                        if (getCheckingPieces().isEmpty()){
                            squares.add(board.getSquares()[2][7]);
                        }
                        piece.setSquare(start);
                    }
                }
            }
        }
        
        return squares;
    }

    /**
     * Palauttaa listan ruuduista, mihin sotilas saa siirtyä. 
     * @param Piece pawn
     * @return ArrayList<Square> squares
     */
    private ArrayList<Square> pawnCanMoveTo(Piece piece) {
        ArrayList<Square> squares = pawnsInfluence(piece);
        ArrayList<Square> cantMoveTo = new ArrayList<Square>();
        Square start = piece.getSquare();
        
        for (Square square : squares) {
            if (square.getSide() == Side.NEUTRAL){
                if (enPassant != null){
                    if (!enPassant.equals(square)){
                        cantMoveTo.add(square);
                    }
                } else {
                    cantMoveTo.add(square);
                }
            }
            if (square.getSide() == turn.getSide()){
                cantMoveTo.add(square);
            }
        }
        squares.removeAll(cantMoveTo);
        
        if (piece.getSide() == Side.WHITE){
            if (board.getSquares()[piece.getSquare().getX()][piece.getSquare().getY() + 1].getSide() == Side.NEUTRAL){
                squares.add(board.getSquares()[piece.getSquare().getX()][piece.getSquare().getY() + 1]);
                if (piece.getSquare().getY() == 1 && board.getSquares()[piece.getSquare().getX()][piece.getSquare().getY() + 2].getSide() == Side.NEUTRAL){
                    squares.add(board.getSquares()[piece.getSquare().getX()][piece.getSquare().getY() + 2]);
                }
            }
        } else {
            if (board.getSquares()[piece.getSquare().getX()][piece.getSquare().getY() - 1].getSide() == Side.NEUTRAL){
                squares.add(board.getSquares()[piece.getSquare().getX()][piece.getSquare().getY() - 1]);
                if (piece.getSquare().getY() == 6 && board.getSquares()[piece.getSquare().getX()][piece.getSquare().getY() - 2].getSide() == Side.NEUTRAL){
                    squares.add(board.getSquares()[piece.getSquare().getX()][piece.getSquare().getY() - 2]);
                }
            }
        }
        
        boolean occupiedByOpponent = false;
        
        for (Square square : squares) {
            
            if (square.getSide() == Side.NEUTRAL){
                occupiedByOpponent = false;
            } else {
                occupiedByOpponent = true;
            }
            
            piece.setSquare(square);
            if (getCheckingPieces().size() == 1){
                if (!getCheckingPieces().get(0).getSquare().equals(square)){
                    cantMoveTo.add(square);
                }
            } else if (getCheckingPieces().size() == 2){
                cantMoveTo.add(square);
            }
            piece.setSquare(start);
            if (occupiedByOpponent){
                turn.next();
                square.setSide(turn.getSide());
                turn.next();
            }
        }
        squares.removeAll(cantMoveTo);
        
        return squares;
    }
    
    /**
     * Palauttaa listan ruuduista, mihin nappula (muu kuin kuningas tai sotilas) saa siirtyä. 
     * @param Piece piece
     * @return ArrayList<Square> squares
     */
    private ArrayList<Square> XCanMoveTo(Piece piece) {
        ArrayList<Square> squares = piecesInfluence(piece);
        ArrayList<Square> cantMoveTo = new ArrayList<Square>();
        Square start = piece.getSquare();
        
        for (Square square : squares) {
            if (square.getSide() == turn.getSide()){
                cantMoveTo.add(square);
            }
        }
        squares.removeAll(cantMoveTo);
        
        boolean occupiedByOpponent = false;
        
        for (Square square : squares) {
            if (square.getSide() == Side.NEUTRAL){
                occupiedByOpponent = false;
            } else {
                occupiedByOpponent = true;
            }
            piece.setSquare(square);
            
            if (getCheckingPieces().size() == 2){
                cantMoveTo.add(square);
            } else if (getCheckingPieces().size() == 1){
                if (!getCheckingPieces().get(0).getSquare().equals(square)){
                    cantMoveTo.add(square);
                }
            }
            piece.setSquare(start);
            if (occupiedByOpponent){
                turn.next();
                square.setSide(turn.getSide());
                turn.next();
            }
        }
        squares.removeAll(cantMoveTo);
        
        return squares;
    }

    /**
     * Tarkistaa onko laudalla riittävästi nappuloita matin tekemiseen. Palauttaa true, jos on. 
     * @return boolean tooFewPieces 
     */
    private boolean tooFewPieces() {
        if (board.getPieces().size() == 2){
            return true;
        }
        
        if (board.getPieces().size() == 3){
            for (Piece piece : board.getPieces()) {
                if (piece.getType() == Type.KNIGHT || piece.getType() == Type.BISHOP){
                    return true;
                }
            }
        }
        return false;
    }
}