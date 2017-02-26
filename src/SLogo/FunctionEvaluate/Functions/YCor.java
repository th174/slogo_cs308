package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.Turtles.Turtle;

/**
 * Implements YCOR command.
 * 
 * @author Stone Mathers
 * Created 2/25/17
 */
public class YCor extends TurtleSettings {

	public YCor(Environment env) {
		super(env);
	}

	@Override
	public NumberVariable operation() {
		Turtle turt = this.getEnvironment().getTurtle();
		return new NumberVariable(turt.getY());
	}

}