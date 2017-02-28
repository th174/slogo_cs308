package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.BoolVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;

/**
 * Created by th174 on 2/28/2017.
 */
public class DefineFunction implements Invokable{
    public static int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    @Override
    public Variable invoke(Environment env, Expression... expr) {
        if (expr.length != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new UnexpectedArgumentException(EXPECTED_NUMBER_OF_ARGUMENTS, expr.length);
        }
        env.addUserFunction(expr[0].toString(), new Procedure(expr[1], expr[2]));
        return BoolVariable.TRUE;
    }
}
