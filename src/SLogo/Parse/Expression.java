package SLogo.Parse;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.Variables.Variable;

import java.util.Deque;

/**
 * Created by th174 on 2/21/2017.
 */
public interface Expression {
    Variable eval(Environment env) throws EvaluationTargetException;

    Invokable getCommand(Environment env);

    Expression[] getBody();

    int size();

    class EvaluationTargetException extends Exception{
        EvaluationTargetException(Exception e){
            super("Error in function evaluation!",e);
        }
    }
}