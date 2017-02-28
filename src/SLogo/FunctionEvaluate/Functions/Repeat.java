package SLogo.FunctionEvaluate.Functions;

import java.util.ResourceBundle;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Functions.Invokable.UnexpectedArgumentException;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.RecursiveExpression;

/**
 * Implements REPEAT command.
 * 
 * @author Stone Mathers
 * Created 2/26/17
 */
public class Repeat extends EnvironmentCommand {

	public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
	private ResourceBundle myResources = ResourceBundle.getBundle("resources.variables/variables");

	public Repeat(Environment env) {
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
		int loops = (int)Math.floor(arg1.toNumber());
		Environment env = this.getEnvironment();
		env.addUserVariable(myResources.getString("repCount"), new NumberVariable(1));
		Variable finalValue = new NumberVariable(0);
		
		if(loops > 0 && commands.length > 0){
			for(int i = 1; i <= loops; i++){
				for(RecursiveExpression expr: commands){
					try{
						finalValue = expr.eval(env);
					}catch(Exception e){
						throw new RuntimeException(e);
					}
				}
				Variable iteration = env.getVariableByName(myResources.getString("repCount"));
				env.addUserVariable(myResources.getString("repCount"),  new NumberVariable(1).sum(iteration));
			}
		}
		
		return finalValue;
		
	}

}
