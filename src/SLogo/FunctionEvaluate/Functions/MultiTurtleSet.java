package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.EnvironmentImpl;
import SLogo.FunctionEvaluate.PredefinedCommandList;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Repl;
import SLogo.Turtles.Turtle;

import java.util.Arrays;

/**
 * This command allows the user to execute turtle commands in a new local environment with a filtered set of turtles
 *
 * @author Created by th174 on 3/2/2017.
 */
@FunctionalInterface
public interface MultiTurtleSet extends IterableInvokable {

    /**
     * A Predicate on that filters active turtles when true
     *
     * @param repl   Current REPL session
     * @param env    Current local env
     * @param turtle Turtle to be filtered
     * @param expr   Expression to be used to determine active turtles
     * @return True if the turtle is assigned active, otherwise false
     */
    boolean filter(Repl repl, Environment env, Turtle turtle, Expression expr);

    /**
     * Creates a sub environment with a filtered set of active turtles, and evaluates all expr in the sub environment
     *
     * @param repl  Current REPL session
     * @param env   Current dynamic runtime environment
     * @param exprs Array of arguments to this command
     * @return The result of the operation
     */
    @Override
    default Variable operation(Repl repl, Environment env, Expression... exprs) {
        Environment subEnv = new EnvironmentImpl(env);
        subEnv.filterTurtles(turtle -> filter(repl, env, turtle, exprs[0]));
        return PredefinedCommandList.$DEFAULT_OPERATION$.eval(repl, subEnv, Arrays.copyOfRange(exprs, 1, exprs.length));
    }

    @Override
    default int minimumArity() {
        return 2;
    }
}
