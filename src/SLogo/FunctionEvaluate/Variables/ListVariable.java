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
    public ListVariable list(Variable other) {
        List<Variable> temp = new LinkedList<>(value());
        temp.add(other);
        return new ListVariable(temp);
    }

    @Override
    public boolean toBoolean() {
        return value().get(value().size() - 1).toBoolean();
    }

    @Override
    public double toNumber() throws NotANumberException {
        try {
            return value().get(value().size() - 1).toNumber();
        } catch (NumberFormatException e) {
            throw new NotANumberException(value().toString());
        }
    }

    @Override
    public String toString() {
        return super.toString().replace("[", "(").replace("]", ")").replace(",", " .");
    }
}
