package SLogo.FunctionEvaluate.Variables;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.EnvironmentImpl;
import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.Parse.Expression;
import SLogo.Parse.LispSyntaxParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created by th174 on 2/28/2017.
 */
public final class LambdaVariable extends Variable implements Invokable {
    private final List<String> params;
    private final Expression[] body;

    public LambdaVariable(Expression params, Expression... body) {
        super(null);
        ResourceBundle regex = ResourceBundle.getBundle(LispSyntaxParser.RESOURCES_LOCATION + LispSyntaxParser.REGEX);
        this.params = new ArrayList<>();
        for (Expression expr : params.getBody()) {
            if (expr.toString().matches(regex.getString("Variable"))) {
                this.params.add(expr.toString());
            } else {
                throw new RuntimeException(expr.toString());
            }
        }
        this.body = body;
    }

    @Override
    public Variable invoke(Environment env, Expression... expr) throws Expression.EvaluationTargetException {
        return invokeBody(new EnvironmentImpl(env, params, expr), body);
    }

    private Variable invokeBody(Environment env, Expression... expr) throws Expression.EvaluationTargetException {
        if (expr.length == 1) {
            return expr[0].eval(env);
        } else {
            expr[0].eval(env);
            return invokeBody(env, Arrays.copyOfRange(expr, 1, expr.length));
        }
    }

    @Override
    public boolean equals(Variable o) {
        return false;
    }

    @Override
    public boolean toBoolean() {
        return true;
    }

    @Override
    public double toNumber() throws NotANumberException {
        throw new NotANumberException(this.toString());
    }

    @Override
    public String toString() {
        return "(λ " + params.toString().replace("[","(").replace("]",")").replace(","," . ") + " |\n\t" + Arrays.stream(body).map(Expression::toString).collect(Collectors.joining("\t\n")) + ")";
    }
}
