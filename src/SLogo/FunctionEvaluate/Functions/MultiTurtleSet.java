package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.EnvironmentImpl;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Turtles.Turtle;

import java.util.Arrays;

/**
 * Created by th174 on 3/2/2017.
 */
@FunctionalInterface
public interface MultiTurtleSet extends IterableInvokable {

    Variable filter(Environment env, Turtle turtle, Expression expr) throws Expression.EvaluationTargetException;

    @Override
    default Variable operation(Environment env, Expression... expr) throws Expression.EvaluationTargetException {
        return CommandList.$DEFAULT_OPERATION$.invoke(new EnvironmentImpl(env, turtle -> filter(env, turtle, expr[0]).toBoolean()), Arrays.copyOfRange(expr, 1, expr.length));
    }

    @Override
    default int minimumArity() {
        return 1;
    }
}
