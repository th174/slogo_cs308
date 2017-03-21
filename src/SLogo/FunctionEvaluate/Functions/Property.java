package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Repl;

/**
 * This interface allows for commands that query information on current session settings
 *
 * @author Created by th174 on 3/2/2017.
 */
@FunctionalInterface
public interface Property extends Invokable {

    /**
     * @param repl Current REPL session
     * @return Current setting value
     */
    Object operation(Repl repl);

    @Override
    default int minimumArity() {
        return 0;
    }

    @Override
    default Variable eval(Repl repl, Environment env, Expression... exprs) {
        return Variable.newInstance(operation(repl));
    }
}