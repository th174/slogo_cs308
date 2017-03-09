package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Turtles.Turtle;

/**
 * Created by th174 on 2/28/2017.
 */
@FunctionalInterface
public interface TurtleProperties extends Invokable, TurtleIterable {

    Object operation(Turtle turtle);

    @Override
    default int minimumArity() {
        return 0;
    }

    @Override
    default Variable eval(Environment env, Expression... expr) throws Expression.EvaluationTargetException {
        return forEachTurtle(env.getTurtles(), env, expr);
    }

    @Override
    default Object doTurtle(Turtle turtle, Environment env, Expression... vargs) throws Expression.EvaluationTargetException {
        return operation(turtle);
    }
}
