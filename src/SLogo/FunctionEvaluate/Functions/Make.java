package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Functions.Invokable.UnexpectedArgumentException;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.RecursiveExpression;

/**
 * Implements MAKE/SET command.
 * 
 * @author Stone Mathers
 * Created 2/26/17
 */
public class Make extends EnvironmentCommand {

	public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
	
	public Make(Environment env) {
		super(env);
	}

	@Override
	public Variable invoke(String[] flags, Variable[] args, RecursiveExpression[] expr, RecursiveExpression[] alt) {
		if (args.length != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new UnexpectedArgumentException(EXPECTED_NUMBER_OF_ARGUMENTS, args.length);
        } else {
            return operation(args[0], args[1]);
        }
	}
	
	private Variable operation(Variable arg1, Variable arg2){
		this.getEnvironment().addUserVariable(arg1.toString(), arg2);
		return arg2;
	}
	
}
