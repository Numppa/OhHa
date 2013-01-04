
package chess.ui;

import chess.board.Board;
import chess.pieces.Side;
import chess.ui.graphics.BoardGraphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Ui implements Runnable{
    private JFrame frame;
    private Board board;
    
    public Ui(){
        this.board = new Board();
    }
    
    @Override
    public void run() {
        frame = new JFrame("Joel's Chess");
        frame.setPreferredSize(new Dimension(800 , 625));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        createComponents(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }

    public void createComponents(Container container) {
        Drawer drawer = new Drawer(board);
        container.add(drawer);
        
        
    }
    
    public JFrame getFrame(){
        return frame;
    }
    
}
