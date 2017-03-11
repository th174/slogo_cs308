package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.EnvironmentImpl;
import SLogo.FunctionEvaluate.PredefinedCommandList;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Repl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by th174 on 2/28/2017.
 */
public final class UserFunction implements Invokable {
    private final List<String> params;
    private final Expression[] body;

    public UserFunction(Expression... exprs) {
        this(exprs[0], Arrays.copyOfRange(exprs, 1, exprs.length));
    }

    public UserFunction(Expression params, Expression... body) {
        this.params = params.getBody().stream().map(Expression::toString).collect(Collectors.toList());
        this.body = body;
    }

    @Override
    public Variable eval(Repl repl, Environment env, Expression... expr) {
        return PredefinedCommandList.$DEFAULT_OPERATION$.eval(repl, new EnvironmentImpl(env, params, repl, expr), body);
    }

    @Override
    public int minimumArity() {
        return params.size();
    }

    @Override
    public String toString() {
        return params.toString().replace("[", "(").replace("]", ")").replace(",", "") + "\n\t" + Arrays.stream(body).map(Expression::toString).collect(Collectors.joining("\n\t")) + ")\n";
    }
}
