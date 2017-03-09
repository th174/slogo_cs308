package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Parse.PolishParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by th174 on 2/16/2017.
 */
public interface Invokable {
    PolishParser parser = new PolishParser();

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

    default List<Expression> readArgs(int numArgs, Environment env, Deque tokens) {
        if (numArgs == 0) {
            return new ArrayList<>(0);
        } else if (numArgs == 1) {
            return Collections.singletonList(parser.readTokens(env, tokens));
        } else {
            return Stream.concat(readArgs(numArgs - 1, env, tokens).stream(), Stream.of(parser.readTokens(env, tokens))).collect(Collectors.toList());
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
