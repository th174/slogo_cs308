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
 * Created by th174 on 3/2/2017.
 */
@FunctionalInterface
public interface MultiTurtleSet extends IterableInvokable {

    Variable filter(Repl repl, Environment env, Turtle turtle, Expression expr);

    @Override
    default Variable operation(Repl repl, Environment env, Expression... expr) {
        return PredefinedCommandList.$DEFAULT_OPERATION$.eval(repl, new EnvironmentImpl(env, turtle -> filter(repl, env, turtle, expr[0]).toBoolean()), Arrays.copyOfRange(expr, 1, expr.length));
    }

    @Override
    default int minimumArity() {
        return 1;
    }
}
