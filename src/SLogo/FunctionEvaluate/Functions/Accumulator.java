package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Repl;

import java.util.Arrays;

/**
 * This interface wraps operations that work by accumulating value over a number of arguments.
 * This recursively applies Accumulator::accumulate over each of its arguments.
 * @see Invokable
 * @author Created by th174 on 2/16/2017.
 */
@FunctionalInterface
public interface Accumulator extends Invokable {
    /**
     * The accumulator operation to be applied successively to each argument of this command
     * @param var1 First parameter
     * @param var2 Second parameter
     * @return Result
     */
    Variable accumulate(Variable var1, Variable var2);

    @Override
    default int minimumArity() {
        return 2;
    }

    /**
     * Applies the accumulator recursively to each expression in expr.
     * @param repl Current REPL session
     * @param env Current dynamic runtime environment
     * @param exprs Array of arguments to this command
     * @return The result of the accumulation, or FALSE if no arguments
     */
    @Override
    default Variable eval(Repl repl, Environment env, Expression... exprs) {
        if (exprs.length == 0) {
            return Variable.FALSE;
        }
        Expression total = exprs[exprs.length - 1];
        if (exprs.length == 1) {
            return total.eval(repl, env);
        } else {
            try {
                return accumulate(eval(repl, env, Arrays.copyOfRange(exprs, 0, exprs.length - 1)), total.eval(repl, env));
            } catch (Expression.EvaluationTargetException e) {
                throw e;
            } catch (Exception e) {
                throw new Expression.EvaluationTargetException(e);
            }
        }
    }

    @Override
    default Variable invoke(Repl repl, Environment env, Expression... exprs) {
        if (exprs.length < 1) {
            throw new UnexpectedArgumentException(minimumArity(), exprs.length);
        }
        return eval(repl, env, exprs);
    }
}
