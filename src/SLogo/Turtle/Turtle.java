package SLogo.Turtle;

import javafx.scene.image.Image;

public interface Turtle {

	public int getX();
	
	public int getY();
	
	public double getHeading();
	
	public void setX(double x);
	
	public void setY(double y);
	
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
	
	public void turn(double degrees);
	
	/**
	 * @return true if Turtle is hidden, false if Turtle is not hidden
	 */
	public boolean hidden();
	
	
	/**
	 * Returns Turtle to (0,0) and sets all instance variables to default values
	 */
	public void reset();
	
}
