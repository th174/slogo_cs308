package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Variables.BoolVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.RecursiveExpression;

/**
 * Created by th174 on 2/16/2017.
 */
@FunctionalInterface
public interface BooleanTest extends Invokable {
    int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    @Override
    default Variable invoke(String[] flags, Variable[] args, RecursiveExpression[] expr, RecursiveExpression[] alt) {
        if (args.length != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new UnexpectedArgumentException(EXPECTED_NUMBER_OF_ARGUMENTS, args.length);
        } else {
            return operation(args[0], args[1]);
        }
    }

    BoolVariable operation(Variable var1, Variable var2);
}
