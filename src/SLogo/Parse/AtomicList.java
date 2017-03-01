package SLogo.Parse;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.Variables.Variable;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by th174 on 2/21/2017.
 */
public final class AtomicList extends LinkedList<String> implements Expression {

    public AtomicList(String s) {
        super();
        add(s);
    }

    @Override
    public Variable eval(Environment env) throws EvaluationTargetException, Environment.VariableNotFoundException {
        if (get().matches(Variable.regex.getString("Variable"))) {
            return env.getVariableByName(get());
        } else {
            try {
                return Variable.fromString(get());
            } catch (Exception e) {
                throw new EvaluationTargetException(e);
            }
        }
    }

    @Override
    public Invokable getCommand(Environment env) {
        return env.getFunctionByName(get());
    }

    @Override
    public Expression[] getBody() {
        return new Expression[]{this};
    }

    public boolean add(String o) {
        if (super.size() > 0) {
            throw new AtomicException();
        } else {
            return super.add(o);
        }
    }

    private String get() {
        return get(0);
    }

    @Override
    public boolean addAll(Collection c) {
        if (c.size() + this.size() > 1) {
            throw new AtomicException();
        } else {
            return super.addAll(c);
        }
    }

    @Override
    public int size() {
        if (super.size() > 1) {
            throw new RuntimeException("How did you end up with more than 1 wtf are you doing");
        } else {
            return super.size();
        }
    }

    @Override
    public String toString() {
        return get(0);
    }

    private static class AtomicException extends RuntimeException {
        private AtomicException() {
            super("Size of AtomicList cannot go above 1! That's why it's atomic.");
        }
    }
}
