package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;

/**
 * Created by th174 on 2/28/2017.
 */
public class Loop implements Invokable {
    @Override
    public Variable invoke(Environment env, Expression... expr) throws Expression.EvaluationTargetException {
        double count = expr[0].eval(env).toNumber();
        Variable last = new NumberVariable(0);
        while (count-- > 0) {
            last = expr[1].eval(env);
            System.out.println(last);
        }
        return last.finalElement();
    }
}
