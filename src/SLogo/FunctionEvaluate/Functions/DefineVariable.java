package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;

import java.util.Arrays;

/**
 * Created by th174 on 2/26/2017.
 */
public final class DefineVariable implements Invokable {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    @Override
    public Variable invoke(Environment env, Expression... expr) throws Expression.EvaluationTargetException {
        if (expr.length != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new UnexpectedArgumentException(EXPECTED_NUMBER_OF_ARGUMENTS, expr.length);
        } else {
            env.addUserVariable(expr[0].toString(), expr[1].eval(env));
            return expr[0].eval(env);
        }
    }
}