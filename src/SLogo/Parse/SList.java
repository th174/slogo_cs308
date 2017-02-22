package SLogo.Parse;

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
public class SList extends LinkedList<Expression> implements Expression<Expression> {

    public SList() {
        super();
    }

    public SList(List<String> l) {
        super();
        addAll(l);
    }

    public SList(Expression l) {
        super(l);
    }

    public Variable eval() throws EvaluationTargetException {
        Invokable function;
        if (!(peek() instanceof AtomicList)) {
            return pop().eval();
        } else if (peek() instanceof AtomicList) {
            try {
                function = peek().getFunction();
                removeFirst();
            } catch (EnvironmentImpl.FunctionNotFoundException e) {
                function = BasicOperations.SUM;
            }
            List<String> flags = new ArrayList<>();
            List<Variable> variables = new ArrayList<>();
            for (int i = 0; i < this.size(); i++) {
                if (get(i) instanceof AtomicList && ((AtomicList) peekFirst()).isFlag()) {
                    flags.add(get(i).toString());
                } else {
                    variables.add(get(i).eval());
                }
            }
            try {
                return function.invoke(flags.toArray(new String[0]), variables.toArray(new Variable[0]));
            } catch (Exception e){
                throw new EvaluationTargetException(e);
            }
        } else if (size() == 1) {
            return pop().eval();
        } else {
            throw new LispSyntaxParser.SyntaxException(toString());
        }
    }

    private boolean addAll(List<String> c) {
        return super.addAll(c.stream().map(AtomicList::new).collect(Collectors.toList()));
    }

    @Override
    public Invokable getFunction() {
        return null;
    }

    @Override
    public String toString() {
        String s = super.toString().replace("[", "(").replace("]", ")").replace(",", " .");
        while (s.startsWith("((") && s.endsWith("))")) {
            s = s.substring(1, s.length()-1);
        }
        return s;
    }
}
