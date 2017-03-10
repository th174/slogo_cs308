/**
 *
 */
package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Turtles.Turtle;

/**
 * Created by th174 on 2/28/2017.
 */
@FunctionalInterface
public interface TurtleMovement extends IterableInvokable, TurtleIterable {

    Number operation(Turtle turtle, double var1);

    @Override
    default int minimumArity() {
        return 1;
    }

    @Override
    default Variable operation(Environment env, Expression... vargs)  {
        return forEachTurtle(env.getActiveTurtleList(), env, vargs);
    }

    @Override
    default Object doTurtle(Turtle turtle, Environment env, Expression... vargs)  {
        return operation(turtle, vargs[0].eval(env).toNumber());
    }
}
