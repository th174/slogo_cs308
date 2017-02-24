/**
 * 
 */
package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Functions.Invokable.UnexpectedArgumentException;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;

/**
 * @author Stone Mathers
 * Created 2/24/17
 *
 */
public abstract class TurtleMovement implements Invokable {

	int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
	
	@Override
	public NumberVariable invoke(String[] flags, Variable... args) {
		if (args.length != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new UnexpectedArgumentException(EXPECTED_NUMBER_OF_ARGUMENTS, args.length);
        } else {
            return operation(args[0]);
        }
	}
	
	protected abstract NumberVariable operation(Variable var1);

}
