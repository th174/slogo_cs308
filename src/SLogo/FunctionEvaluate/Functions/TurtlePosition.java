package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Functions.Invokable.UnexpectedArgumentException;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.RecursiveExpression;

/**
 * Superclass of all function classes that deal with
 * a Turtle's position. This includes changing the position
 * or performing operations relative to the position. These 
 * functions all take two arguments.
 * 
 * @author Stone Mathers
 * Created 2/25/17
 */
public abstract class TurtlePosition extends EnvironmentCommand{
	
	public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

	public TurtlePosition(Environment env){
		super(env);
	}

	public abstract NumberVariable operation(Variable var1, Variable var2);

    @Override 
    public Variable invoke(String[] flags, Variable[] args, RecursiveExpression[] expr, RecursiveExpression[] alt){
    	if (args.length != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new UnexpectedArgumentException(EXPECTED_NUMBER_OF_ARGUMENTS, args.length);
        } else {
            return operation(args[0], args[1]);
        }
    }
}
