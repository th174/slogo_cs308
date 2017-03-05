package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;

/**
 * Created by th174 on 2/16/2017.
 */
@FunctionalInterface
public interface BooleanTest extends IterableInvokable {

    Variable operation(Variable var1, Variable var2);

    @Override
    default int minimumArity() {
        return 2;
    }

    @Override
    default Variable operation(Environment env, Expression... vargs) throws Expression.EvaluationTargetException {
        return operation(vargs[0].eval(env), vargs[1].eval(env));
    }
}
