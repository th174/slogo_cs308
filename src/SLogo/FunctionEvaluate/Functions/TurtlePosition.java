package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Turtles.NewTurtle;

/**
 * Created by th174 on 2/28/2017.
 */
@FunctionalInterface
public interface TurtlePosition extends IterableInvokable, TurtleIterable {

    Number operation(NewTurtle turtle, Variable var1, Variable var2);

    @Override
    default int expectedArity() {
        return 2;
    }

    @Override
    default Variable operation(Environment env, Expression... vargs) throws Expression.EvaluationTargetException {
        return forEachTurtle(env.getTurtles(), env, vargs);
    }

    @Override
    default Object doTurtle(NewTurtle turtle, Environment env, Expression... vargs) throws Expression.EvaluationTargetException {
        return operation(turtle, vargs[0].eval(env), vargs[1].eval(env));
    }
}
