package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Functions.Invokable.UnexpectedArgumentException;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.RecursiveExpression;

/**
 * Implements IF command.
 * 
 * @author Stone Mathers
 * Created 2/26/2017
 */
public class IfCommand extends EnvironmentCommand {

	public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
	
	public IfCommand(Environment env) {
		super(env);
	}
	
	@Override
	public Variable invoke(String[] flags, Variable[] args, RecursiveExpression[] expr, RecursiveExpression[] alt) {
		if (args.length != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new UnexpectedArgumentException(EXPECTED_NUMBER_OF_ARGUMENTS, args.length);
        } else {
            return operation(args[0], expr);
        }
	}
	
	private Variable operation(Variable arg1, RecursiveExpression[] commands){
		Environment env = this.getEnvironment();
		Variable finalValue = new NumberVariable(0);
		
		if(arg1.toNumber() != 0){
			for(RecursiveExpression expr: commands){
				try{
					finalValue = expr.eval(env);
				}catch(Exception e){
					throw new RuntimeException(e);
				}
			}
		}
		
		return finalValue;
	}
}
