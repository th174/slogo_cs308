package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Turtles.Turtle;
import SLogo.View.Sprite.Sprite;

/**
 * Implements TOWARDS command.
 * 
 * @author Stone Mathers
 * Created 2/25/17
 */
public class Towards extends TurtlePosition {


	public Towards(Environment env) {
		super(env);
	}


	@Override
	public NumberVariable operation(Variable var1, Variable var2) {
		double x = var1.toNumber();
		double y = var2.toNumber();
		Turtle turt = this.getEnvironment().getTurtle();
		return new NumberVariable(turn(turt, x, y));
	}

	/**
	 * Turns the given Turtle towards the given x- and y-coordinates.
	 * 
	 * @param turt Turtle to be turned
	 * @param x X-coordinate to be turned towards
	 * @param y Y-coordinate to be turned towards
	 * @return Number of degrees the Turtle was turned
	 */
	private double turn(Turtle turt, double newX, double newY){
		Sprite sprite = this.getEnvironment().getSprite();
		double newHeading = calculateHeading(sprite, newX, newY);		
		double degMoved = Math.abs(turt.getHeading() - newHeading);		
		turt.setHeading(newHeading);
		return degMoved;
		
	}
	
	private double calculateHeading(Sprite sprite, double newX, double newY){
		double curX = sprite.getPosition()[0];
		double curY = sprite.getPosition()[1];
		double xDist = newX - curX;
		double yDist = newY - curY;
		double hypotenuse = Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
		double newHeading = Math.toDegrees(Math.acos(xDist/hypotenuse));
		if(yDist < 0){			//Because Math.acos only returns 0-180, this checks if it should be facing downward
			newHeading *= -1;
		}
		return newHeading;
	}
	
}
