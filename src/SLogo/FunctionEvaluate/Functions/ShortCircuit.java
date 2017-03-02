package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.BoolVariable;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;

import java.util.Arrays;

/**
 * Created by th174 on 3/1/2017.
 */
public interface ShortCircuit extends Invokable {
    @Override
    default Variable invoke(Environment env, Expression... expr) throws Expression.EvaluationTargetException {
        if (expr.length == 0) {
            return new NumberVariable(0);
        }
        Variable total = expr[0].eval(env);
        if (expr.length == 1) {
            return total;
        } else {
            return (total.toBoolean() == test(BoolVariable.TRUE, BoolVariable.FALSE).toBoolean()) ? total : test(total, invoke(env, Arrays.copyOfRange(expr, 1, expr.length)));
        }
    }

    Variable test(Variable var1, Variable var2);
}
