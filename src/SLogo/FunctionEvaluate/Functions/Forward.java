package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Turtles.Turtle;

/**
 * Implements FORWARD/FD command.
 * 
 * @author Stone Mathers
 * Created 2/24/17
 */

public class Forward extends TurtleMovement{
	
	protected NumberVariable operation(Variable var1){
		NumberVariable num = (NumberVariable)var1;
		//Turtle turt;  TODO: How are we accessing the Turtle? Passed in?
		//turt.move(num.toNumber());
		return num;
	}

}
