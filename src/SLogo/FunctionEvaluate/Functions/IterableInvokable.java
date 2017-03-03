package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;

import java.util.Arrays;

/**
 * Created by th174 on 3/2/2017.
 */
public interface IterableInvokable extends Invokable {

    @Override
    default Variable invoke(Environment env, Expression... expr) throws Expression.EvaluationTargetException {
        if (expr.length == 0 || expr.length % expectedArity() != 0) {
            throw new UnexpectedArgumentException(expectedArity(), expr.length);
        } else if (expr.length == expectedArity()) {
            return operation(env, expr);
        } else {
            operation(env, expr);
            return invoke(env, Arrays.copyOfRange(expr, expectedArity(), expr.length));
        }
    }

    int expectedArity();

    Variable operation(Environment env, Expression... vargs) throws Expression.EvaluationTargetException;
}