package SLogo.FunctionEvaluate.Variables;

import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by th174 on 2/16/2017.
 */
public final class StringVariable extends Variable<String> {
    StringVariable(String value) {
        super(value);
    }

    @Override
    public StringVariable sum(Variable other) {
        return new StringVariable(value() + other.toContentString());
    }

    @Override
    public StringVariable random() {
        return new StringVariable(
                new Random().ints((int) (Math.random() * (value().length() + 1)), ' ', 127)
                        .mapToObj(i -> (char) i)
                        .map(Object::toString)
                        .collect(Collectors.joining()));
    }

    @Override
    public boolean equals(Variable o) {
        return o instanceof StringVariable ? this.toContentString().equals(o.toContentString()) : super.equals(o);
    }

    @Override
    public boolean toBoolean() {
        return value().length() > 0;
    }

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

    @Override
    public String toString() {
        return '\"' + value() + '\"';
    }
}
