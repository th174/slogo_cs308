package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.Parse.AtomicList;
import SLogo.Parse.Expression;
import SLogo.Parse.Parser;

import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * This interface provides a extends Invokable to provide a template for function definitions
 *
 * @author Created by th174 on 3/4/2017.
 * @see Invokable
 */
@FunctionalInterface
public interface Define extends Invokable {

    @Override
    default int minimumArity() {
        return 3;
    }

    /**
     * @param numArgs Number of arguments to parse
     * @param env     Current environment
     * @param tokens  Deque of tokenized inputs to parse
     * @param parser  Current parser associated with
     * @return {@inheritDoc}
     */
    @Override
    default List<Expression> readArgs(int numArgs, Environment env, Deque<String> tokens, Parser parser) {
        if (numArgs == 2) {
            Expression[] exprs = new Expression[]{new AtomicList(tokens.removeFirst()), parser.readTokens(env, tokens)};
            env.addUserFunction(exprs[0].toString(), new UserFunction(exprs[1], new Expression[0]));
            return Arrays.asList(exprs);
        } else {
            return Invokable.super.readArgs(numArgs, env, tokens, parser);
        }
    }
}
