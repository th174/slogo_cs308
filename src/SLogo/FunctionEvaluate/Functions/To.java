package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Functions.Invokable.UnexpectedArgumentException;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.RecursiveExpression;

/**
 * Implements TO command.
 * 
 * @author Stone Mathers
 * Created 2/26/2017
 */
public class To extends EnvironmentCommand {

	public static final int MINIMUM_NUMBER_OF_ARGUMENTS = 1;
	
	public To(Environment env) {
		super(env);
	}
	
	@Override
	public Variable invoke(String[] flags, Variable[] args, RecursiveExpression[] expr, RecursiveExpression[] alt) {
		if (args.length < MINIMUM_NUMBER_OF_ARGUMENTS) {
            throw new UnexpectedArgumentException(MINIMUM_NUMBER_OF_ARGUMENTS, args.length);
        } else {
            return operation(args, expr);
        }
	}
	
	private Variable operation(Variable[] args, RecursiveExpression[] commands){
		Environment env = this.getEnvironment();
		//TODO
		return null;
	}
}
