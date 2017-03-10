package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.AtomicList;
import SLogo.Parse.Expression;

import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * Created by th174 on 3/4/2017.
 */
@FunctionalInterface
public interface Define extends Invokable {
    @Override
    default int minimumArity() {
        return 3;
    }

    @Override
    default Variable invoke(Environment env, Expression... expr)  {
        if (expr.length < minimumArity()) {
            throw new UnexpectedArgumentException(minimumArity(), expr.length);
        }
        return eval(env, expr);
    }

    @Override
    default List<Expression> readArgs(int numArgs, Environment env, Deque tokens) {
        if (numArgs == 2) {
            Expression[] exprs = new Expression[]{new AtomicList(tokens.removeFirst().toString()), parser.readTokens(env, tokens)};
            env.addUserFunction(exprs[0].toString(), new UserFunction(exprs[1], null));
            return Arrays.asList(exprs);
        } else {
            return Invokable.super.readArgs(numArgs, env, tokens);
        }
    }
}
