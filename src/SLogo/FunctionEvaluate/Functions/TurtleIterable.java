package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.EnvironmentImpl;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Repl;
import SLogo.Turtles.Turtle;

import java.util.Collections;
import java.util.List;

import static SLogo.FunctionEvaluate.Variables.Variable.FALSE;

/**
 * This interface allows for commands that operate separately on each turtle
 *
 * @author Created by th174 on 3/2/2017.
 */
@FunctionalInterface
public interface TurtleIterable {

    /**
     * Operation to be done on each turtle
     *
     * @param repl   Current REPL session
     * @param env    Current local environment
     * @param turtle Turtle to be operated on
     * @param exprs  Expressions to be evaluated on each turtle
     * @return Result of evaluating expressions
     */
    Object doTurtle(Repl repl, Environment env, Turtle turtle, Expression... exprs);

    /**
     * Executes expressions on a list of turtles
     *
     * @param repl       Current REPL session
     * @param env        Current local environment
     * @param turtleList List of turtles to operate on
     * @param exprs      Expressions to execute
     * @return Result of operation on the last turtle in list
     */
    default Variable forEachTurtle(Repl repl, Environment env, List<Turtle> turtleList, Expression... exprs) {
        if (turtleList.isEmpty()) {
            return FALSE;
        } else if (turtleList.size() == 1) {
            return Variable.newInstance(doTurtle(repl, new EnvironmentImpl(env, Collections.singletonList(turtleList.get(0).id())), turtleList.get(0), exprs));
        } else {
            Variable.newInstance(doTurtle(repl, new EnvironmentImpl(env, Collections.singletonList(turtleList.get(0).id())), turtleList.get(0), exprs));
            return forEachTurtle(repl, env, turtleList.subList(1, turtleList.size()), exprs);
        }
    }
}
