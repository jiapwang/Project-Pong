import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * Sets up the GUI and starts the game.
 * 
 * @author sdexter72
 *
 */
public class Main{

    public static void main(String[] args) {
    	
        JFrame frame = new JFrame("Let's Play Pong!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        // bare bones: just add a panel where the game objects are drawn
        
        GamePanel gamePanel = new GamePanel();
        
        frame.add(gamePanel, BorderLayout.CENTER);

        frame.setSize(500, 500);
        
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);	//place the frame at the center of the screen
        frame.setVisible(true);
        frame.setResizable(false);	//prevents user from changing the window size
        
        // the game starts when the gamepanel animation begins
        
        gamePanel.go();
    }
}