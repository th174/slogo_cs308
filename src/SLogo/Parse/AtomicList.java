package SLogo.Parse;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.Variables.Variable;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by th174 on 2/21/2017.
 */
public final class AtomicList extends LinkedList<String> implements Expression<String> {

    private static Environment env;
    private final boolean isFlag;

    public AtomicList(String s) {
        super();
        isFlag = s.matches(Variable.regex.getString("Flag"));
        add(isFlag ? s.substring(1) : s);
    }

    @Override
    public Variable eval() throws EvaluationTargetException {
        if (get().matches(Variable.regex.getString("Variable"))) {
            try {
                return env.getVariableByName(get());
            } catch (Exception e) {
                throw new EvaluationTargetException(e);
            }
        } else {
            try {
                return Variable.fromString(get());
            } catch (Exception e) {
                throw new EvaluationTargetException(e);
            }
        }
    }

    @Override
    public Invokable getFunction() {
        return env.getFunctionByName(get());
    }

    public boolean add(String o) {
        if (super.size() > 0) {
            throw new AtomicException();
        } else {
            return super.add(o);
        }
    }

    boolean isFlag() {
        return isFlag;
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

    static void setEnvironment(Environment env) {
        AtomicList.env = env;
    }

    private static class AtomicException extends RuntimeException {
        private AtomicException() {
            super("Size of AtomicList cannot go above 1! That's why it's atomic.");
        }
    }
}
