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
    /**
     * Lista jossa on pelatut siirrot. 
     * 
     */
    ArrayList<String> log;
    /**
     * Tiedosto, johon voidaan tallentaa peli. 
     */
    File file;
    
    /**
     * Luo uuden listan sekä asettaa tiedostolle polun. 
     */
    public Moves() {
        this.log = new ArrayList<String>();
        this.file = new File("save");
    }
    
    /**
     * Lisää siirron listaan.
     * Siirto on muotoa xyxyt eli lähtöruudun koordinaatit, maaliruudunkoordinaatit 
     * ja mahdollinen nappulan tyyppi, joksi sotilas korottuu.
     * @param piece
     * @param square 
     */
    public void addMove(Piece piece , Square square){
        log.add(piece.getSquare().toString() + "" + square.toString());
    }
    
    /**
     * Lisää suoraan merkkijonon listaan. Käytetään ainoastaan tilanteissa, kun sotilas korottuu. 
     * @param string 
     */
    public void addString(String string){
        log.add(string);
    }
    
    /**
     * Poistaa viimeisen siirron listasta. 
     */
    public void removeLast(){
        if (log.size() > 0){
            log.remove(log.size() - 1);
        }
    }
    
    /**
     * palauttaa listan. 
     * @return log
     */
    public List<String> getLog(){
        return log;
    }
    
    /**
     * Tallentaa listan tiedostoon. 
     * @throws IOException 
     */
    public void save() throws IOException{
        FileWriter filewriter = new FileWriter(file);
        for (String string : log) {
            filewriter.write(string + "\n");
        }
        filewriter.close();
    }
    
    /**
     * Kopioi listan tilalle tiedoston sisällön. 
     * @throws FileNotFoundException 
     */
    public void load() throws FileNotFoundException{
        Scanner scanner = new Scanner(file);
        log = new ArrayList<String>();
        while (scanner.hasNextLine()){
            log.add(scanner.nextLine());
        }
    }
}
