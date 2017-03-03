/**
 *
 */
package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Turtles.NewTurtle;

/**
 * Created by th174 on 2/28/2017.
 */
@FunctionalInterface
public interface TurtleMovement extends IterableInvokable, TurtleIterable {

    Number operation(NewTurtle turtle, Variable var1);

    @Override
    default int expectedArity() {
        return 1;
    }

    @Override
    default Variable operation(Environment env, Expression... vargs) throws Expression.EvaluationTargetException {
        return forEachTurtle(env.getTurtles(), env, vargs);
    }

    @Override
    default Object doTurtle(NewTurtle turtle, Environment env, Expression... vargs) throws Expression.EvaluationTargetException {
        return operation(turtle, vargs[0].eval(env));
    }
}
