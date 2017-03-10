package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Repl;

/**
 * Created by th174 on 2/16/2017.
 */
@FunctionalInterface
public interface UnaryIterable extends IterableInvokable {

    Variable operation(Variable var);

    @Override
    default int minimumArity() {
        return 1;
    }

    @Override
    default Variable operation(Repl repl, Environment env, Expression... vargs) {
        return operation(vargs[0].eval(repl,env));
    }
}
