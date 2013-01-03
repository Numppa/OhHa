package chess.ui;

import chess.board.Board;
import chess.board.Locator;
import chess.board.Square;
import chess.logic.Controls;
import chess.logic.Logic;
import chess.pieces.Piece;
import chess.pieces.Type;
import java.io.IOException;
import java.util.Scanner;


public class TextUi {
    private Scanner scanner;
    private Board board;
    private Logic logic;
    private Controls controls;
    
    public TextUi(){
        this.scanner = new Scanner(System.in);
        this.board = new Board();
        
        this.logic = new Logic(board);
        this.controls = new Controls(board , logic);
    }
    
    
    
    public void run() throws IOException{
        
        
        System.out.println("options: quit , new , save , load , undo , or make your move");
        while (true){
            isItOver();            
            System.out.println(logic.getTurn().getSide() + " moves");
            String command = scanner.nextLine();
            
            if (command.length() == 2){
                startMoving(command);
            }
            
            if (command.equals("quit")){
                System.out.println("bye");
                break;
            }
            
            if (command.equals("new")){
                controls.newGame();
                System.out.println("a new game starts");
            }
            
            if (command.equals("undo")){
                controls.undo();
                System.out.println("one move taken back");
            }
            
            if (command.equals("save")){
                controls.save();
                System.out.println("game saved");
            }
            if (command.equals("load")){
                controls.loadGame();
                System.out.println("game loaded");
            }
    }
}

    private void startMoving(String command) {
        Locator locator = new Locator(board);
        Square square = locator.getSquare(command);
        if (square == null){
            System.out.println("invalid command");
            return;
        }
        if (cantMove(square)){
            return;
        }
        moveTo(square);
    }

    private boolean cantMove(Square square) {
        if (square.getSide() != logic.getTurn().getSide()){
            System.out.println("you have no piece in that square");
            return true;
        }
        if (logic.pieceCanMoveTo(board.getPiece(square)).isEmpty()){
            System.out.println("you can't move that piece");
            return true;
        }
        return false;
    }

    private void isItOver() {
        if (logic.checkmate()){
            logic.nextTurn();
            System.out.println("game over, " + logic.getTurn().getSide() + " wins");
            logic.nextTurn();
        }
        if (logic.stalemate()){
            System.out.println("stalemate");
        }
    }

    private void moveTo(Square square) {
        System.out.println("square selected");
        while (true){
            String destination = scanner.nextLine();
            Locator l = new Locator(board);
            
            Square square2 = l.getSquare(destination);
            
            if (square2 ==null){
                System.out.println("invalid command");
                continue;
            }
            if (!logic.pieceCanMoveTo(board.getPiece(square)).contains(square2)){
                System.out.println("you can't move it to that square");
                continue;
            }
            System.out.println("piece moved");
            boolean promotion = controls.makeAMove(board.getPiece(square), square2, false);
            if (promotion){
                promote(board.getPiece(square2));
            }
            break;
        }
    }

    private void promote(Piece piece) {
        while (true){
            System.out.println("promotion: queen , rook , knight or bishop");
            String command = scanner.nextLine();
            if (command.equals("queen")){
                controls.promote(piece, Type.QUEEN);
                break;
            } else if (command.equals("rook")){
                controls.promote(piece, Type.ROOK);
                break;
            } else if (command.equals("knight")){
                controls.promote(piece, Type.KNIGHT);
                break;
            } else if (command.equals("bishop")){
                controls.promote(piece, Type.BISHOP);
                break;
            } else {
                System.out.println("invalid command");
            }
        }
        System.out.println("pawn promoted to " + piece.getType());
    }
}