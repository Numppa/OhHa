
package chess.ui;

import chess.board.Board;
import chess.logic.Controls;
import chess.logic.Logic;
import java.awt.Container;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Pelin käyttöliittymä
 * 
 * @author joel
 */

public class Ui implements Runnable{
    /**
     * Ikkuna-olio. 
     */
    private JFrame frame;
    
    /**
     * Tyhjä kostruktori. 
     */
    public Ui() {
    }
    
    
    /**
     * Luo ikkunan, sen sisällön ja asettaa sen näkyviin. 
     */
    @Override
    public void run() {
        frame = new JFrame("Joel's Chess");
        frame.setPreferredSize(new Dimension(800 , 600));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
                createComponents(frame.getContentPane());
        } catch (IOException ex) {
        }
        
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Luo käyttöliittymäcomponentit. 
     * @param container
     * @throws IOException 
     */
    public void createComponents(Container container) throws IOException {
        
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        
        JPanel panel = new JPanel();
        
        BoxLayout box = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setMaximumSize(new Dimension(200, 200));
        
        JButton newButton = new JButton("New game");
        JButton saveButton = new JButton("Save game");
        JButton loadButton = new JButton("Load saved game");
        JButton undoButton = new JButton("Undo");
        
        panel.add(newButton);
        panel.add(saveButton);
        panel.add(loadButton);
        panel.add(undoButton);
        
        
        Board board = new Board();
        Drawer drawer = new Drawer(board);
        drawer.setMaximumSize(new Dimension(600, 600));
        
        container.add(drawer);
        container.add(panel);
        
        Logic logic = new Logic(board);
        Controls controls = new Controls(board, logic);
        Selections selections = new Selections(logic, controls, board , drawer);
        MouseListener mouseListener = new MouseListener(selections, board);
        
        ButtonListener buttonListener = new ButtonListener(selections , controls , drawer , newButton , saveButton , loadButton , undoButton);
        
        newButton.addActionListener(buttonListener);
        saveButton.addActionListener(buttonListener);
        loadButton.addActionListener(buttonListener);
        undoButton.addActionListener(buttonListener);
        
        
        container.addMouseListener(mouseListener);
    }
    
    /**
     * Palauttaa ikkunan. 
     * @return frame
     */
    public JFrame getFrame(){
        return frame;
    }
    
}
