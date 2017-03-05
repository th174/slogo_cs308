package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.View.CanvasView;

/**
 * Created by th174 on 3/4/2017.
 */
@FunctionalInterface
public interface CanvasSetting extends IterableInvokable {
    Object operation(CanvasView canvas, double var1);

    @Override
    default int minimumArity() {
        return 1;
    }

    @Override
    default Variable operation(Environment env, Expression... vargs) throws Expression.EvaluationTargetException {
        return Variable.newInstance(operation(env.getCanvas(), vargs[0].eval(env).toNumber()));
    }
}
