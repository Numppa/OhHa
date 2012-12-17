
package chess.ui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Ui implements Runnable{
    private JFrame frame;
    
    public Ui(){
        
    }
    
    @Override
    public void run() {
        frame = new JFrame("Joel's Chess");
        frame.setPreferredSize(new Dimension(800 , 500));
        
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
