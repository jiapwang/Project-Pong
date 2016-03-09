/**
 * Contains all the methods that are characteristic of a Ball
 *
 * @author Jia
 */
public class Ball extends GameObject {
	public Ball(int initX, int initY, int height, int width, int xSpeed, int ySpeed) {
		super(initX, initY, height, width, xSpeed, ySpeed);
	}

	/**
	 * When the ball reaches any part of the frame's top or bottom edges it will
	 * bounce back.
	 */

	public void bounce() {

		if (topLeft.y <= 0) {
			this.setYSpeed(-ySpeed);
		}

		else if (bottomRight.y >= 475) { // !! watch out for magic numbers--if
											// you decide to change the size of
											// the game's frame, you'll have to
											// modify Ball code, which seems odd
			this.setYSpeed(-ySpeed);
		}
	}

	/**
	 * When the ball collides with the left paddle the ball is deflected and its
	 * xSpeed is increased by one.
	 */
	public void leftPaddleBounce() {
		this.setXSpeed(-xSpeed);
		this.accelX(1);
	}

	/**
	 * When the ball collides with the right paddle the ball is deflected and
	 * its xSpeed is increased by one.
	 */

	public void rightPaddleBounce() {
		this.setXSpeed(-xSpeed);
		this.accelX(-1);
	}

	/**
	 * Check to see if the ball goes out of bound in the x direction.
	 *
	 * @return 1 if the ball goes out of bound on the left side of the frame.
	 * @return 2 if the ball goes out of bound on the right side of the frame.
	 * @return 0 otherwise
	 */

	// !! these return values should be declared as static final int "constants
	// in Ball; this will make your code, both here and in GamePanel, much more
	// readable

	public int checkoutofbound() {
		if (this.getTopLeft().x < -20) // !! more magic numbers
			return 1;
		if (this.getTopLeft().x > 505)
			return 2;
		else
			return 0;
	}

}