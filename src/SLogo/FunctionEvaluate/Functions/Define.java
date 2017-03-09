package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    default Variable invoke(Environment env, Expression... expr) throws Expression.EvaluationTargetException {
        if (expr.length < minimumArity()) {
            throw new UnexpectedArgumentException(minimumArity(), expr.length);
        }
        return eval(env, expr);
    }

    @Override
    default List<Expression> readArgs(int numArgs, Environment env, Deque tokens) {
        if (numArgs < 2) {
            return new ArrayList<>(0);
        } else if (numArgs == 2) {
            Expression[] exprs = new Expression[]{parser.readTokens(env, tokens), parser.readTokens(env, tokens)};
            env.addUserFunction(exprs[0].toString(), new UserFunction(exprs[1], null));
            return Arrays.asList(exprs);
        } else {
            return Stream.concat(readArgs(numArgs - 1, env, tokens).stream(), Stream.of(parser.readTokens(env, tokens))).collect(Collectors.toList());
        }
    }
}
