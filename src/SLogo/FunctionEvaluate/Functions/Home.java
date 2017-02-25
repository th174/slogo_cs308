package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.Turtles.Turtle;

/**
 * Implements HOME command.
 * 
 * @author Stone Mathers
 * Created 2/25/17
 */
public class Home extends TurtleSettings {

	public Home(Environment env) {
		super(env);
	}

	@Override
	public NumberVariable operation() {
		Turtle turt = this.getEnvironment().getTurtle();
		return new NumberVariable(move(turt));
	}
	
	private double move(Turtle turt){
		double xChange = Math.abs(turt.getX());
		double yChange = Math.abs(turt.getY());
		
		turt.reset();
		
		return Math.sqrt(Math.pow(xChange, 2) + Math.pow(yChange, 2));
	}

}
