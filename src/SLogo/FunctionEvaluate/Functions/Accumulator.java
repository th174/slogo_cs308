package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;

import java.util.Arrays;

/**
 * This interface wraps operations that work by accumulating value over a number of arguments.
 * This recursively applies Accumulator::accumulate over each of its arguments.
 *
 * @Author Created by th174 on 2/16/2017.
 */
@FunctionalInterface
public interface Accumulator extends Invokable {
    Variable accumulate(Variable var1, Variable var2);

    @Override
    default int minimumArity() {
        return 2;
    }

    @Override
    default Variable eval(Environment env, Expression... expr) {
        if (expr.length == 0) {
            return Variable.FALSE;
        }
        Expression total = expr[expr.length - 1];
        if (expr.length == 1) {
            return total.eval(env);
        } else {
            try {
                return accumulate(eval(env, Arrays.copyOfRange(expr, 0, expr.length - 1)), total.eval(env));
            } catch (Exception e) {
                throw new Expression.EvaluationTargetException(e);
            }
        }
    }

    @Override
    default Variable invoke(Environment env, Expression... expr) {
        if (expr.length < 1) {
            throw new UnexpectedArgumentException(minimumArity(), expr.length);
        }
        return eval(env, expr);
    }
}
