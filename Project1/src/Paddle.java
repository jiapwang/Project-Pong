
/** 
 * Contains methods for changing the ySpeed of the paddles
 * 
 * @author Jia
 */

public class Paddle extends GameObject {
	
	public Paddle (int initX, int initY, int height, int width, int xSpeed, int ySpeed) {
					super(initX, initY, height, width, xSpeed, ySpeed);
	}
	
	/**
	 * Call the setYSpeed method and pass in the value -10
	 */
	
	public void setUpSpeed () {
		this.setYSpeed(-10);
	}
	
	/**
	 * call the setYSpeed method and pass in the value 10
	 */
	
	public void setDownSpeed () {
		this.setYSpeed(10);
	}
	
	/**
	 * call the setYSpeed method and pass in the value 0
	 */
	
	public void setStop () {
		this.setYSpeed(0);
	}
	
}
