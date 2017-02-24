package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Turtles.Turtle;

/**
 * Implements SETHEADING/SETH command.
 * 
 * @author Stone Mathers
 * Created 2/24/17
 */

public class SetHeading extends TurtleMovement {

	@Override
	protected NumberVariable operation(Variable var1) {
		NumberVariable num = (NumberVariable)var1;
		// TODO Turtle turt = 
		// TODO turt.setHeading(num.toNumber());
		return num;
	}

}