package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Repl;
import SLogo.View.CanvasView;

/**
 * Created by th174 on 3/2/2017.
 */
@FunctionalInterface
public interface CanvasProperty extends Invokable {
    Object operation(CanvasView canvas);

    @Override
    default int minimumArity() {
        return 0;
    }

    @Override
    default Variable eval(Repl repl, Environment env, Expression... expr) {
        return Variable.newInstance(operation(repl.getCanvas()));
    }
}