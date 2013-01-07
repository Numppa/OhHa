package chess.saving;

import chess.board.Square;
import chess.pieces.Piece;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Mahdollistaa pelin tallentamisen ja lataamisen.
 * 
 * @author joel
 */

public class Moves {
    ArrayList<String> log;
    File file;
    
    public Moves() {
        this.log = new ArrayList<String>();
        this.file = new File("save");
    }
    
    public void addMove(Piece piece , Square square){
        log.add(piece.getSquare().toString() + "" + square.toString());
    }
    
    public void removeLast(){
        if (log.size() > 0){
            log.remove(log.size() - 1);
        }
    }
    
    public List<String> getLog(){
        return log;
    }
    
    public void save() throws IOException{
        FileWriter filewriter = new FileWriter(file);
        for (String string : log) {
            filewriter.write(string + "\n");
        }
        filewriter.close();
    }
    
    public void load() throws FileNotFoundException{
        Scanner scanner = new Scanner(file);
        log = new ArrayList<String>();
        while (scanner.hasNextLine()){
            log.add(scanner.nextLine());
        }
    }
    
}
