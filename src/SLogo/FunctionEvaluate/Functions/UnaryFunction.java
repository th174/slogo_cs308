package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;

/**
 * Created by th174 on 2/16/2017.
 */
public interface UnaryFunction extends IterableInvokable {

    Variable operation(Variable var);

    @Override
    default int expectedArity() {
        return 1;
    }

    @Override
    default Variable operation(Environment env, Expression... vargs) throws Expression.EvaluationTargetException {
        return operation(vargs[0].eval(env));
    }
}
