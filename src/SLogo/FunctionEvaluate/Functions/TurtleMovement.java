/**
 * 
 */
package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.RecursiveExpression;

/**
 * @author Stone Mathers
 * Created 2/24/17
 *
 */
public abstract class TurtleMovement implements Invokable {
	
	private Environment myEnvironment;

	public TurtleMovement(Environment env){
		myEnvironment = env;
	}
	
	int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
	
	@Override
	public Variable invoke(String[] flags, Variable[] args, RecursiveExpression[] expr, RecursiveExpression[] alt) {
		if (args.length != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new UnexpectedArgumentException(EXPECTED_NUMBER_OF_ARGUMENTS, args.length);
        } else {
            return operation(args[0]);
        }
	}
	
	protected Environment getEnvironment(){
		return myEnvironment;
	}
	
	protected abstract NumberVariable operation(Variable var1);

}
