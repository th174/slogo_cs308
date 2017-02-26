package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.Turtles.Turtle;

/**
 * Implements HIDETURTLE/HT command.
 * 
 * @author Stone Mathers
 *
 */
public class HideTurtle extends TurtleSettings {

	public HideTurtle(Environment env) {
		super(env);
	}

	@Override
	public NumberVariable operation() {
		Turtle turt = this.getEnvironment().getTurtle();
		turt.hide();
		return new NumberVariable(0);
	}

}
