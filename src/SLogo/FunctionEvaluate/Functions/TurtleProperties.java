package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Turtles.NewTurtle;
import SLogo.Turtles.Turtle;
import SLogo.View.CanvasView;

/**
 * Created by th174 on 2/28/2017.
 */
@FunctionalInterface
public interface TurtleProperties extends Invokable {
    Object operation(NewTurtle turtle);

    @Override
    default Variable invoke(Environment env, Expression... expr) throws Expression.EvaluationTargetException{
        return Variable.newInstance(operation(env.getTurtle()));
    }
}
