import java.awt.Point;
import java.awt.Rectangle;

/**
 * Models an 'object' that is rendered in the game display.
 * 
 * Each object has a "bounding box" defined by its top left and bottom right
 * corners. Each object has a speed in the x and y direction, which can be
 * changed by the set and accel methods.
 * 
 * @author sdexter72
 *
 */

public class GameObject {
	protected Point topLeft; // initial coordinates of top left corner of object
	protected Point bottomRight; // initial coordinates of bottom right corner of object

	protected int xSpeed;
	protected int ySpeed;

	/**
	 * @return Rectangle object with the same bounding box as the GameObject.
	 */
	
	public Rectangle getBounds() { //return a Rectangle object given the coordinates of the GameObject
		return new Rectangle (this.getTopLeft().x, this.getTopLeft().y, this.getWidth(), this.getHeight());
	}
	
	/**
	 * @return true if the two objects have collided, false otherwise.
	 */

	public static boolean collide(GameObject object1, GameObject object2) {
		
		if(object1.getBounds().intersects(object2.getBounds()))
			return true;
		else 
			return false;
	}
	
	/**
	 * Initialize object with top and bottom corners and initial x- and y-speed
	 */

	public GameObject(Point topLeft, Point bottomRight, int xSpeed, int ySpeed) {
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;

		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	/**
	 * Initialize object with top corner, height, width, and initial x- and
	 * y-speed
	 */

	public GameObject(int initX, int initY, int height, int width, int xSpeed, int ySpeed) {
		this(new Point(initX, initY), new Point(initX + width, initY + height), xSpeed, ySpeed);
	}

	/**
	 * Set the GameObject's speed in the x dimension
	 * 
	 * @param xSpeed
	 *            The desired x-speed.
	 */

	public void setXSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	/**
	 * Set the GameObject's speed in the y dimension
	 * 
	 * @param ySpeed
	 *            The desired y-speed.
	 */
	public void setYSpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}

	/**
	 * Increase the GameObject's speed in the x dimension
	 * 
	 * @param x
	 *            The amount of increase.
	 */

	public void accelX(int x) {
		xSpeed += x;
	}

	/**
	 * Increase the GameObject's speed in the y dimension
	 * 
	 * @param y
	 *            The amount of increase.
	 */

	public void accelY(int y) {
		ySpeed += y;
	}

	/**
	 * @return A Point representing the top-left corner of the GameObject's
	 *         bounding box
	 */
	
	public Point getTopLeft() {
		return topLeft;
	}

	/**
	 * @return A Point representing the bottom-right corner of the GameObject's
	 *         bounding box
	 */

	public Point getBottomRight() {
		return bottomRight;
	}

	/**
	 * @return The height (in pixels) of the GameObject
	 */
	
	public int getHeight() {
		return bottomRight.y - topLeft.y;
	}

	/**
	 * @return The width (in pixels) of the GameObject
	 */

	public int getWidth() {
		return bottomRight.x - topLeft.x;
	}

	/**
	 * Changes the location of the object for the next "animation frame"
	 */

	public void step() {
		topLeft.x += xSpeed;
		bottomRight.x += xSpeed;

		topLeft.y += ySpeed;
		bottomRight.y += ySpeed;
	}

}
