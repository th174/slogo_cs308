package SLogo.FunctionEvaluate.Variables;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * This class models a list in SLogo's dynamic type system
 *
 * @author Created by th174 on 2/23/2017.
 */
public final class ListVariable extends Variable<List<Variable>> {

    ListVariable(Variable... vars) {
        super(new LinkedList<>(Arrays.asList(vars)));
    }

    ListVariable(List<Variable> list) {
        super(list);
    }

    /**
     * @param other Variable to be combined with
     * @return A new list consisting of this variable with other appended to the end
     * @see Variable#list(Variable)
     */
    @Override
    public ListVariable list(Variable other) {
        List<Variable> temp = new LinkedList<>(value());
        temp.add(other);
        return new ListVariable(temp);
    }

    /**
     * @return The last element of this ListVariable in boolean context
     */
    @Override
    public boolean booleanContext() {
        return last().booleanContext();
    }

    /**
     * @return The last element of this ListVariable in numerical context
     * @throws NotANumberException Thrown if the last element cannot be represented in numerical context
     */
    @Override
    public double numericalContext() throws NotANumberException {
        try {
            return last().numericalContext();
        } catch (NumberFormatException e) {
            throw new NotANumberException(value().toString());
        }
    }

    @Override
    public String stringContext() {
        return last().stringContext();
    }

    @Override
    public String toString() {
        return super.stringContext().replace("[", "(").replace("]", ")");
    }

    private Variable last() {
        return value().get(value().size() - 1);
    }
}
