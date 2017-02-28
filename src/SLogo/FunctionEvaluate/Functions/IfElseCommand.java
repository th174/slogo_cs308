package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Functions.Invokable.UnexpectedArgumentException;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.RecursiveExpression;

/**
 * Implements IFELSE command.
 * 
 * @author Stone Mathers
 * Created 2/26/2017
 */
public class IfElseCommand extends EnvironmentCommand {

	public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
	
	public IfElseCommand(Environment env) {
		super(env);
	}
	
	@Override
	public Variable invoke(String[] flags, Variable[] args, RecursiveExpression[] expr, RecursiveExpression[] alt) {
		if (args.length != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new UnexpectedArgumentException(EXPECTED_NUMBER_OF_ARGUMENTS, args.length);
        } else {
            return operation(args[0], expr, alt);
        }
	}
	
	private Variable operation(Variable arg1, RecursiveExpression[] trueCommands, RecursiveExpression[] falseCommands){
		Variable finalValue = new NumberVariable(0);
		
		if(arg1.toNumber() != 0){
			evaluate(finalValue, trueCommands);
		}else{
			evaluate(finalValue, falseCommands);
		}
		
		return finalValue;
	}
	
	private void evaluate(Variable result, RecursiveExpression[] commands){
		Environment env = this.getEnvironment();
		for(RecursiveExpression expr: commands){
			try{
				result = expr.eval(env);
			}catch(Exception e){
				throw new RuntimeException(e);
			}
		}
	}
}
