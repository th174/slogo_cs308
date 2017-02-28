package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.EnvironmentImpl;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Parse.LispSyntaxParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by th174 on 2/28/2017.
 */
public final class Procedure implements Invokable {
    private final List<String> params;
    private final Expression body;

    public Procedure(Expression params, Expression body) {
        ResourceBundle regex = ResourceBundle.getBundle(LispSyntaxParser.RESOURCES_LOCATION + LispSyntaxParser.REGEX);
        this.params = new ArrayList<>();
        for (Expression expr : params.getBody()) {
            if (expr.toString().matches(regex.getString("Variable"))){
                this.params.add(expr.toString());
            } else {
                throw new RuntimeException(expr.toString());
            }
        }
        this.body = body;
    }

    @Override
    public Variable invoke(Environment env, Expression... expr) throws Expression.EvaluationTargetException {
        Environment local = new EnvironmentImpl(env, params, expr);
        return body.eval(local);
    }
}
