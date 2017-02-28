package SLogo.Parse;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.EnvironmentImpl;
import SLogo.FunctionEvaluate.Functions.CommandList;
import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.Variables.Variable;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by th174 on 2/17/2017.
 */
public class SExpression extends LinkedList<Expression> implements Expression {

    public SExpression() {
        super();
    }

    public SExpression(List<String> l) {
        super();
        addAll(l);
    }

    public Variable eval(Environment env) throws EvaluationTargetException {
        Invokable command;
        try {
            command = peek().getCommand(env);
        } catch (EnvironmentImpl.FunctionNotFoundException e) {
            command = CommandList.DEFAULT_OPERATION;
            push(new AtomicList("LIST"));
        }
        return command.invoke(env, getBody());
    }

    @Override
    public Expression[] getBody() {
        Expression[] args = new Expression[this.size() - 1];
        for (int i = 0; i < args.length; i++) {
            args[i] = get(i + 1);
        }
        return args;
    }

    private boolean addAll(List<String> c) {
        return super.addAll(c.stream().map(AtomicList::new).collect(Collectors.toList()));
    }

    @Override
    public Invokable getCommand(Environment env) {
        throw new Environment.FunctionNotFoundException(toString());
    }

    @Override
    public String toString() {
        return super.toString().replace("[", "(").replace("]", ")").replace(",", " .");
    }
}
