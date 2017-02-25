package SLogo.Turtles;


/**
 * Handles all data behind the Turtle that will be
 * displayed in the GUI.
 * 
 * @author Stone Mathers
 * Created 2/23/17
 */

public interface Turtle {

	/**
	 * @return X-position
	 */
	public int getX();
	
	/**
	 * @return Y-position
	 */
	public int getY();
	
	/**
	 * @return Angle at which the Turtle is facing
	 */
	public double getHeading();
	
	/**
	 * @param x X-position to place Turtle
	 */
	public void setX(double x);
	
	/**
	 * @param y Y-position to place Turtle
	 */
	public void setY(double y);
	
	/**
	 * @param angle Angle at which to set heading
	 */
	public void setHeading(double angle);
	
	/**
	 * Sets pen to up position.
	 * 
	 */
	public void liftPen();
	
	/**
	 * Sets pen to down position.
	 * 
	 */
	public void dropPen();
	
	/**
	 * @return true if pen is down, false if pen is up
	 */
	public boolean penDown();
	
	/**
	 * Turns Turtle the number of degrees given.
	 * A positive value turns the Turtle to the left,
	 * a negative value turns the Turtle to the right.
	 * 
	 * @param degrees Degrees to turn the turtle
	 */
	public void turn(double degrees);
	
	/**
	 * Moves Turtle the given number of pixels 
	 * in the direction of its heading.
	 * 
	 * @param pixels Number of pixels to move Turtle
	 */
	public void move(int pixels);
	
	/**
	 * @return true if Turtle is hidden, false if Turtle is not hidden
	 */
	public boolean hidden();
	
	
	/**
	 * Returns Turtle to (0,0) and sets all instance variables to default values
	 */
	public void reset();
	
}
