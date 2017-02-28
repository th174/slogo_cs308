package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;

/**
 * Created by th174 on 2/26/2017.
 */
public class IfVariable implements Invokable {
    public static int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    @Override
    public Variable invoke(Environment env, Expression... expr) throws Expression.EvaluationTargetException {
        if (expr.length != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new UnexpectedArgumentException(EXPECTED_NUMBER_OF_ARGUMENTS, expr.length);
        }
        if (expr[0].eval(env).toBoolean()) {
            return expr[1].eval(env);
        } else {
            return new NumberVariable(0);
        }
    }
}