package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Repl;

import java.util.Arrays;

/**
 * This interface allows for short circuiting boolean logical operators
 *
 * @author Created by th174 on 3/1/2017.
 */
@FunctionalInterface
public interface ShortCircuit extends Invokable {

    /**
     * A boolean test such as AND or OR
     *
     * @param var1 The first argument
     * @param var2 The second argument
     * @return Either var1 or var2, depending on the test and their values
     */
    Variable test(Variable var1, Variable var2);

    /**
     * Applies the boolean operation recursively to each expression until it short circuits and terminates
     *
     * @param repl  Current REPL session
     * @param env   Current dynamic runtime environment
     * @param exprs Array of arguments to this command
     * @return The first result of the first expression that causes the test to terminate
     */
    @Override
    default Variable eval(Repl repl, Environment env, Expression... exprs) {
        if (exprs.length == 0) {
            return Variable.FALSE;
        }
        Expression total = exprs[exprs.length - 1];
        if (exprs.length == 1) {
            return total.eval(repl, env);
        } else {
            Variable eval = eval(repl, env, Arrays.copyOfRange(exprs, 0, exprs.length - 1));
            return (eval.booleanContext() == test(Variable.TRUE, Variable.FALSE).booleanContext()) ? eval :
                    test(eval, total.eval(repl, env));
        }
    }

    @Override
    default int minimumArity() {
        return 2;
    }
}
