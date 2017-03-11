package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Parse.Parser;
import SLogo.Repl;

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

    /**
     * @param env Current runtime environment
     * @return Result of the command as a variable
     */
    Variable eval(Repl repl, Environment env, Expression... expr);

    /**
     * Invokes the method on its arguments in dynamic scope
     *
     * @param env  Current runtime environment
     * @param expr Array of arguments
     * @return Result of the command as a variable
     */
    default Variable invoke(Repl repl, Environment env, Expression... expr) {
        if (expr.length < minimumArity()) {
            throw new UnexpectedArgumentException(minimumArity(), expr.length);
        }
        return eval(repl, env, expr);
    }

    /**
     * Parses arguments for this Invokable
     *
     * @param numArgs Number of arguments to parse
     * @param env     Current environment
     * @param tokens  Deque of tokenized inputs to parse
     * @return List of arguments for this invokable
     */
    default List<Expression> readArgs(int numArgs, Environment env, Deque tokens, Parser parser) {
        if (numArgs == 0) {
            return new ArrayList<>(0);
        } else if (numArgs == 1) {
            return Collections.singletonList(parser.readTokens(env, tokens));
        } else {
            try {
                return Stream.concat(readArgs(numArgs - 1, env, tokens, parser).stream(), Stream.of(parser.readTokens(env, tokens))).collect(Collectors.toList());
            } catch (Parser.SyntaxException e) {
                throw new UnexpectedArgumentException(e.getMessage());
            }
        }
    }


    /**
     * @return Minimum number of arguments for this
     */
    int minimumArity();

    /**
     * Is thrown when this function is invoked on either an unexpected number of arguments, or an argument for which it has undefined behavior
     */
    class UnexpectedArgumentException extends RuntimeException {
        protected UnexpectedArgumentException(int expected, int actual) {
            super("Unexpected Number of arguments: Expected: " + expected + " Received: " + actual);
        }

        protected UnexpectedArgumentException(String s) {
            super("Unexpected argument: " + s);
        }
    }
}
