package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.BoolVariable;
import SLogo.Turtles.Turtle;

/**
 * Implements SHOWINGP/SHOWING? command.
 * 
 * @author Stone Mathers
 * Created 2/25/17
 */
public class ShowingP extends TurtleSettings {

	public ShowingP(Environment env) {
		super(env);
	}

	@Override
	public BoolVariable operation() {
		Turtle turt = this.getEnvironment().getTurtle();
		if(turt.hidden()){
			return BoolVariable.FALSE;
		}
		else{
			return BoolVariable.TRUE;
		}
	}

}
