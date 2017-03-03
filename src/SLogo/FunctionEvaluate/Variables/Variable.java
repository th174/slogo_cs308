package SLogo.FunctionEvaluate.Variables;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created by th174 on 2/16/2017.
 */
public abstract class Variable<T> implements Comparable<Variable> {
    public static final Variable<Number> PI = new NumberVariable(Math.PI);
    public static final Variable<Number> E = new NumberVariable(Math.E);
    public static final Variable<Boolean> TRUE = new BoolVariable(true);
    public static final Variable<Boolean> FALSE = new BoolVariable(false);
    public static final ResourceBundle regex = ResourceBundle.getBundle("resources.languages/Syntax");

    private final T value;

    Variable(T value) {
        this.value = value;
    }

    public static Variable newInstance(Object o) {
        if (o instanceof Boolean) {
            return ((Boolean) o).booleanValue() ? TRUE : FALSE;
        } else if (o instanceof Number) {
            return new NumberVariable((Number) o);
        } else if (o instanceof String) {
            return new StringVariable((String) o);
        } else if (o instanceof List) {
            return new ListVariable((List<Variable>) o);
        } else {
            throw new UnrecognizedSymbolException(o.toString());
        }
    }

    final T value() {
        return value;
    }

    public Variable sum(Variable other) {
        return new NumberVariable(this.toNumber() + other.toNumber());
    }

    public NumberVariable difference(Variable other) {
        return new NumberVariable(toNumber() - other.toNumber());
    }

    public NumberVariable product(Variable other) {
        return new NumberVariable(toNumber() * other.toNumber());
    }

    public NumberVariable quotient(Variable other) {
        if (other.toNumber() == 0) {
            throw new NotANumberException(this.toString() + "/" + other.toString());
        }
        return new NumberVariable(toNumber() / other.toNumber());
    }

    public NumberVariable remainder(Variable other) {
        return new NumberVariable(toNumber() % other.toNumber());
    }

    public Variable negate() {
        return new NumberVariable(-1 * toNumber());
    }

    public Variable random() {
        return new NumberVariable(Math.random() * toNumber());
    }

    public NumberVariable sine() {
        return new NumberVariable(Math.sin(Math.toRadians(toNumber())));
    }

    public NumberVariable cosine() {
        return new NumberVariable(Math.cos(Math.toRadians(toNumber())));
    }

    public NumberVariable tangent() {
        return new NumberVariable(Math.tan(Math.toRadians(toNumber())));
    }

    public NumberVariable atangent() {
        return new NumberVariable(Math.toDegrees(Math.atan(toNumber())));
    }

    public NumberVariable log() {
        return new NumberVariable(Math.log(toNumber()));
    }

    public NumberVariable power(Variable other) {
        return new NumberVariable(Math.pow(toNumber(), other.toNumber()));
    }

    public Variable lessThan(Variable other) {
        return this.compareTo(other) < 0 ? Variable.TRUE : Variable.FALSE;
    }

    public Variable greaterThan(Variable other) {
        return this.compareTo(other) > 0 ? Variable.TRUE : Variable.FALSE;
    }

    public Variable equalTo(Variable other) {
        return this.equals(other) ? Variable.TRUE : Variable.FALSE;
    }

    public Variable notEqualTo(Variable other) {
        return equalTo(other).negate();
    }

    public Variable and(Variable other) {
        return this.toBoolean() ? other : this;
    }

    public Variable or(Variable other) {
        return this.toBoolean() ? this : other;
    }

    public Variable not() {
        return this.toBoolean() ? Variable.FALSE : Variable.TRUE;
    }

    public boolean equals(Variable o) {
        return this.compareTo(o) == 0;
    }

    public ListVariable list(Variable other) {
        return new ListVariable(this, other);
    }

    public int size() {
        return 1;
    }

    @Override
    public int compareTo(Variable other) {
        return (int) ((toNumber() - other.toNumber()) * 1000000000);
    }

    public abstract boolean toBoolean();

    public abstract double toNumber() throws NotANumberException;

    public String toContentString() {
        return value.toString();
    }

    @Override
    public String toString(){
        return toContentString();
    }

    public static Variable<? extends java.io.Serializable> fromString(String s) {
        if (s.matches(regex.getString("Constant"))) {
            try {
                return new NumberVariable(Double.parseDouble(s));
            } catch (NumberFormatException e) {
                throw new NotANumberException(s);
            }
        } else if (s.matches(regex.getString("StringLiteral"))) {
            return new StringVariable(s.substring(1, s.length() - 1));
        } else {
            throw new UnrecognizedSymbolException(s);
        }
    }

    public static Collection<Field> getPredefinedVariables() {
        return Arrays.stream(Variable.class.getDeclaredFields()).filter(e -> e.getType().equals(Variable.class)).collect(Collectors.toSet());
    }

    static class UnrecognizedSymbolException extends RuntimeException {
        UnrecognizedSymbolException(String s) {
            super("Unrecognized Symbol: " + s);
        }
    }

    static class NotANumberException extends RuntimeException {
        NotANumberException(String s) {
            super("Not a number: " + s);
        }
    }
}
