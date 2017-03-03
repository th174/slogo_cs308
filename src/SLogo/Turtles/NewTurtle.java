package SLogo.Turtles;

import java.util.Observer;

/**
 * Created by th174 on 3/2/2017.
 */
public interface NewTurtle {
    /**
     * @return Angle at which the Turtle is facing
     */
    double getHeading();

    /**
     * @param angle Angle at which to set heading
     * @return change in heading angle
     */
    double setHeading(double angle);

    /**
     * Set heading towards a specific point
     *
     * @param x x-coordinate of point
     * @param y y-coordinate of point
     * @return angle moved
     */
    default double setHeadingTowards(double x, double y) {
        return setHeading(Math.atan2(y, x));
    }

    /**
     * Moves to absolute position
     *
     * @param x x-coordinate to move to
     * @param y y-coordinate to move to
     * @return distance moved
     */
    double setXY(double x, double y);

    /**
     * @return x-coordinate
     */
    double getX();

    /**
     * @return y-coordinate
     */
    double getY();

    /**
     * @param distance amount to move forward
     * @return distance moved
     */
    double moveForward(double distance);

    /**
     * @param distance amount to move backward
     * @return distance moved
     */
    default double moveBackward(double distance) {
        return -moveForward(-distance);
    }

    /**
     * @param angle to rotate left
     * @return angle moved
     */
    double rotateCCW(double angle);

    /**
     * @param angle to rotate left
     * @return angle moved
     */
    default double rotateCW(double angle) {
        return -rotateCCW(-angle);
    }

    /**
     * @param isPenDown new state of isPenDown
     * @return isPenDown
     */
    boolean setPenDown(boolean isPenDown);

    /**
     * Sets pen to up position.
     *
     * @return 0
     */
    default int penUp() {
        setPenDown(false);
        return 0;
    }

    /**
     * Sets pen to down position.
     *
     * @return 1
     */
    default int penDown() {
        setPenDown(true);
        return 0;
    }

    /**
     * @return true if pen is down, false if pen is up
     */
    boolean isPenDown();

    /**
     * @param isTurtleShow new state of isTurtleShow
     * @return is
     */
    boolean setTurtleShow(boolean isTurtleShow);

    /**
     * Sets turtle to show
     *
     * @return 1
     */
    default int showTurtle() {
        setTurtleShow(true);
        return 1;
    }

    /**
     * Sets turtle to hide
     *
     * @return 0
     */
    default int hideTurtle() {
        setTurtleShow(false);
        return 0;
    }

    /**
     * @return returns true if turtle showing, else false if turtle hidden
     */
    boolean isTurtleShow();

    /**
     * Calculate coordinate changes required to return Turtle to (0,0)
     * from (curX, curY) and sets all instance variables to default values.
     *
     * @return distance moved
     */
    default double reset() {
        return setXY(0, 0);
    }

    /**
     * Add an object as a listener
     *
     * @author Riley Nisbet
     */
    void addObserver(Observer o);

    /**
     * Remove a listener
     *
     * @author Riley Nisbet
     */
    void removeObserver(Observer o);

    /**
     * Tell all listeners that something has changed
     *
     * @author Riley Nisbet
     */
    void notifyObservers();
}
