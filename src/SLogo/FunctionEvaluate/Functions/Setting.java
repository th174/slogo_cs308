package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Repl;
import SLogo.View.CanvasView;

import java.io.IOException;

/**
 * Created by th174 on 3/4/2017.
 */
@FunctionalInterface
public interface Setting extends IterableInvokable {
    Object operation(Repl repl, Variable var1) throws Exception;

    @Override
    default int minimumArity() {
        return 1;
    }

    @Override
    default Variable operation(Repl repl, Environment env, Expression... vargs) throws Exception {
        return Variable.newInstance(operation(repl, vargs[0].eval(repl,env)));
    }
}
