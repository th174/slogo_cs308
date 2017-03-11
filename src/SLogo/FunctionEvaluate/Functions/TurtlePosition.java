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
public interface TurtlePosition extends IterableInvokable, TurtleIterable {

    Object operation(Turtle turtle, double var1, double var2);

    @Override
    default int minimumArity() {
        return 2;
    }

    @Override
    default Variable operation(Repl repl, Environment env, Expression... vargs) {
        return forEachTurtle(repl, env.getActiveTurtleList(), env, vargs);
    }

    @Override
    default Object doTurtle(Repl repl, Turtle turtle, Environment env, Expression... vargs) {
        return operation(turtle, vargs[0].eval(repl, env).toNumber(), vargs[1].eval(repl, env).toNumber());
    }
}
