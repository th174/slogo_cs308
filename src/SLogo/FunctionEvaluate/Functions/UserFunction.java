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
 * This class models a user-defined function
 *
 * @author Created by th174 on 2/28/2017.
 */
public final class UserFunction implements Invokable {
    private final List<String> params;
    private final Expression[] body;

    /**
     * Create user defined function from array of expressions
     *
     * @param exprs Array of expressions, with the first element being the function params
     */
    public UserFunction(Expression... exprs) {
        this(exprs[0], Arrays.copyOfRange(exprs, 1, exprs.length));
    }

    /**
     * @param params Function parameters
     * @param body   Expressions to be evaluated when this function is called
     */
    public UserFunction(Expression params, Expression... body) {
        this.params = params.getBody().stream().map(Expression::toString).collect(Collectors.toList());
        this.body = body;
    }

    /**
     * Evaluates this function in a new local environment
     *
     * @param repl  Current REPL session
     * @param env   Current dynamic runtime environment
     * @param exprs Array of arguments to this command
     * @return List of results after evaluating each Expression in the body of this UserFunction
     */
    @Override
    public Variable eval(Repl repl, Environment env, Expression... exprs) {
        Environment functionEnvrionment = new EnvironmentImpl(env);
        for (int i = 0; i < exprs.length; i++) {
            functionEnvrionment.addUserVariable(params.get(i), body[i].eval(repl, env));
        }
        return PredefinedCommandList.$DEFAULT_OPERATION$.eval(repl, functionEnvrionment, body);
    }

    @Override
    public int minimumArity() {
        return params.size();
    }

    @Override
    public String toString() {
        return "\u03bb " + params.toString().replace("[", "(").replace("]", ")").replace(",", "") + "\n\t" + Arrays.stream(body).map(Expression::toString).collect(Collectors.joining("\n\t")) + ")\n";
    }
}
