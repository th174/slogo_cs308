package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.Turtles.Turtle;

/**
 * Implements PENUP/PU command.
 * 
 * @author Stone Mathers
 * Created 2/25/17
 */
public class PenUp extends TurtleSettings {

	public PenUp(Environment env) {
		super(env);
	}

	@Override
	public NumberVariable operation() {
		Turtle turt = this.getEnvironment().getTurtle();
		turt.liftPen();
		return new NumberVariable(0);
	}

}
