package SLogo.Parse;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Repl;

import java.util.List;

/**
 * This interface provides an API for an Expression
 *
 * @author Created by th174 on 2/21/2017.
 */
public interface Expression {

    /**
     * Evaluates this SExpression
     *
     * @param repl Current REPL session
     * @param env  Current local environment
     * @return The result of evaluating this expression
     * @throws EvaluationTargetException Thrown if an exception is thrown in evaluation
     */
    Variable eval(Repl repl, Environment env) throws EvaluationTargetException;

    /**
     * @param env Current local environment
     * @return This Expression interpreted as a function if possible, else null
     */
    Invokable getCommand(Environment env);

    /**
     * @return All sub-Expression function arguments in this Expression
     */
    List<Expression> getBody();

    /**
     * Thrown if an exception is thrown in evaluation
     */
    class EvaluationTargetException extends RuntimeException {
        public EvaluationTargetException(Exception e) {
            super("Error in function evaluation!", e);
        }
    }
}
