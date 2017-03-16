package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Repl;
import SLogo.Turtles.Turtle;

/**
 * This interface allows for commands that query information on each active turtle
 *
 * @author Created by th174 on 2/28/2017.
 */
@FunctionalInterface
public interface TurtleProperty extends Invokable, TurtleIterable {

    /**
     * @param turtle Turtle to be operated on
     * @return Result of operation
     */
    Object operation(Turtle turtle);

    @Override
    default int minimumArity() {
        return 0;
    }

    @Override
    default Variable eval(Repl repl, Environment env, Expression... exprs) {
        return forEachTurtle(repl, env, env.getActiveTurtleList(), exprs);
    }

    @Override
    default Object doTurtle(Repl repl, Environment env, Turtle turtle, Expression... exprs) {
        return operation(turtle);
    }
}
