
package chess.ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Ui implements Runnable{
    private JFrame frame;
    
    public Ui(){
        
    }
    
    @Override
    public void run() {
        frame = new JFrame("Joel's Chess");
        frame.setPreferredSize(new Dimension(1200 , 600));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        createComponents(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }

    public void createComponents(Container container) {
        GridLayout layout = new GridLayout(1 , 2);
        container.setLayout(layout);
        
        GridLayout grids = new GridLayout(8, 8);
        JPanel panel = new JPanel(grids);
        
        for (int i = 0; i < 64; i++) {
            panel.add(new JButton());
        }
        container.add(panel);
        container.add(new JButton());
        
    }
    
    public JFrame getFrame(){
        return frame;
    }
    
}
