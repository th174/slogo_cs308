package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.SLogoParser;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by th174 on 2/16/2017.
 */
public interface Invokable {
    ResourceBundle REGEX = ResourceBundle.getBundle("resources.languages/Syntax");
    SLogoParser parser = new SLogoParser();

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

    default Variable invoke(Environment env, Deque tokens) throws Expression.EvaluationTargetException {
        List<Expression> exprs = readArgs(minimumArity(),env,tokens);
        System.out.println("\n--Function Invoked--\nArgs:");
        System.out.println(exprs);
        return invoke(env, exprs.toArray(new Expression[0]));
    }

    static List<Expression> readArgs(int numArgs, Environment env, Deque tokens) {
        if (numArgs == 0) {
            return new ArrayList<>(0);
        } else if (numArgs == 1) {
            return new ArrayList<>(Collections.singletonList(parser.readTokens(env, tokens)));
        } else {
            List<Expression> temp = readArgs(numArgs - 1, env, tokens);
            temp.add(parser.readTokens(env, tokens));
            return temp;
        }
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
