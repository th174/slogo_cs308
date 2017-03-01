package SLogo.Turtles;

import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;

/**
 * Represents the data behind the Turtle in SLogo.
 * Holds position, heading, hidden state, and pen state.
 * Heading is held in degrees, in a range from -180 to 180.
 * 
 * @author Stone Mathers
 * Created 2/25/17
 */
public class SLogoTurtle extends Observable implements Turtle {
	
	private ArrayList<Observer> observers;
	private double myChangeX;
	private double myChangeY;
	private double myHeading;
	boolean penDown;
	boolean hidden;
	
	public SLogoTurtle(){
		this(90);
	}
	
	public SLogoTurtle(double heading){
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

	@Override
	public double getHeading() {
		return myHeading;
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
	public void setHeading(double angle) {
		angle = adjustAngle(angle);
		myHeading = angle;
		notifyObservers();
	}

	@Override
	public void liftPen() {
		penDown = false;
		notifyObservers();
	}

	@Override
	public void dropPen() {
		penDown = true;
		notifyObservers();
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
	}
	
	@Override
	public void hide() {
		hidden = true;
		notifyObservers();
	}

	@Override
	public void show() {
		hidden = false;	
		notifyObservers();
	}

	@Override
	public boolean hidden() {
		return hidden;
	}

	@Override
	public void reset(double curX, double curY) {
		this.setChangeX(-curX);
		this.setChangeY(-curY);
		this.setHeading(90);
		this.dropPen();
		this.show();
		notifyObservers();
	}
	
	/**
	 * Restrains all angles between -180 and 180
	 * by adjusting any angles that fall outside of that range.
	 * 
	 * @param angle
	 * @return
	 */
	private double adjustAngle(double angle){
		double newAngle = angle - ((angle/360) * 360); //puts angle into range from -360 to 360
		if(newAngle > 180){
			return (newAngle - 360);
		}
		else if(newAngle < -180){
			return (newAngle + 360);
		}
		else{
			return newAngle;
		}
	}

	public void addObserver(Observer o){
		observers.add(o);
	}

	public void removeObserver(Observer o){
		observers.remove(o);
	}

	public void notifyObservers(){
		for (Observer o : observers){
			o.update(this, new Object[] {penDown, myHeading, myChangeX, myChangeY, hidden});
		}
	}
}