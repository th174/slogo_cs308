package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Repl;

import java.util.Arrays;

/**
 * Created by th174 on 3/2/2017.
 */
public interface IterableInvokable extends Invokable {

    @Override
    default Variable eval(Repl repl, Environment env, Expression... expr) {
        try {
            if (expr.length == 0 || expr.length % minimumArity() != 0) {
                throw new UnexpectedArgumentException(minimumArity(), expr.length);
            } else if (expr.length == minimumArity()) {
                return operation(repl, env, expr);
            } else {
                operation(repl, env, expr);
                return eval(repl, env, Arrays.copyOfRange(expr, minimumArity(), expr.length));
            }
        } catch (Expression.EvaluationTargetException e) {
            throw e;
        } catch (Exception e) {
            throw new Expression.EvaluationTargetException(e);
        }
    }

    int minimumArity();

    Variable operation(Repl repl, Environment env, Expression... vargs) throws Exception;
}
