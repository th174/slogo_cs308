package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.View.CanvasView;

/**
 * Created by th174 on 3/2/2017.
 */
@FunctionalInterface
public interface CanvasProperty extends Invokable {
    Object operation(CanvasView canvas);

    @Override
    default Variable invoke(Environment env, Expression... expr) throws Expression.EvaluationTargetException {
        return Variable.newInstance(operation(env.getCanvas()));
    }
}