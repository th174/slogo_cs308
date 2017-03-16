/**
 *
 */
package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Repl;
import SLogo.Turtles.Turtle;

/**
 * This interface allows for commands with one argument that operate on each active turtle
 *
 * @author Created by th174 on 2/28/2017.
 */
@FunctionalInterface
public interface TurtleUnary extends IterableInvokable, TurtleIterable {

    /**
     * @param turtle Turtle to be operated on
     * @param var1   First argument of operation
     * @return Result of operation
     */
    Number operation(Turtle turtle, double var1);

    @Override
    default int minimumArity() {
        return 1;
    }

    @Override
    default Variable operation(Repl repl, Environment env, Expression... exprs) {
        return forEachTurtle(repl, env, env.getActiveTurtleList(), exprs);
    }

    @Override
    default Object doTurtle(Repl repl, Environment env, Turtle turtle, Expression... exprs) {
        return operation(turtle, exprs[0].eval(repl, env).toNumber());
    }
}
