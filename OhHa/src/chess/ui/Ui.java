
package chess.ui;

import chess.board.Board;
import chess.logic.Controls;
import chess.logic.Logic;
import chess.pieces.Side;
import chess.ui.graphics.BoardGraphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import sun.awt.HorizBagLayout;

public class Ui implements Runnable{
    private JFrame frame;
    private Board board;
    
    public Ui() throws IOException{
        this.board = new Board();
    }
    
    @Override
    public void run() {
        frame = new JFrame("Joel's Chess");
        frame.setPreferredSize(new Dimension(800 , 625));
        frame.setMinimumSize(new Dimension(800, 625));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        createComponents(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
        
    }

    public void createComponents(Container container) {
        
        container.setLayout(new BorderLayout());
        
        
        JPanel panel = new JPanel();
        
        BorderLayout border = new BorderLayout();
        
        panel.setLayout(border);
        panel.setMinimumSize(new Dimension(200, 600));
        panel.setMaximumSize(new Dimension(200, 600));
        
        
        
        panel.add(new JButton("jee") , BorderLayout.SOUTH);
        panel.add(new JButton("jee") , BorderLayout.WEST);
        panel.add(new JButton("jee") , BorderLayout.EAST);
        panel.add(new JButton("jee") , BorderLayout.NORTH);
        panel.add(new JButton("jee"));
        panel.add(new JButton("jee"));
        
        
        Drawer drawer = new Drawer(board);
        container.add(drawer);
        container.add(panel , BorderLayout.EAST);
        
        
        
        
        Logic logic = new Logic(board);
        Controls controls = new Controls(board, logic);
        Selections selections = new Selections(logic, controls, board , drawer);
        MouseListener mouseListener = new MouseListener(selections, board);
        
        container.addMouseListener(mouseListener);
    }
    
    public JFrame getFrame(){
        return frame;
    }
    
}
