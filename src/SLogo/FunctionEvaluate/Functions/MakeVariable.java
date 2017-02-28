package SLogo.FunctionEvaluate.Functions;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.RecursiveExpression;
/**
 * Created by th174 on 2/26/2017.
 */
public class MakeVariable implements Invokable {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    @Override
    public Variable invoke(String[] flags, Variable[] args, RecursiveExpression[] expr, RecursiveExpression[] alt) {
        if (args.length != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new UnexpectedArgumentException(EXPECTED_NUMBER_OF_ARGUMENTS, args.length);
        } else {
            return args[0];
        }
    }
}