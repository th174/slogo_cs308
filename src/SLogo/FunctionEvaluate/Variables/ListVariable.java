package SLogo.FunctionEvaluate.Variables;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by th174 on 2/23/2017.
 */
public final class ListVariable extends Variable<List<Variable>> {

    ListVariable(Variable... vars) {
        super(new LinkedList<>(Arrays.asList(vars)));
    }

    ListVariable(List<Variable> list) {
        super(list);
    }

    @Override
    public Variable sum(Variable other) {
        return last().sum(other);
    }

    @Override
    public Variable random() {
        return last().random();
    }

    @Override
    public boolean equals(Variable o) {
        return last().equals(o);
    }

    @Override
    public int compareTo(Variable o) {
        return last().compareTo(o);
    }

    public Variable contains(Variable other) {
        return value().stream().anyMatch(other::equals) ? TRUE : FALSE;
    }

    @Override
    public ListVariable list(Variable other) {
        List<Variable> temp = new LinkedList<>(value());
        temp.add(other);
        return new ListVariable(temp);
    }

    @Override
    public boolean toBoolean() {
        return last().toBoolean();
    }

    @Override
    public double toNumber() throws NotANumberException {
        try {
            return last().toNumber();
        } catch (NumberFormatException e) {
            throw new NotANumberException(value().toString());
        }
    }

    @Override
    public String toContentString() {
        return last().toContentString();
    }

    @Override
    public String toString() {
        return super.toContentString().replace("[", "(").replace("]", ")").replace(",", " .");
    }

    private Variable last() {
        return value().get(value().size() - 1);
    }
}
