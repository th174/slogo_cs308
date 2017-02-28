/**
 *
 */
package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Turtles.Turtle;

/**
 * Created by th174 on 2/28/2017.
 */
@FunctionalInterface
public interface TurtleMove extends Invokable {
    int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    NumberVariable operation(Turtle turtle, Variable var1);

    @Override
    default Variable invoke(Environment env, Expression... expr) throws Expression.EvaluationTargetException {
        if (expr.length != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new UnexpectedArgumentException(EXPECTED_NUMBER_OF_ARGUMENTS, expr.length);
        } else {
            return operation(env.getTurtle(), expr[0].eval(env));
        }
    }
}
