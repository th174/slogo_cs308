package SLogo.FunctionEvaluate.Variables;

import java.util.Random;
import java.util.stream.Collectors;

/**
 * This class models a string value in SLogo's dynamic type system
 *
 * @author Created by th174 on 2/16/2017.
 */
public final class StringVariable extends Variable<String> {

    StringVariable(String value) {
        super(value);
    }

    /**
     * @param other Variable to be concatenated
     * @return A concatenation of this Variable with other in string context
     */
    @Override
    public StringVariable sum(Variable other) {
        return new StringVariable(value() + other.toContentString());
    }

    /**
     * @return A random string with equal or fewer characters that this string
     */
    @Override
    public StringVariable random() {
        return new StringVariable(
                new Random().ints((int) (Math.random() * (value().length() + 1)), ' ', 127)
                        .mapToObj(i -> (char) i)
                        .map(Object::toString)
                        .collect(Collectors.joining()));
    }

    /**
     * If both variables are strings, compares them in string context, else uses number context
     *
     * @param o Variable to be compared with
     * @return true if the variables are equal, else false
     */
    @Override
    public boolean equals(Variable o) {
        return o instanceof StringVariable ? this.toContentString().equals(o.toContentString()) : super.equals(o);
    }

    /**
     * {@inheritDoc}
     *
     * @return false if this variable is the empty string, else true.
     */
    @Override
    public boolean toBoolean() {
        return value().length() > 0;
    }

    /**
     * {@inheritDoc}
     *
     * @return This variable in numerical context by parsing it as a double
     * @throws NotANumberException Thrown when this variable cannot be represented in numerical context
     */
    @Override
    public double toNumber() throws NotANumberException {
        if (value().length() == 0) {
            return 0;
        }
        try {
            return Double.parseDouble(value());
        } catch (NumberFormatException e) {
            throw new NotANumberException(value());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return This variable in string context surrounded by double quotes
     */
    @Override
    public String toString() {
        return '\"' + value() + '\"';
    }
}
