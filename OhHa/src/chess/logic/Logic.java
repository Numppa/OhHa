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
        return new ArrayList<Piece>();
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
        squares.addAll(rookCanMove(piece));
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
    
}
