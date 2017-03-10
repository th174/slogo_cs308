package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Repl;
import SLogo.Turtles.Turtle;

import java.util.List;

import static SLogo.FunctionEvaluate.Variables.Variable.FALSE;

/**
 * Created by th174 on 3/2/2017.
 */
@FunctionalInterface
public interface TurtleIterable {
    Object doTurtle(Repl repl, Turtle turtle, Environment env, Expression... vargs);

    default Variable forEachTurtle(Repl repl, List<Turtle> turtleList, Environment env, Expression... vargs) {
        if (turtleList.isEmpty()) {
            return FALSE;
        } else if (turtleList.size() == 1) {
            return Variable.newInstance(doTurtle(repl, turtleList.get(0), env, vargs));
        } else {
            Variable.newInstance(doTurtle(repl, turtleList.get(0), env, vargs));
            return forEachTurtle(repl, turtleList.subList(1, turtleList.size()), env, vargs);
        }
    }
}
