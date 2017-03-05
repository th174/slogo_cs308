package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;

import java.util.Arrays;

/**
 * Created by th174 on 3/1/2017.
 */
@FunctionalInterface
public interface ShortCircuit extends Invokable {
    Variable test(Variable var1, Variable var2);

    @Override
    default Variable eval(Environment env, Expression... expr) throws Expression.EvaluationTargetException {
        if (expr.length == 0) {
            return Variable.FALSE;
        }
        Expression total = expr[expr.length - 1];
        if (expr.length == 1) {
            return total.eval(env);
        } else {
            Variable eval = invoke(env, Arrays.copyOfRange(expr, 0, expr.length - 1));
            return (eval.toBoolean() == test(Variable.TRUE, Variable.FALSE).toBoolean()) ? eval :
                    test(eval, total.eval(env));
        }
    }

    default int minimumArity() {
        return 2;
    }
}
