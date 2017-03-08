package SLogo.Turtles;

import java.util.Observer;

/**
 * Handles all data behind the Turtle that will be
 * displayed in the GUI.
 *
 * @author Stone Mathers
 *         Created 2/23/17
 */

public interface Turtle {

	/**
	 * @return Change in X-position
	 */
	public double getChangeX();
	
	/**
	 * @return Change in Y-position
	 */
	public double getChangeY();
	
	/**
	 * @return Angle at which the Turtle is facing
	 */
	public double getHeading();
	
	/**
	 * Set the Turtle's change in X-position.
	 * 
	 * @param changeX 
	 */
	public void setChangeX(double changeX);
	
	/**
	 * Set the Turtle's change in Y-position.
	 * 
	 * @param changeY
	 */
	public void setChangeY(double changeY);
	
	/**
	 * @param angle Angle at which to set heading
	 * @return change in angle
	 */
	public double setHeading(double angle);
	
	/**
     * Set heading towards a specific point
     *
     * @param x x-coordinate of point
     * @param y y-coordinate of point
     * @return angle moved
     */
    public double setHeadingTowards(double x, double y);
	
	/**
	 * Sets pen to up position.
	 * @return 0
	 */
	public int liftPen();
	
	/**
	 * Sets pen to down position.
	 * @return 1
	 */
	public int dropPen();
	
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
	 * Calculates coordinate changes required to move the Turtle 
	 * the given number of pixels in the direction of its heading.
	 * 
	 * @param pixels Number of pixels to move Turtle
	 */
	public void move(double pixels);
	
	/**
	 * Sets Turtle's state to hidden.
	 * @return 0
	 */
	public int hide();
	
	/**
	 * Sets Turtle's state to not hidden.
	 * @return 1
	 */
	public int show();
	
	/**
	 * @return true if Turtle is hidden, false if Turtle is not hidden
	 */
	public boolean hidden();
	
	
	/**
	 * Calculate coordinate changes required to return Turtle to (0,0)
	 * from (curX, curY) and sets all instance variables to default values.
	 * 
	 * @param curX Current X-coordinate
	 * @param curY Current Y-coordinate
	 */
	public void reset(double curX, double curY);
	
	/**
	 * Add an object as a listener
	 * 
	 * @author Riley Nisbet
	 */
	public void addObserver(Observer o);
	
	/**
	 * Remove a listener
	 * 
	 * @author Riley Nisbet
	 */
	public void removeObserver(Observer o);
	
	/**
	 * Tell all listeners that something has changed
	 * 
	 * @author Riley Nisbet
	 */
	public void notifyObservers();
}
