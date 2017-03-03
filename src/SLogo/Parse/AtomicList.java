package SLogo.Parse;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.Variables.Variable;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
        try {
            return env.getVariableByName(get());
        } catch (Environment.VariableNotFoundException vnfe) {
            return Variable.fromString(get());
        }
    }

    @Override
    public Invokable getCommand(Environment env) {
        try {
            Variable temp = env.getVariableByName(get());
            if (temp instanceof Invokable) {
                return (Invokable) temp;
            } else {
                throw new Environment.VariableNotFoundException(temp.toString());
            }
        } catch (Environment.VariableNotFoundException e) {
            return env.getFunctionByName(get());
        }
    }

    @Override
    public List<Expression> getBody() {
        return Collections.singletonList(this);
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
