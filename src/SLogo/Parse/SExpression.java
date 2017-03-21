package SLogo.Parse;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.PredefinedCommandList;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Repl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * This class models an Expression as a Lisp-style linked list, where the first element is a function, and all further arguments are function arguments
 *
 * @author Created by th174 on 2/17/2017.
 */
public class SExpression extends LinkedList<Expression> implements Expression {
    private int isOp = 0;

    @Override
    public Variable eval(Repl repl, Environment env) throws EvaluationTargetException {
        try {
            if (size() == 0) {
                return Variable.FALSE;
            }
            Invokable command = peek().getCommand(env);
            if (Objects.isNull(command)) {
                command = PredefinedCommandList.$DEFAULT_OPERATION$;
                isOp = 0;
            } else {
                isOp = 1;
            }
            return command.invoke(repl, env, getBody().toArray(new Expression[0]));
        } catch (EvaluationTargetException e) {
            throw e;
        } catch (Exception e) {
            throw new EvaluationTargetException(e);
        }
    }

    @Override
    public List<Expression> getBody() {
        List<Expression> args = new ArrayList<>();
        for (int i = 0; i < this.size() - isOp; i++) {
            args.add(get(i + isOp));
        }
        return args;
    }

    /**
     * Returns null because SExpressions are lists, and cannot be parsed to a single Function
     *
     * @param env Current local environment
     * @return null
     */
    @Override
    public Invokable getCommand(Environment env) {
        return null;
    }

    @Override
    public String toString() {
        return super.toString().replace("[", "(").replace("]", ")").replace(",", "").replace('\u039b', '\u03bb');
    }
}
