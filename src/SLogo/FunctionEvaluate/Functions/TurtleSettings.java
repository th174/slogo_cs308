package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Functions.Invokable.UnexpectedArgumentException;
import SLogo.FunctionEvaluate.Variables.Variable;

/**
 * Superclass of all function classes that deal with
 * a Turtle's settings. This includes changing a setting
 * and inquiring about a setting. These functions all take
 * no arguments.
 * 
 * @author Stone Mathers
 * Created 2/25/17
 */
public abstract class TurtleSettings extends TurtleCommand {

	public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

	public TurtleSettings(Environment env){
		super(env);
	}

	public abstract Variable operation();
	
    @Override
    public Variable invoke(String[] flags, Variable[] args) {
        if (args.length != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new UnexpectedArgumentException(EXPECTED_NUMBER_OF_ARGUMENTS, args.length);
        } else {
            return operation();
        }
    }

}
