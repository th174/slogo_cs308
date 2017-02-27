package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;

/**
 * Superclass for all functions that deal with the Turtle.
 * 
 * @author Stone Mathers
 * Created 2/25/17
 */
public abstract class EnvironmentCommand implements Invokable {

	private Environment myEnvironment;
	
	public EnvironmentCommand(Environment env){
		myEnvironment = env;
	}
	
	protected Environment getEnvironment(){
		return myEnvironment;
	}
}
