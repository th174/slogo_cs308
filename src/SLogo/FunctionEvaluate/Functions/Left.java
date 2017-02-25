package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Turtles.Turtle;

/**
 * Implements LEFT/LT command.
 * 
 * @author Stone Mathers
 * Created 2/24/17
 */

public class Left extends TurtleMovement {

	public Left(Environment env){
		super(env);
	}
	
	@Override
	public NumberVariable operation(Variable var1) {
		NumberVariable num = (NumberVariable)var1;
		Turtle turt = this.getEnvironment().getTurtle();
		turt.turn(num.toNumber());
		return num;
	}

}
