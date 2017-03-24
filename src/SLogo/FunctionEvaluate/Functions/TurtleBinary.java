package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Repl;
import SLogo.Turtles.Turtle;

/**
 * This interface allows for commands with 2 arguments that operate on each turtle
 *
 * @author Created by th174 on 2/28/2017.
 */
@FunctionalInterface
public interface TurtleBinary extends IterableInvokable, TurtleIterable {

    /**
     * @param turtle Turtle to be operated on
     * @param var1   First argument of operation
     * @param var2   Second argument of operation
     * @return Result of operation
     */
    Object operation(Turtle turtle, double var1, double var2);

    @Override
    default int minimumArity() {
        return 2;
    }

    @Override
    default Variable operation(Repl repl, Environment env, Expression... exprs) {
        return forEachTurtle(repl, env, env.getActiveTurtleList(), exprs);
    }

    @Override
    default Object doTurtle(Repl repl, Environment env, Turtle turtle, Expression... exprs) {
        return operation(turtle, exprs[0].eval(repl, env).numericalContext(), exprs[1].eval(repl, env).numericalContext());
    }
}
