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
 * @author Created by th174 on 2/16/2017.
 */
public interface Invokable {

    /**
     * @param repl  Current REPL session
     * @param env   Current dynamic runtime environment
     * @param exprs Array of arguments to this command
     * @return Result of evaluating this command
     */
    Variable eval(Repl repl, Environment env, Expression... exprs);

    /**
     * Invokes the method on its arguments in dynamic scope
     *
     * @param repl  Current REPL session
     * @param env   Current runtime environment
     * @param exprs Array of arguments
     * @return Result of the command as a variable
     */
    default Variable invoke(Repl repl, Environment env, Expression... exprs) {
        if (exprs.length < minimumArity()) {
            throw new UnexpectedArgumentException(minimumArity(), exprs.length);
        }
        return eval(repl, env, exprs);
    }

    /**
     * Parses arguments for this Invokable by converting a Deque of tokens into a List of Expressions
     *
     * @param numArgs Number of arguments to parse
     * @param env     Current environment
     * @param tokens  Deque of tokenized inputs to parse
     * @param parser  Parser to use when reading arguments
     * @return List of arguments for this invokable
     */
    default List<Expression> readArgs(int numArgs, Environment env, Deque<String> tokens, Parser parser) {
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
     * @return Minimum number of arguments in this Invokable
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
