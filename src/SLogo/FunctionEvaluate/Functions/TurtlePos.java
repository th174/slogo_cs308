package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Turtles.Turtle;
import SLogo.View.CanvasView;

/**
 * Created by th174 on 2/28/2017.
 */
@FunctionalInterface
public interface TurtlePos extends Invokable {
    int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    NumberVariable operation(Turtle turtle, CanvasView canvas, Variable var1, Variable var2);

    @Override
    default Variable invoke(Environment env, Expression... expr) throws Expression.EvaluationTargetException {
        if (expr.length != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new UnexpectedArgumentException(EXPECTED_NUMBER_OF_ARGUMENTS, expr.length);
        } else {
            return operation(env.getTurtle(), env.getCanvas(), expr[0].eval(env), expr[1].eval(env));
        }
    }
}
