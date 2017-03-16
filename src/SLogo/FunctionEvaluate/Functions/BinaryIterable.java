package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Repl;

/**
 * This interface extends an IterableInvokable to specifically have 2 minimum arguments. The operation is performed on each pair of arguments.
 *
 * @author Created by th174 on 2/16/2017.
 * @see IterableInvokable
 */
@FunctionalInterface
public interface BinaryIterable extends IterableInvokable {
    /**
     * @param var1 First variable of bifunction
     * @param var2 Second variable of bifunction
     * @return The result of the operation
     * @throws Exception if operation throws exception
     */
    Variable operation(Variable var1, Variable var2) throws Exception;

    @Override
    default int minimumArity() {
        return 2;
    }

    @Override
    default Variable operation(Repl repl, Environment env, Expression... exprs) throws Exception {
        return operation(exprs[0].eval(repl, env), exprs[1].eval(repl, env));
    }
}
