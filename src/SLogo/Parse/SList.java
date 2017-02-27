package SLogo.Parse;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.EnvironmentImpl;
import SLogo.FunctionEvaluate.Functions.CommandList;
import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.Variables.Variable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by th174 on 2/17/2017.
 */
public class SList extends LinkedList<RecursiveExpression> implements RecursiveExpression<RecursiveExpression> {

    public SList() {
        super();
    }

    public SList(List<String> l) {
        super();
        addAll(l);
    }

    public SList(RecursiveExpression l) {
        super(l);
    }

    public Variable eval(Environment env) throws EvaluationTargetException {
        Invokable command;
        try {
            command = peek().getCommand(env);
            removeFirst();
        } catch (EnvironmentImpl.FunctionNotFoundException e) {
            command = CommandList.DEFAULT_OPERATION;
        }
        List<String> flags = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            if (get(i) instanceof AtomicList && ((AtomicList) get(i)).isFlag()) {
                flags.add(get(i).toString());
            }
        }
        if (command.equals(CommandList.MAKEVARIABLE)) {
            return evalAssignment(command, flags.toArray(new String[0]), env);
        }
        return evalProcedure(command, flags.toArray(new String[0]), env);
    }

    private Variable evalAssignment(Invokable command, String[] flags, Environment env) throws EvaluationTargetException {
        env.addUserVariable(get(0).toString(), get(1).eval(env));
        try {
            return command.invoke(flags, new Variable[]{get(0).eval(env), get(1).eval(env)});
        } catch (Exception e) {
            throw new EvaluationTargetException(e);
        }
    }

    private Variable evalProcedure(Invokable command, String[] flags, Environment env) throws EvaluationTargetException {
        List<Variable> variables = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            variables.add(get(i).eval(env));
        }
        try {
            return command.invoke(flags, variables.toArray(new Variable[0]));
        } catch (Exception e) {
            throw new EvaluationTargetException(e);
        }
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
