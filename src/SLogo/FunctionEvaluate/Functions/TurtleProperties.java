package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Repl;
import SLogo.Turtles.Turtle;

/**
 * Created by th174 on 2/28/2017.
 */
@FunctionalInterface
public interface TurtleProperties extends Invokable, TurtleIterable {

    Object operation(Turtle turtle);

    @Override
    default int minimumArity() {
        return 0;
    }

    @Override
    default Variable eval(Repl repl, Environment env, Expression... expr) {
        return forEachTurtle(repl, env.getActiveTurtleList(), env, expr);
    }

    @Override
    default Object doTurtle(Repl repl, Turtle turtle, Environment env, Expression... vargs) {
        return operation(turtle);
    }
}
