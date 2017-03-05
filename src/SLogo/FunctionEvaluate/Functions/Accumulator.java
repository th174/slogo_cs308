package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;

import java.util.Arrays;

/**
 * Created by th174 on 2/16/2017.
 */
@FunctionalInterface
public interface Accumulator extends Invokable {
    Variable accumulate(Variable var1, Variable var2);

    @Override
    default int minimumArity() {
        return 2;
    }

    @Override
    default Variable eval(Environment env, Expression... expr) throws Expression.EvaluationTargetException {
        if (expr.length == 0) {
            return Variable.FALSE;
        }
        Expression total = expr[expr.length - 1];
        if (expr.length == 1) {
            return total.eval(env);
        } else {
            return accumulate(invoke(env, Arrays.copyOfRange(expr, 0, expr.length - 1)), total.eval(env));
        }
    }

    @Override
    default Variable invoke(Environment env, Expression... expr) throws Expression.EvaluationTargetException {
        if (expr.length < minimumArity() - 1) {
            throw new UnexpectedArgumentException(minimumArity(), expr.length);
        }
        return eval(env, expr);
    }
}
