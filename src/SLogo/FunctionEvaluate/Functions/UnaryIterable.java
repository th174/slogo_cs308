package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;

/**
 * Created by th174 on 2/16/2017.
 */
@FunctionalInterface
public interface UnaryIterable extends IterableInvokable {

    Variable operation(Variable var);

    @Override
    default int minimumArity() {
        return 1;
    }

    @Override
    default Variable operation(Environment env, Expression... vargs) throws Expression.EvaluationTargetException {
        return operation(vargs[0].eval(env));
    }
}
