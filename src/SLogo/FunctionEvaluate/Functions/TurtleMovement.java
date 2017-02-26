/**
 *
 */
package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.RecursiveExpression;

/**
 * Superclass of all function classes that deal with
 * a Turtle's movement, such as changing the heading or
 * location. These functions all take one argument.
 *
 * @author Stone Mathers
 *         Created 2/24/17
 */
public abstract class TurtleMovement extends TurtleCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public TurtleMovement(Environment env) {
        super(env);
    }

    public abstract NumberVariable operation(Variable var1);

    @Override
    public Variable invoke(String[] flags, Variable[] args, RecursiveExpression[] expr, RecursiveExpression[] alt) {
        if (args.length != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new UnexpectedArgumentException(EXPECTED_NUMBER_OF_ARGUMENTS, args.length);
        } else {
            return operation(args[0]);
        }
    }
}
