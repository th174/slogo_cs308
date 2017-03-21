package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Repl;

/**
 * This interface allows for commands that change current session settings
 *
 * @author Created by th174 on 3/4/2017.
 */
@FunctionalInterface
public interface Setting extends IterableInvokable {
    /**
     * @param repl Current REPL session
     * @param var1 New value of setting
     * @return New value of setting
     * @throws Exception Thrown when operation throws Exception
     */
    Object operation(Repl repl, Variable var1) throws Exception;

    @Override
    default int minimumArity() {
        return 1;
    }

    @Override
    default Variable operation(Repl repl, Environment env, Expression... exprs) throws Exception {
        return Variable.newInstance(operation(repl, exprs[0].eval(repl, env)));
    }
}
