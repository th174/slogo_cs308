package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Turtles.Turtle;

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
		return new NumberVariable(move(turt, x, y));
	}
	
	/**
	 * Moves Turtle to the given x- and y-coordinates.
	 * 
	 * @param turt Turtle to be moved
	 * @param newX X-coordinate to be moved to
	 * @param newY Y-coordinate to be moved to
	 * @return Distance the turtle was moved
	 */
	private double move(Turtle turt, double newX, double newY){
		//TODO
		//Retrieve x and y from Sprite
		//Calculate vectors (3 lines at bottom)
		//setChangeX and setChangeY
		
		double oldX = turt.getX();
		double oldY = turt.getY();
		
		turt.setX(newX);
		turt.setY(newY);
		
		double xChange = Math.abs(newX - oldX);
		double yChange = Math.abs(newY - oldY);
		return Math.sqrt(Math.pow(xChange, 2) + Math.pow(yChange, 2));
	}

}
