package SLogo.Turtles;

import java.util.Observer;

/**
 * Created by th174 on 3/2/2017.
 */
public class NewTurtleImpl implements NewTurtle {
    @Override
    public int id() {
        return 0;
    }

    @Override
    public double getHeading() {
        return 0;
    }

    @Override
    public double setHeading(double angle) {
        return 0;
    }

    @Override
    public double setXY(double x, double y) {
        return 0;
    }

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public double moveForward(double distance) {
        return 0;
    }

    @Override
    public double rotateCCW(double angle) {
        return 0;
    }

    @Override
    public boolean setPenDown(boolean isPenDown) {
        return false;
    }

    @Override
    public boolean isPenDown() {
        return false;
    }

    @Override
    public boolean setTurtleShow(boolean isTurtleShow) {
        return false;
    }

    @Override
    public boolean isTurtleShow() {
        return false;
    }

    @Override
    public void addObserver(Observer o) {

    }

    @Override
    public void removeObserver(Observer o) {

    }

    @Override
    public void notifyObservers() {

    }
}
