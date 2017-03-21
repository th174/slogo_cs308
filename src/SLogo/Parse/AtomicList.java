package SLogo.Parse;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Repl;

import java.util.*;

/**
 * An implementation of Expression modeling a single atom.
 * @author Created by th174 on 2/21/2017.
 */
public final class AtomicList extends LinkedList<String> implements Expression {

    public AtomicList(String s) {
        super();
        add(s);
    }

    @Override
    public Variable eval(Repl repl, Environment env) {
        Variable ret = env.getVariableByName(get());
        return Objects.nonNull(ret) ? ret : Variable.fromString(get());
    }

    @Override
    public Invokable getCommand(Environment env) {
        return env.getFunctionByName(get());
    }

    @Override
    public List<Expression> getBody() {
        return new LinkedList<>(Collections.singleton(this));
    }

    private String get() {
        return get(0);
    }

    @Override
    public String toString() {
        return get(0);
    }

}
