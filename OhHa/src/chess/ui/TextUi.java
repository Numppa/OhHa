package chess.ui;

import chess.board.Board;
import chess.board.Locator;
import chess.board.Square;
import chess.logic.Controls;
import chess.logic.Logic;
import chess.logic.Turn;
import chess.pieces.Side;
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
        
        this.logic = new Logic(board , turn);
        this.controls = new Controls(board);
    }
    
    
    
    public void run(){
        System.out.println("komennot: (lataa) , (tallenna) , (uusi) , (undo) , lopeta tai (tee siirto)");
        while (true){
            if (turn.getSide() == Side.WHITE){
                System.out.println("Valkoinen siirtää");
            } else {
                System.out.println("Musta siirtää");
            }
            
            System.out.println("Kirjoita ruutu, josta siirretään");
            
            String command = scanner.nextLine();
            
            try{
                
            
            
                if (Integer.parseInt(command.substring(1)) > 0 && Integer.parseInt(command.substring(1)) < 9){
                    Locator locator = new Locator(board);
                    if (locator.getSquare(command) != null){
                        Square s = locator.getSquare(command);
                        if (s.getSide() != turn.getSide()){
                            System.out.println("Ruudussa ei omaa nappulaa!");
                        } else {
                            System.out.println("Ruutu " + command + " valittu");
                        }
                    }
                }
            } catch (Exception e){
            }
            
            
            
            
            if (command.equals("lopeta")){
                break;
            }
                

        }
    }
    
    
    
}
