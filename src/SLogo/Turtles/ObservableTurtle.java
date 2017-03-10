package SLogo.Turtles;


import javafx.util.Pair;

import java.util.Observable;

/**
 * Created by th174 on 3/2/2017.
 */
public class ObservableTurtle extends Observable implements Turtle {
    private static final Pair<Double, Double> NO_POS_CHANGE = new Pair<>(0.0, 0.0);
    private int id;
    private double xPos;
    private double yPos;
    private double headingAngle;
    private boolean isTurtleShowing;
    private boolean isPenDown;

    public ObservableTurtle(int i) {
        this.id = i;
        this.xPos = DEFAULT_X_POS;
        this.yPos = DEFAULT_Y_POS;
        this.headingAngle = DEFAULT_HEADING;
        this.isTurtleShowing = DEFAULT_IS_SHOWING;
        this.isPenDown = DEFAULT_IS_PEN_DOWN;
        notifyObservers(NO_POS_CHANGE);
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public double getHeading() {
        return headingAngle;
    }

    @Override
    public double setHeading(double angle) {
        double change = angle - headingAngle;
        headingAngle = angle;
        notifyObservers(NO_POS_CHANGE);
        return change;
    }

    @Override
    public double setXY(double x, double y) {
        double dx = x - xPos;
        double dy = y - yPos;
        double dist = Math.hypot(dx, dy);
        xPos = x;
        yPos = y;
        notifyObservers(new Pair<>(dx, dy));
        return dist;
    }

    @Override
    public double getX() {
        return xPos;
    }

    @Override
    public double getY() {
        return yPos;
    }

    @Override
    public boolean setPenDown(boolean isPenDown) {
        this.isPenDown = isPenDown;
        notifyObservers(NO_POS_CHANGE);
        return isPenDown;
    }

    @Override
    public boolean isPenDown() {
        return isPenDown;
    }

    @Override
    public boolean setTurtleShow(boolean isTurtleShowing) {
        this.isTurtleShowing = isTurtleShowing;
        notifyObservers(NO_POS_CHANGE);
        return isTurtleShowing;
    }

    @Override
    public boolean isTurtleShow() {
        return isTurtleShowing;
    }

    @Override
    public void notifyObservers(Object coordinates) {
        setChanged();
        super.notifyObservers(coordinates);
    }
}
