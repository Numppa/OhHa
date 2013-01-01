package chess.logic;

import chess.board.Board;
import chess.board.Square;
import chess.pieces.Piece;
import chess.pieces.Side;
import chess.pieces.Type;
import java.util.ArrayList;
import java.util.List;

public class Logic {
    private Board board;
    private Turn turn;
    private boolean shortCastle;
    private boolean longCastle;
    private Square enPassant;
    
    public Logic(Board board){
        this.board = board;
        this.turn = new Turn();
        this.longCastle = true;
        this.shortCastle = true;
        this.enPassant = null;
    }
    
    public void startOfTurn(){
        turn.next();
        
        
    }
    
    public boolean checkmate(){
        Piece king = null;
        for (Piece piece : board.getPieces()) {
            if (piece.getSide() == turn.getSide() && piece.getType() == Type.KING){
                king = piece;
            }
        }
        if (pieceCanMoveTo(king).size() > 0 || getCheckingPieces().isEmpty()){
            return false;
        }
        
        if (getCheckingPieces().size() == 2){
            return true;
        }
        if (getCheckingPieces().size() == 1){
            
        }
        return false;
    }
    
    
    

    public ArrayList<Square> pieceCanMoveTo(Piece piece){
        ArrayList<Square> canMoveTo = new ArrayList<Square>();
        
        if (getCheckingPieces().size() == 2 && piece.getType() != Type.KING){
            return canMoveTo;
        }
        
        if (piece.getType() == Type.KING){
            canMoveTo = kingCanMoveTo(piece);
        }
        
        if (piece.getType() == Type.PAWN){
            canMoveTo = pawnCanMoveTo(piece);
        }
        
        if (piece.getType() == Type.KNIGHT){
            canMoveTo = knightCanMoveTo(piece);
        }
        
        
        return canMoveTo;
    }

    

    
    
    private List<Square> PiecesInfluence(Piece piece){
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
    private ArrayList<Square> queensInfluence(Piece piece){
        ArrayList<Square> squares = bishopsInfluence(piece);
        squares.addAll(rooksInfluence(piece));
        return squares;
    }
    
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
                List<Square> squares = PiecesInfluence(piece);
                for (Square square : squares) {
                    if (square == king.getSquare()){
                        pieces.add(piece);
                    }
                }
            }
        }
        
        
        return pieces;
    }

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
        
        boolean ownedByOpponent = false;
        
        for (Square square : squares) {
            if (square.getSide() == Side.NEUTRAL){
                ownedByOpponent = false;
            } else {
                ownedByOpponent = true;
            }
            
            piece.setSquare(square);
            
            if (getCheckingPieces().size() > 0){
                cantMoveTo.add(square);
            }
            
            piece.setSquare(start);
            if (ownedByOpponent){
                turn.next();
                square.setSide(turn.getSide());
                turn.next();
            }
        }
        
        squares.removeAll(cantMoveTo);
        
        if (getCheckingPieces().isEmpty()){
            if (shortCastle && squares.contains(board.getSquares()[5][0])){
                if (board.getSquares()[6][0].getSide() == Side.NEUTRAL){
                    piece.setSquare(board.getSquares()[6][0]);
                    if (getCheckingPieces().isEmpty()){
                        squares.add(board.getSquares()[6][0]);
                    }
                    piece.setSquare(start);
                }
            }
            if (longCastle && squares.contains(board.getSquares()[3][0])){
                if (board.getSquares()[2][0].getSide() == Side.NEUTRAL && board.getSquares()[1][0].getSide() == Side.NEUTRAL){
                    piece.setSquare(board.getSquares()[2][0]);
                    if (getCheckingPieces().isEmpty()){
                        squares.add(board.getSquares()[2][0]);
                    }
                    piece.setSquare(start);
                }
            }
        }
        
        return squares;
    }

    private ArrayList<Square> pawnCanMoveTo(Piece piece) {
        ArrayList<Square> squares = pawnsInfluence(piece);
        ArrayList<Square> cantMoveTo = new ArrayList<Square>();
        Square start = piece.getSquare();
        
        for (Square square : squares) {
            if (square.getSide() == Side.NEUTRAL){
                if (enPassant != null){
                    if (!enPassant.equals(square));
                        cantMoveTo.add(square);
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
        
        boolean ownedByOpponent = false;
        
        for (Square square : squares) {
            
            if (square.getSide() == Side.NEUTRAL){
                ownedByOpponent = false;
            } else {
                ownedByOpponent = true;
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
            if (ownedByOpponent){
                turn.next();
                square.setSide(turn.getSide());
                turn.next();
            }
        }
        squares.removeAll(cantMoveTo);
        
        return squares;
    }

    private ArrayList<Square> knightCanMoveTo(Piece piece) {
        ArrayList<Square> squares = knightsInfluence(piece);
        ArrayList<Square> cantMoveTo = new ArrayList<Square>();
        Square start = piece.getSquare();
        
        for (Square square : squares) {
            if (square.getSide() == turn.getSide()){
                cantMoveTo.add(square);
            }
        }
        squares.removeAll(cantMoveTo);
        
        boolean ownedByOpponent = false;
        
        for (Square square : squares) {
            if (square.getSide() == Side.NEUTRAL){
                ownedByOpponent = false;
            } else {
                ownedByOpponent = true;
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
            if (ownedByOpponent){
                turn.next();
                square.setSide(turn.getSide());
                turn.next();
            }
        }
        squares.removeAll(cantMoveTo);
        
        return squares;
    }


    
}
