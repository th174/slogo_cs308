package SLogo.FunctionEvaluate.Variables;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by th174 on 2/23/2017.
 */
public final class ListVariable extends Variable<List<Variable>> {

    public ListVariable(Variable... vars) {
        super(new LinkedList<>(Arrays.asList(vars)));
    }

    private ListVariable(List<Variable> list) {
        super(list);
    }

    @Override
    public ListVariable append(Variable other) {
        List<Variable> temp = new LinkedList<>(value());
        if (other instanceof ListVariable) {
            temp.addAll(((ListVariable) other).value());
        } else {
            temp.add(other);
        }
        return new ListVariable(temp);
    }

    @Override
    public Variable scalar() {
        return value().get(value().size() - 1);
    }

    @Override
    public boolean toBoolean() {
        return !value().isEmpty();
    }

    @Override
    public double toNumber() throws NotANumberException {
        try {
            if (value().size() == 1) {
                return value().get(0).toNumber();
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            throw new NotANumberException(value().toString());
        }
    }

    @Override
    public String toString() {
        return super.toString().replace("[", "(").replace("]", ")").replace(",", " .");
    }
}