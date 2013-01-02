package chess.ui;

import chess.board.Board;
import chess.board.Locator;
import chess.board.Square;
import chess.logic.Controls;
import chess.logic.Logic;
import chess.logic.Turn;
import chess.pieces.Side;
import java.util.ArrayList;
import java.util.Scanner;


public class TextUi {
    private Turn turn;
    private Scanner scanner;
    private Board board;
    private Logic logic;
    private Controls controls;
    
    public TextUi(Board board){
        this.turn = new Turn();
        this.scanner = new Scanner(System.in);
        this.board = board;
        
        this.logic = new Logic(board);
        this.controls = new Controls(board , logic);
    }
    
    
    
    public void run(){
        
        
        System.out.println("options: (load) , (save) , (new) , (undo) , quit or (make your move)");
        while (true){
            if (turn.getSide() == Side.WHITE){
                System.out.println("White moves\n");
            } else {
                System.out.println("Black moves\n");
            }
            
            System.out.println("Write that square's name where the piece you want to move is standing");
            
            String command = scanner.nextLine();
            
            try{
                if (Integer.parseInt(command.substring(1)) > 0 && Integer.parseInt(command.substring(1)) < 9){
                    Locator locator = new Locator(board);
                    if (locator.getSquare(command) != null){
                        Square s = locator.getSquare(command);
                        if (s.getSide() != turn.getSide()){
                            System.out.println("You have no piece in that square!");
                        } else {
                            System.out.println("Square " + command + " selected");
                        }
                    }
                }
            } catch (Exception e){
            }
            
            logic.setEnPassant(board.getSquares()[1][2]);
            ArrayList<Square> s = logic.pieceCanMoveTo(board.getPiece(board.getSquares()[0][1]));
            for (Square square : s) {
                System.out.println(square.getX() + " " + square.getY());
            }
            
            if (command.equals("quit")){
                break;
            }
                

        }
    }
    
    
    
}
