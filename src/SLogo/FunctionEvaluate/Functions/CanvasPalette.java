package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.View.CanvasView;

/**
 * Created by th174 on 3/4/2017.
 */
public interface CanvasPalette extends IterableInvokable {
    Object operation(CanvasView canvas, double var1, double var2, double var3, double var4);

    @Override
    default int minimumArity() {
        return 4;
    }

    @Override
    default Variable operation(Environment env, Expression... vargs)  {
        return Variable.newInstance(operation(env.getCanvas(), vargs[0].eval(env).toNumber(), vargs[1].eval(env).toNumber(), vargs[2].eval(env).toNumber(), vargs[3].eval(env).toNumber()));
    }
}
