package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.BoolVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;

/**
 * Created by th174 on 2/16/2017.
 */
@FunctionalInterface
public interface BooleanTest extends Invokable {
    int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    @Override
    default Variable invoke(Environment env, Expression... expr) throws Expression.EvaluationTargetException {
        if (expr.length != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new UnexpectedArgumentException(EXPECTED_NUMBER_OF_ARGUMENTS, expr.length);
        } else {
            return operation(expr[0].eval(env), expr[1].eval(env));
        }
    }

    BoolVariable operation(Variable var1, Variable var2);
}
