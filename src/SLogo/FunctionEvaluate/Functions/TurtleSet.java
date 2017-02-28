package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Turtles.Turtle;
import SLogo.View.CanvasView;

/**
 * Created by th174 on 2/28/2017.
 */
@FunctionalInterface
public interface TurtleSet extends Invokable {
    int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    Variable operation(Turtle t, CanvasView c);

    @Override
    default Variable invoke(Environment env, Expression... expr) {
        if (expr.length != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new UnexpectedArgumentException(EXPECTED_NUMBER_OF_ARGUMENTS, expr.length);
        } else {
            return operation(env.getTurtle(), env.getCanvas());
        }
    }
}
