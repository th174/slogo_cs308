package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;

import java.util.Arrays;

/**
 * Created by th174 on 2/16/2017.
 */
@FunctionalInterface
public interface Accumulator extends Invokable {

    @Override
    default Variable invoke(Environment env, Expression... expr) throws Expression.EvaluationTargetException {
        if (expr.length == 0) {
            return new NumberVariable(0);
        }
        Variable total = expr[0].eval(env);
        if (expr.length == 1) {
            return total;
        } else {
            return accumulate(total, invoke(env, Arrays.copyOfRange(expr, 1, expr.length)));
        }
    }

    Variable accumulate(Variable var1, Variable var2);
}
