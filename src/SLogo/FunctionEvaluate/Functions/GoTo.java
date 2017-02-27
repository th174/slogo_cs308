package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Turtles.Turtle;
import SLogo.View.Sprite.Sprite;

/**
 * Implements SETXY/GOTO command.
 * 
 * @author Stone Mathers
 * Created 2/25/17
 */
public class GoTo extends TurtlePosition {

	public GoTo(Environment env) {
		super(env);
	}

	@Override
	public NumberVariable operation(Variable var1, Variable var2) {
		double x = var1.toNumber();
		double y = var2.toNumber();
		Turtle turt = this.getEnvironment().getTurtle();
		Sprite sprite = this.getEnvironment().getSprite();
		return new NumberVariable(calculateVectors(turt, sprite, x, y));
	}
	
	/**
	 * Calculates vectors to move Turtle to the given x- and y-coordinates.
	 * 
	 * @param turt Turtle to be moved
	 * @param newX X-coordinate to be moved to
	 * @param newY Y-coordinate to be moved to
	 * @return Distance the turtle was moved
	 */
	private double calculateVectors(Turtle turt, Sprite sprite, double newX, double newY){
		double oldX = sprite.getPosition()[0];
		double oldY = sprite.getPosition()[1];
		
		double xChange = newX - oldX;
		double yChange = newY - oldY;
		
		turt.setChangeX(xChange);
		turt.setChangeY(yChange);
		
		
		return Math.sqrt(Math.pow(xChange, 2) + Math.pow(yChange, 2));
	}

}
