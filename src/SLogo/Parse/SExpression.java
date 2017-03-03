package SLogo.Parse;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.EnvironmentImpl;
import SLogo.FunctionEvaluate.Functions.CommandList;
import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.Variables.Variable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by th174 on 2/17/2017.
 */
public class SExpression extends LinkedList<Expression> implements Expression {
    private int isOp = 0;

    public SExpression() {
        super();
    }

    public Variable eval(Environment env) throws EvaluationTargetException {
        if (size() == 0) {
            return Variable.FALSE;
        }
        Invokable command;
        try {
            command = peek().getCommand(env);
            isOp = 1;
        } catch (EnvironmentImpl.FunctionNotFoundException e) {
            command = CommandList.$DEFAULT_OPERATION$;
            isOp = 0;
        }
        return command.invoke(env, getBody().toArray(new Expression[0]));
    }

    @Override
    public List<Expression> getBody() {
        List<Expression> args = new ArrayList<>();
        for (int i = 0; i < this.size() - isOp; i++) {
            args.add(get(i + isOp));
        }
        return args;
    }

    @Override
    public Invokable getCommand(Environment env) {
        try {
            if (peek().getCommand(env).equals(CommandList.LAMBDA)){
                return (Invokable) eval(env);
            }
            throw new Environment.FunctionNotFoundException(peek().toString());
        } catch (Exception e) {
            throw new Environment.FunctionNotFoundException(toString());
        }
    }

    @Override
    public String toString() {
        return super.toString().replace("[", "(").replace("]", ")").replace(",", "");
    }
}
