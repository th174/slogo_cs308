package SLogo.Parse;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.EnvironmentImpl;
import SLogo.FunctionEvaluate.Functions.BasicOperations;
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

    public Variable eval() throws EvaluationTargetException {
        Invokable function;
        try {
            function = peek().getFunction();
            removeFirst();
        } catch (EnvironmentImpl.FunctionNotFoundException e) {
            function = BasicOperations.DEFAULT_OPERATION;
        }
        List<String> flags = new ArrayList<>();
        List<Variable> variables = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            if (get(i) instanceof AtomicList && ((AtomicList) get(i)).isFlag()) {
                flags.add(get(i).toString());
            } else {
                variables.add(get(i).eval());
            }
        }
        try {
            return function.invoke(flags.toArray(new String[0]), variables.toArray(new Variable[0]));
        } catch (Exception e) {
            throw new EvaluationTargetException(e);
        }
    }


    private boolean addAll(List<String> c) {
        return super.addAll(c.stream().map(AtomicList::new).collect(Collectors.toList()));
    }

    @Override
    public Invokable getFunction() {
        throw new Environment.FunctionNotFoundException(toString());
    }

    @Override
    public String toString() {
        return super.toString().replace("[", "(").replace("]", ")").replace(",", " .");
    }
}
