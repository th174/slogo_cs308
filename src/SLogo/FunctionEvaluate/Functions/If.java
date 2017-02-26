package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.RecursiveExpression;

/**
 * Created by th174 on 2/26/2017.
 */
public class If implements Invokable {
    public static int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    @Override
    public Variable invoke(String[] flags, Variable[] args, RecursiveExpression[] expr, RecursiveExpression[] alt) {
        if (args.length != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new UnexpectedArgumentException(EXPECTED_NUMBER_OF_ARGUMENTS, args.length);
        }
        if (args[0].toBoolean()) {
            return args[1].scalar();
        } else {
            return new NumberVariable(0);
        }
    }
}