package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.Turtles.Turtle;

/**
 * Implements PENDOWN/PD command.
 * 
 * @author Stone Mathers
 * Created 2/25/17
 */
public class PenDown extends TurtleSettings {

	public PenDown(Environment env) {
		super(env);
	}

	@Override
	public NumberVariable operation() {
		Turtle turt = this.getEnvironment().getTurtle();
		turt.dropPen();
		return new NumberVariable(1);
	}

}
