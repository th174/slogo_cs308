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
 * Created by th174 on 2/17/2017.
 */
public class SExpression extends LinkedList<Expression> implements Expression {
    private int isOp = 0;

    public SExpression() {
        super();
    }

    public Variable eval(Repl repl, Environment env) throws EvaluationTargetException {
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
        return null;
    }

    @Override
    public String toString() {
        return super.toString().replace("[", "(").replace("]", ")").replace(",", "").replace('\u039b', '\u03bb');
    }
}
