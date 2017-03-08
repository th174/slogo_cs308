package SLogo.Turtles;


import java.util.Observable;
import java.util.Observer;

/**
 * Created by th174 on 3/2/2017.
 */
public class ObservableTurtle extends Observable implements NewTurtle {
    private static final double[] NO_POS_CHANGE = {0, 0};
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
        notifyObservers(new double[]{dx, dy});
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
    public double moveForward(double distance) {
        xPos += Math.cos(headingAngle) * distance;
        yPos += Math.sin(headingAngle) * distance;
        notifyObservers(new double[]{Math.cos(headingAngle) * distance, Math.sin(headingAngle) * distance});
        return distance;
    }

    @Override
    public double rotateCCW(double angle) {
        headingAngle += angle;
        notifyObservers(NO_POS_CHANGE);
        return 0;
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
    public void removeObserver(Observer o) {
        deleteObserver(o);
    }

    private void notifyObservers(double[] arg) {
        super.notifyObservers(new Object[]{isPenDown(), getHeading(), arg[0], arg[1], !isTurtleShow()});
    }
}
