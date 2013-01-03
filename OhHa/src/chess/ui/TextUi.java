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
    
    public TextUi(){
        this.turn = new Turn();
        this.scanner = new Scanner(System.in);
        this.board = new Board();
        
        this.logic = new Logic(board);
        this.controls = new Controls(board , logic);
    }
    
    
    
    public void run(){
        
        
        System.out.println("options: (load) , (save) , (new) , (undo) , quit or (make your move)");
        while (true){
            
        }
    }
}
