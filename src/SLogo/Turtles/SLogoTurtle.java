package SLogo.Turtles;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents the data behind the Turtle in SLogo.
 * Holds position, heading, hidden state, and pen state.
 * Heading is held in degrees, in a range from -180 to 180.
 *
 * @author Stone Mathers
 *         Created 2/25/17
 */
public class SLogoTurtle extends Observable implements Turtle {

	private int myID;
    private ArrayList<Observer> observers;
    private double myChangeX;
    private double myChangeY;
    private double myHeading;
    boolean penDown;
    boolean hidden;

    public SLogoTurtle(int id) {
        this(90, id);
    }

    public SLogoTurtle(double heading, int id) {
        myHeading = adjustAngle(heading);
        observers = new ArrayList<Observer>();
        this.dropPen();
        this.show();
        myChangeX = 0;
        myChangeY = 0;
    }

    @Override
    public double getChangeX() {
        return myChangeX;
    }

    @Override
    public double getChangeY() {
        return myChangeY;
    }

    /**
	 * @return Angle at which the Turtle is facing, always falls between -180 and 180 (inclusive)
	 */
    @Override
    public double getHeading() {
        return myHeading;
    }
    
    @Override
    public double setHeadingTowards(double x, double y) {
        return setHeading(Math.atan2(y, x));
    }

    @Override
    public void setChangeX(double changeX) {
        myChangeX = changeX;
    }

    @Override
    public void setChangeY(double changeY) {
        myChangeY = changeY;
    }

    @Override
    public double setHeading(double angle) {
        angle = adjustAngle(angle);
        double angleDifference = angle - myHeading ;
        myHeading = angle;
        notifyObservers();
        return angleDifference;
    }

    @Override
    public int liftPen() {
        penDown = false;
        notifyObservers();
        return 0;
    }

    @Override
    public int dropPen() {
        penDown = true;
        notifyObservers();
        return 1;
    }

    @Override
    public boolean penDown() {
        return penDown;
    }

    @Override
    public void turn(double degrees) {
        myHeading += degrees;
        this.setHeading(adjustAngle(myHeading));
    }

    @Override
    public void move(double pixels) {
        this.setChangeX(pixels * Math.cos(Math.toRadians(this.getHeading())));
        this.setChangeY(pixels * Math.sin(Math.toRadians(this.getHeading())));
        notifyObservers();
    }

    @Override
    public int hide() {
        hidden = true;
        notifyObservers();
        return 0;
    }

    @Override
    public int show() {
        hidden = false;
        notifyObservers();
        return 1;
    }

    @Override
    public boolean hidden() {
        return hidden;
    }

    @Override
    public void reset(double curX, double curY) {
    	System.out.println("currPos " + curX + " " + curY);
    	this.liftPen();
        this.setChangeX(-curX);
        this.setChangeY(-curY);
        this.setHeading(90);
        this.dropPen();
        this.show();
    }

    /**
     * Restrains all angles between -180 and 180
     * by adjusting any angles that fall outside of that range.
     *
     * @param angle
     * @return
     */
    private double adjustAngle(double angle) {
        double newAngle = angle - (((int)angle / 360) * 360); //puts angle into range from -360 to 360
        if (newAngle > 180) {
            return (newAngle - 360);
        } else if (newAngle < -180) {
            return (newAngle + 360);
        } else {
            return newAngle;
        }
    }

    /**
     * Add an object as a listener
     *
     * @author Riley Nisbet
     */
    public void addObserver(Observer o) {
        observers.add(o);
        notifyObservers();
    }

    /**
     * Remove a listener
     *
     * @author Riley Nisbet
     */
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    /**
     * Tell all listeners that something has changed
     *
     * @author Riley Nisbet
     */
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(this, new Object[]{penDown, myHeading, myChangeX, myChangeY, hidden, myID});
            myChangeX = 0;
            myChangeY = 0;
        }
    }
}