package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;

/**
 * Created by th174 on 2/16/2017.
 */
@FunctionalInterface
public interface Invokable {
    /**
     * @param env  Current runtime environment
     * @return Result of the command as a variable
     */
    Variable invoke(Environment env, Expression... expr) throws Expression.EvaluationTargetException;


    final class UnexpectedArgumentException extends RuntimeException {
        protected UnexpectedArgumentException(int expected, int actual) {
            super("Unexpected Number of arguments: Expected: " + expected + " Received: " + actual);
        }
    }


}
