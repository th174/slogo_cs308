package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.PredefinedCommandList;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Repl;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * This interface provides a base implementation for loop control commands
 *
 * @author Created by th174 on 3/4/2017.
 */
@FunctionalInterface
public interface Loop extends Invokable {
    /**
     * Determines {Variable name, initial value, bound, expression to be evaluated each iteration} from the first argument of this command
     *
     * @param repl       Current REPL session
     * @param env        Current environment
     * @param loopParams The loop parameters as an list expression, containing 1-4 elements
     * @return An object array containing {Variable name, initial value, bound, expression to be evaluated and used to increment loop}
     */
    Object[] getLoopParams(Repl repl, Environment env, Expression loopParams);

    @Override
    default int minimumArity() {
        return 2;
    }

    /**
     * Sets up initial parameters for the loop, and starts it
     * @param repl Current REPL session
     * @param env Current dynamic runtime environment
     * @param exprs Array of arguments to this command
     * @return Result of final iteration of the loop as a List
     */
    @Override
    default Variable eval(Repl repl, Environment env, Expression... exprs) {
        Object[] loopParams = getLoopParams(repl, env, exprs[0]);
        String loopVar = (String) loopParams[0];
        Variable loopStart = (Variable) loopParams[1];
        Variable loopEnd = (Variable) loopParams[2];
        Expression onChange = (Expression) loopParams[3];
        env.addUserVariable(loopVar, loopStart);
        Predicate<Variable> condition = variable -> variable.lessThan(loopStart).not().and(variable.lessThan(loopEnd)).or(variable.greaterThan(loopStart).not().and(variable.greaterThan(loopEnd))).booleanContext();
        return operation(repl, env, Variable.FALSE, loopVar, condition, onChange, Arrays.copyOfRange(exprs, 1, exprs.length));
    }

    /**
     * Loops while conditions are met
     *
     * @param repl      Current REPL session
     * @param env       Current Environment
     * @param last      Value of previous iteration of the loop
     * @param loopVar   Name of loop variable
     * @param condition Condition to be checked on each iteration of the loop
     * @param onChange  Expression to be evaluated on each iteration of the loop, and used to increment loop variable
     * @param exprs     Expressions to be evaluated on each iteration of the loop
     * @return Result of final iteration of the loop as a List
     */
    default Variable operation(Repl repl, Environment env, Variable last, String loopVar, Predicate<Variable> condition, Expression onChange, Expression... exprs) {
        if (!condition.test(env.getVariableByName(loopVar))) {
            return last;
        } else {
            last = PredefinedCommandList.$DEFAULT_OPERATION$.eval(repl, env, exprs);
            env.addUserVariable(loopVar, env.getVariableByName(loopVar).sum(onChange.eval(repl, env)));
            return operation(repl, env, last, loopVar, condition, onChange, exprs);
        }
    }
}
