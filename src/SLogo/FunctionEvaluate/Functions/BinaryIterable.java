package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Repl;

/**
 * Created by th174 on 2/16/2017.
 */
@FunctionalInterface
public interface BinaryIterable extends IterableInvokable {

    Variable operation(Variable var1, Variable var2);

    @Override
    default int minimumArity() {
        return 2;
    }

    @Override
    default Variable operation(Repl repl, Environment env, Expression... vargs) {
        return operation(vargs[0].eval(repl,env), vargs[1].eval(repl,env));
    }
}
