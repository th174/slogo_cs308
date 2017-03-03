package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.ListVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;

/**
 * Created by th174 on 3/2/2017.
 */
public interface MultiTurtleSet extends Invokable {

    Variable operation(Environment env, ListVariable listVariable, Expression... expr) throws Expression.EvaluationTargetException;

    @Override
    default Variable invoke(Environment env, Expression... expr) throws Expression.EvaluationTargetException {
        Variable list = expr[0].eval(env);
        if (list instanceof ListVariable) {
            return operation(env, (ListVariable) list,expr);
        } else {
            throw new Invokable.UnexpectedArgumentException(expr[0].toString());
        }
    }
}
