
package chess.ui;

import java.awt.BorderLayout;
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
        
        
        
    }
    
    public JFrame getFrame(){
        return frame;
    }
    
}
