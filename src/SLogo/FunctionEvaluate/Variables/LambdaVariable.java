package SLogo.FunctionEvaluate.Variables;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.EnvironmentImpl;
import SLogo.FunctionEvaluate.Functions.CommandList;
import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.Parse.Expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by th174 on 2/28/2017.
 */
public final class LambdaVariable extends Variable implements Invokable {
    private final List<String> params;
    private final Expression[] body;

    public LambdaVariable(Expression... exprs) {
        this(exprs[0], Arrays.copyOfRange(exprs, 1, exprs.length));
    }

    public LambdaVariable(Expression params, Expression... body) {
        super(null);
        this.params = new ArrayList<>();
        for (Expression expr : params.getBody()) {
            if (expr.toString().matches(Variable.REGEX.getString("Variable"))) {
                this.params.add(expr.toString());
            } else {
                throw new RuntimeException(expr.toString());
            }
        }
        this.body = body;
    }

    @Override
    public Variable eval(Environment env, Expression... expr) throws Expression.EvaluationTargetException {
        return CommandList.LIST.invoke(new EnvironmentImpl(env, params, expr), body);
    }

    @Override
    public int minimumArity() {
        return params.size();
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
        throw new NotANumberException(this.toContentString());
    }

    @Override
    public String toContentString() {
        return "#<LAMBDA>";
    }

    @Override
    public String toString() {
        return "\n(λ " + params.toString().replace("[", "(").replace("]", ")").replace(",", "") + "\n\t" + Arrays.stream(body).map(Expression::toString).collect(Collectors.joining("\n\t")) + ")\n";
    }
}
