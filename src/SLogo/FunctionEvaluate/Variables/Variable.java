package SLogo.FunctionEvaluate.Variables;

import java.util.ResourceBundle;

/**
 * Created by th174 on 2/16/2017.
 */
public abstract class Variable<T> implements Comparable<Variable> {
    public static final ResourceBundle regex = ResourceBundle.getBundle("resources.languages/Syntax");
    //IMMUTABLE
    //ALL FIELDS MUST BE FINAL
    private final T value;

    Variable(T value) {
        this.value = value;
    }

    final T value() {
        return value;
    }

    public Variable sum(Variable other) {
        return new NumberVariable(toNumber() + other.toNumber());
    }

    public NumberVariable difference(Variable other) {
        return new NumberVariable(toNumber() - other.toNumber());

    }

    public NumberVariable product(Variable other) {
        return new NumberVariable(toNumber() * other.toNumber());
    }

    public NumberVariable quotient(Variable other) {
        return new NumberVariable(toNumber() / other.toNumber());

    }

    public NumberVariable remainder(Variable other) {
        return new NumberVariable(toNumber() % other.toNumber());

    }

    public Variable negate() {
        try {
            return new NumberVariable(-1 * toNumber());
        } catch (NumberFormatException e) {
            return not();
        }
    }

    public Variable random() {
        return new NumberVariable(Math.random() * toNumber());
    }

    public NumberVariable sine() {
        return new NumberVariable(Math.sin(toNumber()));
    }

    public NumberVariable cosine() {
        return new NumberVariable(Math.cos(toNumber()));
    }

    public NumberVariable tangent() {
        return new NumberVariable(Math.tan(toNumber()));
    }

    public NumberVariable atangent() {
        return new NumberVariable(Math.atan(toNumber()));
    }

    public NumberVariable log() {
        return new NumberVariable(Math.log(toNumber()));
    }

    public NumberVariable power(Variable other) {
        return new NumberVariable(Math.pow(toNumber(), other.toNumber()));
    }

    public BoolVariable lessThan(Variable other) {
        return this.compareTo(other) < 0 ? BoolVariable.TRUE : BoolVariable.FALSE;
    }

    public BoolVariable greaterThan(Variable other) {
        return this.compareTo(other) > 0 ? BoolVariable.TRUE : BoolVariable.FALSE;
    }

    public BoolVariable equalTo(Variable other) {
        return this.equals(other) ? BoolVariable.TRUE : BoolVariable.FALSE;
    }

    public BoolVariable notEqualTo(Variable other) {
        return equalTo(other).negate();
    }

    public Variable and(Variable other) {
        return this.toBoolean() ? other : this;
    }

    public Variable or(Variable other) {
        return this.toBoolean() ? this : other;
    }

    public BoolVariable not() {
        return this.toBoolean() ? BoolVariable.FALSE : BoolVariable.TRUE;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Variable && this.compareTo((Variable) o) == 0;
    }

    @Override
    public int compareTo(Variable other) {
        return (int) ((toNumber() - other.toNumber()) * 1000000000);
    }

    abstract boolean toBoolean();

    abstract double toNumber() throws NumberFormatException;

    @Override
    public String toString() {
        return value.toString();
    }

    public static Variable fromString(String s) {
        if (s.toUpperCase().equals(BoolVariable.TRUE.toString())) {
            return BoolVariable.TRUE;
        } else if (s.toUpperCase().equals(BoolVariable.FALSE.toString())) {
            return BoolVariable.FALSE;
        } else if (s.matches(regex.getString("Constant"))) {
            return new NumberVariable(s);
        } else if (s.matches(regex.getString("StringLiteral"))) {
            return new StringVariable(s.substring(1, s.length() - 1));
        } else {
            throw new UnrecognizedSymbolException(s);
        }
    }

    static class UnrecognizedSymbolException extends RuntimeException {
        UnrecognizedSymbolException(String s) {
            super("Unrecognized Symbol: " + s);
        }
    }

    static class UndefinedOperationException extends RuntimeException {
        UndefinedOperationException() {
            super("Operation is not defined.");
        }

        UndefinedOperationException(String s) {
            super("Operation is not defined on " + s);
        }
    }
}
