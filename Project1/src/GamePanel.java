import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 * Animates a simple graphical game.
 * 
 * Uses a Swing Timer to advance the animation; keeps track of, and renders, all GameObjects. Handles all relevant game events.
 * 
 * @author sdexter72
 *
 */

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	
   	static final int FRAME_RATE = 30; // animation proceeds at 30 frames per second
	Timer t;	// animation timer
	
	JFrame frame;	//frame for announcing the winner at the end of the game
	
	Ball ball; 	//ball reference variable
	Paddle paddleLeft;	//left paddle reference variable
	Paddle paddleRight;	//right paddle reference variable
	
	int LeftLives = 5;	//starting lives for player on the left
	int RightLives = 5;	//starting lives for player on the right
	
	/**
	 * Sets up gray panel background. 
	 * Creates game Timer.
	 * Creates the ball object and pass in the indicated values to the constructor. 
	 * Creates the paddleLeft object and pass in the indicated values into the constructor. 
	 * Creates the paddleRight object and pass in the indicated values into the constructor. 
	 */
	
	GamePanel () {
		setBackground(Color.gray);
        t = new Timer(1000/FRAME_RATE, this);	
		ball = new Ball(0,0,15,15,5,5);			
		paddleLeft = new Paddle(0,200,70,10,0,0);		
		paddleRight = new Paddle(490,200,70,10,0,0);	
	}
	
	/**
	 * Draws a line down the middle of the window and a circle at the middle of the window. 
	 * Draws the score for the left and right player on the top portion of the window.
	 * Creates a blue ball at the upper left hand corner of the window.
	 * Creates an orange paddle at half way down the frame for both sides. 
	 */
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.WHITE);
		g.drawLine(250, 0, 250, 500);
		g.drawOval(125, 115, 250, 250);
		g.setFont(new Font("Arial", 1, 25));
		g.drawString("Lives: ", 65, 25);
		g.drawString(String.valueOf(this.LeftLives),145,25);
		g.drawString("Lives: ", 320, 25);
		g.drawString(String.valueOf(this.RightLives),250+155,25);
		
		g.setColor(Color.blue);
		g.fillOval(ball.getTopLeft().x, ball.getTopLeft().y, ball.getWidth(), ball.getHeight());
		g.setColor(Color.orange);
		g.fillRect(paddleLeft.topLeft.x, paddleLeft.topLeft.y, paddleLeft.getWidth(), paddleLeft.getHeight());
		g.fillRect(paddleRight.topLeft.x, paddleRight.topLeft.y, paddleRight.getWidth(), paddleRight.getHeight());
		
	}

	/**
	 * Responds to all actionPerformed events. In bare-bones implementation, these are just 'ticks' from the timer.
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// if this is an event from the Timer, call the method that advances the animation
		if (e.getSource() == t) {
			tick();
		}
	}

	/**
	 * Make sure all GameObjects are in the right place, 
	 * checks for collision and out of bound, 
	 * then redraw game
	 */
	
	private void tick() {
		ball.step();	// move ball
		ball.bounce();	// checks to see if ball needs to bounce off surfaces
		
		paddleRight.step();
		paddleLeft.step();
		
		if (GameObject.collide(paddleLeft, ball) == true)
			ball.leftPaddleBounce();

		if(GameObject.collide(ball, paddleRight) == true)
			ball.rightPaddleBounce();
		
		if (ball.checkoutofbound() == 1) {
			LeftLives--;
			ball=null;
			ball = new Ball(0,0,15,15,6,5);
			
			if (LeftLives == 0) {
				repaint();
				t.stop();
				JOptionPane.showMessageDialog(frame, "Right Player is the Winner! \n\nThank you for playing!", "Congratulations!", JOptionPane.PLAIN_MESSAGE);
			}
		}
		
		if (ball.checkoutofbound() == 2) {
			RightLives--;
			ball=null;
			ball = new Ball(485,0,15,15,-6,5);	
			
			if (RightLives == 0) {		
				repaint();
				t.stop();
				JOptionPane.showMessageDialog(frame, "Left Player is the Winner! \n\nThank you for playing!", "Congratulations!", JOptionPane.PLAIN_MESSAGE);
			}
			
		}
		
		addKeyListener(this);
        requestFocusInWindow();
		
		repaint();		// ask to have the game redrawn (this will invoke paintComponent() when the system says the time is right)
	}
	
	/**
	 * Start the Timer: this will cause events to be fired, and thus the animation to begin
	 */
	
	void go() {
		t.start();
	}
	
	/**
	 * When UP key is pressed, setUpSpeed method is called for paddleRight
	 * When DOWN key is pressed, setDownSpeed method is called for paddleRight
	 * When W key is pressed, setUpSpeed method is called for paddleLeft
	 * When S key is pressed setDownSpeed method is called for paddleLeft
	 * 
	 */
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyPressed = e.getKeyCode();
		
		//Press Esc to close the program
		if (keyPressed == KeyEvent.VK_ESCAPE) {
			System.out.println("Sad to see you go, BYE! :(");
			System.exit(0);
		}
		
		//Press space to pause
		if (keyPressed == KeyEvent.VK_SPACE) {
			if (t.isRunning())
				t.stop();
			else
				t.start();
		}
		
		if (keyPressed == KeyEvent.VK_UP) {
			paddleRight.setUpSpeed();
		}	
			else if (e.getKeyCode() ==KeyEvent.VK_DOWN) {
				paddleRight.setDownSpeed();
			}
		
		if (keyPressed == KeyEvent.VK_W) {
			paddleLeft.setUpSpeed();
		}
			else if (e.getKeyCode() == KeyEvent.VK_S) {
				paddleLeft.setDownSpeed();
			}
	}
	
	/**
	 * call the paddleRight's setStop() method when the UP or DOWN key is released
	 * call the paddleLeft's setStop() method when the W or S key is released. 
	 */
	
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN)
		paddleRight.setStop();
		
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S)
		paddleLeft.setStop();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// No implementation code for this method. 
		
	}
	
}
