package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Repl;

/**
 * This interface extends an IterableInvokable to specifically have 1 minimum argument. The operation is performed on each argument.
 *
 * @author Created by th174 on 2/16/2017.
 * @see IterableInvokable
 */
@FunctionalInterface
public interface UnaryIterable extends IterableInvokable {

    /**
     * Unary operation
     *
     * @param var Argument
     * @return Result of operation
     */
    Variable operation(Variable var);

    @Override
    default int minimumArity() {
        return 1;
    }

    @Override
    default Variable operation(Repl repl, Environment env, Expression... exprs) {
        return operation(exprs[0].eval(repl, env));
    }
}
