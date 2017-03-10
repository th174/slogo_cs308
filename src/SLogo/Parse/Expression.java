package SLogo.Parse;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Repl;

import java.util.List;

/**
 * Created by th174 on 2/21/2017.
 */
public interface Expression {

    Variable eval(Repl repl, Environment env) throws EvaluationTargetException;

    Invokable getCommand(Environment env);

    List<Expression> getBody();

    int size();

    class EvaluationTargetException extends RuntimeException {
        public EvaluationTargetException(Exception e) {
            super("Error in function evaluation!", e);
        }
    }
}
