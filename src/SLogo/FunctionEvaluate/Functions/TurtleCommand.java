package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;

/**
 * Superclass for all functions that deal with the Turtle.
 * 
 * @author Stone Mathers
 * Created 2/25/17
 */
public abstract class TurtleCommand implements Invokable {

	private Environment myEnvironment;
	
	public TurtleCommand(Environment env){
		myEnvironment = env;
	}
	
	protected Environment getEnvironment(){
		return myEnvironment;
	}
	
	public abstract Variable invoke(String[] flags, Variable... args);

}
