package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Turtles.Turtle;

import java.util.List;

import static SLogo.FunctionEvaluate.Variables.Variable.FALSE;

/**
 * Created by th174 on 3/2/2017.
 */
@FunctionalInterface
public interface TurtleIterable {
    Object doTurtle(Turtle turtle, Environment env, Expression... vargs) throws Expression.EvaluationTargetException;

    default Variable forEachTurtle(List<Turtle> turtleList, Environment env, Expression... vargs) throws Expression.EvaluationTargetException {
        if (turtleList.isEmpty()) {
            return FALSE;
        } else if (turtleList.size() == 1) {
            return Variable.newInstance(doTurtle(turtleList.get(0), env, vargs));
        } else {
            Variable.newInstance(doTurtle(turtleList.get(0), env, vargs));
            return forEachTurtle(turtleList.subList(1,turtleList.size()), env, vargs);
        }
    }
}
