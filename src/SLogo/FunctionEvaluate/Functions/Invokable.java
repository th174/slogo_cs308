package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;

/**
 * Created by th174 on 2/16/2017.
 */
public interface Invokable {

    /**
     * @param env Current runtime environment
     * @return Result of the command as a variable
     */
    Variable eval(Environment env, Expression... expr);

    /**
     * @param env Current runtime environment
     * @return Result of the command as a variable
     */
    default Variable invoke(Environment env, Expression... expr) throws Expression.EvaluationTargetException {
        if (expr.length < minimumArity()) {
            throw new UnexpectedArgumentException(minimumArity(), expr.length);
        }
        return eval(env, expr);
    }

    /**
     * @return default number of arguments
     */
    int minimumArity();


    final class UnexpectedArgumentException extends RuntimeException {
        protected UnexpectedArgumentException(int expected, int actual) {
            super("Unexpected Number of arguments: Expected: " + expected + " Received: " + actual);
        }

        protected UnexpectedArgumentException(String s) {
            super("Unexpected argument: " + s);
        }
    }
}
