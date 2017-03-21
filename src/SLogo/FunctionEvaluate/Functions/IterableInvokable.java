package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Repl;

import java.util.Arrays;

/**
 * This interface allows otherwise fixed argument operations to be applied to an arbitrary number arguments, by iteratively applying the operation to each set of arguments supplied
 *
 * @author Created by th174 on 3/2/2017.
 */
public interface IterableInvokable extends Invokable {

    /**
     * Iteratively evaluates this command over each subset of the arguments passed in. Returns the final value.
     *
     * @param repl  Current REPL session
     * @param env   Current dynamic runtime environment
     * @param exprs Array of arguments to this command
     * @return {@inheritDoc}
     */
    @Override
    default Variable eval(Repl repl, Environment env, Expression... exprs) {
        try {
            if (exprs.length == 0 || exprs.length % minimumArity() != 0) {
                throw new UnexpectedArgumentException(minimumArity(), exprs.length);
            } else if (exprs.length == minimumArity()) {
                return operation(repl, env, exprs);
            } else {
                operation(repl, env, exprs);
                return eval(repl, env, Arrays.copyOfRange(exprs, minimumArity(), exprs.length));
            }
        } catch (Expression.EvaluationTargetException e) {
            throw e;
        } catch (Exception e) {
            throw new Expression.EvaluationTargetException(e);
        }
    }

    @Override
    int minimumArity();

    /**
     * The base implementation of a single iteration of this command. The operation is applied to each set of arguments passed in.
     *
     * @param repl  Current REPL session
     * @param env   Current dynamic runtime environment
     * @param exprs Array of arguments to this command
     * @return The result of evaluating this function over a single set of inputs
     * @throws Exception if operation throws an exception
     */
    Variable operation(Repl repl, Environment env, Expression... exprs) throws Exception;
}
