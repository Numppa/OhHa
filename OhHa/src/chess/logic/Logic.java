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
    
    public Logic(Board board){
        this.board = board;
        this.turn = new Turn();
        this.longCastle = true;
        this.shortCastle = true;
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
    
    
    

    public List<Square> pieceCanMoveTo(Piece piece){
        ArrayList<Square> canMoveTo = new ArrayList<Square>();
        
        if (getCheckingPieces().size() == 2 && piece.getType() != Type.KING){
            return canMoveTo;
        }
        
        if (getCheckingPieces().size() == 1){
            
        }
        
        if (getCheckingPieces().isEmpty()){
            if (pieceCanBeRemoved(piece)){
                
            }
        }
        
        return canMoveTo;
    }

    

    
    
    private List<Square> PiecesInfluence(Piece piece){
        ArrayList<Square> squares = new ArrayList<Square>();
        
        if (piece.getType() == Type.KNIGHT){
            squares = (ArrayList<Square>) knightsInfluence(piece);
        }
        if (piece.getType() == Type.BISHOP){
            squares = (ArrayList<Square>) bishopsInfluence(piece);
        }
        if (piece.getType() == Type.ROOK){
            squares = (ArrayList<Square>) rooksInfluence(piece);
        }
        if (piece.getType() == Type.QUEEN){
            squares = (ArrayList<Square>) queensInfluence(piece);
        }
        if (piece.getType() == Type.KING){
           squares = (ArrayList<Square>) kingsInfluence(piece);
        }
        if (piece.getType() == Type.PAWN){
            squares = (ArrayList<Square>) pawnsInfluence(piece);
        }
        
        return squares;
    }
    
    private List<Square> knightsInfluence(Piece piece){
        List<Square> squares = new ArrayList<Square>();
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
    private List<Square> bishopsInfluence(Piece piece){
        List<Square> squares = new ArrayList<Square>();
        
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
    
    private List<Square> rooksInfluence(Piece piece){
        List<Square> squares = new ArrayList<Square>();
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
    private List<Square> queensInfluence(Piece piece){
        List<Square> squares = bishopsInfluence(piece);
        squares.addAll(rooksInfluence(piece));
        return squares;
    }
    
    private List<Square> kingsInfluence(Piece piece){
        List<Square> squares = new ArrayList<Square>();
        
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
    private List<Square> pawnsInfluence(Piece piece){
        List<Square> squares = new ArrayList<Square>();
        
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
        
        for (Piece piece : pieces) {
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

    private boolean pieceCanBeRemoved(Piece piece) {
        
        
        
        
        
        return false;
    }
    
}
