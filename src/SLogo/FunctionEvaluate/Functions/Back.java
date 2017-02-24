package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Turtles.Turtle;

/**
 * Implements BACK/BK command.
 * 
 * @author Stone Mathers
 * Created 2/24/17
 */

public class Back extends TurtleMovement {
	
	protected NumberVariable operation(Variable var1){
		NumberVariable num = (NumberVariable)var1;
		//Turtle turt;  TODO: How are we accessing the Turtle? Passed in?
		//turt.move(num.negate().toNumber());
		return num;
	}
	
}
