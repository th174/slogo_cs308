package SLogo.Turtles;

import java.util.Observer;

/**
 * Created by th174 on 3/2/2017.
 */
public interface Turtle {
    double DEFAULT_X_POS = 0;
    double DEFAULT_Y_POS = 0;
    double DEFAULT_HEADING = 90;
    boolean DEFAULT_IS_SHOWING = false;
    boolean DEFAULT_IS_PEN_DOWN = false;

    /**
     * @return current ID of turtle
     */
    int id();

    /**
     * @return Angle at which the Turtle is facing in degrees
     */
    double getHeading();

    /**
     * @param angle Angle at which to set heading in degrees
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
    default double moveForward(double distance) {
        setXY(getX() + Math.cos(Math.toRadians(getHeading())) * distance, getY() + Math.sin(Math.toRadians(getHeading())) * distance);
        return distance;
    }

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
    default double rotateCCW(double angle) {
        setHeading(getHeading() + angle);
        return angle;
    }

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
     * @param isTurtleShowing new state of isTurtleShowing
     * @return isTurtleShowing
     */
    boolean setTurtleShow(boolean isTurtleShowing);

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
        setPenDown(DEFAULT_IS_PEN_DOWN);
        setTurtleShow(DEFAULT_IS_SHOWING);
        setHeading(DEFAULT_HEADING);
        return setXY(DEFAULT_X_POS, DEFAULT_Y_POS);
    }

    /**
     * Add an object as a listener
     *
     * @author Riley Nisbet
     */
    void addObserver(Observer o);
}
