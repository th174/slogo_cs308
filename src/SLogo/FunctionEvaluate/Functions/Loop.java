package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.PredefinedCommandList;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Repl;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by th174 on 3/4/2017.
 */
@FunctionalInterface
public interface Loop extends Invokable {

    Object[] getLoopParams(Repl repl, Environment env, List<Expression> loopParams);

    @Override
    default int minimumArity() {
        return 2;
    }

    @Override
    default Variable eval(Repl repl, Environment env, Expression... expr) {
        Object[] loopParams = getLoopParams(repl, env, expr[0].getBody());
        String loopVar = (String) loopParams[0];
        Variable loopStart = (Variable) loopParams[1];
        Variable loopEnd = (Variable) loopParams[2];
        Expression onChange = (Expression) loopParams[3];
        env.addUserVariable(loopVar,loopStart);
        return operation(repl, env, Variable.FALSE, loopVar, (var) -> env.getVariableByName(var).greaterThan(loopEnd).equals(Variable.FALSE), onChange, Arrays.copyOfRange(expr, 1, expr.length));
    }

    default Variable operation(Repl repl, Environment env, Variable last, String loopVar, Predicate<String> condition, Expression onChange, Expression... expr) {
        if (!condition.test(loopVar)) {
            return last;
        } else {
            last = PredefinedCommandList.$DEFAULT_OPERATION$.eval(repl, env, expr);
            env.addUserVariable(loopVar, env.getVariableByName(loopVar).sum(onChange.eval(repl, env)));
            return operation(repl, env, last, loopVar, condition, onChange, expr);
        }
    }
}
