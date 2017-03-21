package SLogo.FunctionEvaluate.Variables;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * This class models an single immutable value SLogo's dynamic type system, and supports boolean, numerical, and string scalar contexts
 *
 * @author Created by th174 on 2/16/2017.
 */
public abstract class Variable<T> implements Comparable<Variable> {
    public static final Variable PI = new NumberVariable(Math.PI);
    public static final Variable E = new NumberVariable(Math.E);
    public static final Variable TRUE = new BoolVariable(true);
    public static final Variable FALSE = new BoolVariable(false);
    private static final ResourceBundle REGEX = ResourceBundle.getBundle("resources.languages/Syntax");

    private final T value;

    Variable(T value) {
        this.value = value;
    }

    /**
     * Factory method to create a new Variable that wraps a boolean, Number, String, or List.
     *
     * @param obj the object to create a Variable out of
     * @return An instance of Variable, created from o
     */
    public static Variable newInstance(Object obj) {
        if (obj instanceof Variable) {
            return (Variable) obj;
        } else if (obj instanceof Boolean) {
            return (Boolean) obj ? TRUE : FALSE;
        } else if (obj instanceof Number) {
            return new NumberVariable((Number) obj);
        } else if (obj instanceof String) {
            return new StringVariable((String) obj);
        } else if (obj instanceof List) {
            return new ListVariable(((List<Object>) obj).stream().map(Variable::newInstance).collect(Collectors.toList()));
        } else {
            throw new UnrecognizedSymbolException(obj.toString());
        }
    }

    /**
     * Factory method to create a new Variable from a String token
     *
     * @param s the string to create a Variable out of
     * @return An instance of Variable
     */
    public static Variable fromString(String s) {
        if (s.matches(REGEX.getString("Constant"))) {
            return new NumberVariable(s);
        } else if (s.matches(REGEX.getString("StringLiteral"))) {
            return new StringVariable(s.substring(1, s.length() - 1));
        } else if (s.matches(REGEX.getString("Variable"))) {
            return FALSE;
        } else {
            throw new UnrecognizedSymbolException(s);
        }
    }

    /**
     * @return A Map of predefined Variables
     */
    public static Map<String, Variable> getPredefinedVariables() {
        return Arrays.stream(Variable.class.getDeclaredFields()).filter(e -> Variable.class.isAssignableFrom(e.getType())).collect(Collectors.toMap(Field::getName, e -> {
            try {
                return (Variable) e.get(null);
            } catch (IllegalAccessException e1) {
                throw new UnrecognizedSymbolException(e.getName());
            }
        }));
    }

    T value() {
        return value;
    }

    /**
     * @param other Addend
     * @return Sum of two variables in numerical context, with this variable as the augend.
     */
    public Variable sum(Variable other) {
        return new NumberVariable(this.toNumber() + other.toNumber());
    }

    /**
     * @param other Subtrahend
     * @return Difference of two variables in numerical context, with this variable as the minuend.
     */
    public NumberVariable difference(Variable other) {
        return new NumberVariable(toNumber() - other.toNumber());
    }

    /**
     * @param other Multiplier
     * @return Product of two variables in numerical context, with this variable as the multiplicand.
     */
    public NumberVariable product(Variable other) {
        return new NumberVariable(toNumber() * other.toNumber());
    }

    /**
     * @param other Divisor
     * @return Quotient of two variables in numerical context, with this variable as the dividend.
     */
    public NumberVariable quotient(Variable other) {
        if (other.toNumber() == 0) {
            throw new NotANumberException(this.toString() + "/" + other.toString());
        }
        return new NumberVariable(toNumber() / other.toNumber());
    }

    /**
     * @param other Divisor
     * @return Remainder of two variables in numerical context, with this variable as the dividend.
     */
    public NumberVariable remainder(Variable other) {
        return new NumberVariable(toNumber() % other.toNumber());
    }

    /**
     * @return The arithmetic inverse of this variable in numerical context.
     */
    public Variable negate() {
        return new NumberVariable(-1 * toNumber());
    }

    /**
     * @return A random number between 0 and this variable in numerical context.
     */
    public Variable random() {
        return new NumberVariable(Math.random() * toNumber());
    }

    /**
     * @return The sine of this variable in numerical context.
     */
    public NumberVariable sine() {
        return new NumberVariable(Math.sin(Math.toRadians(toNumber())));
    }

    /**
     * @return The cosine of this variable in numerical context.
     */
    public NumberVariable cosine() {
        return new NumberVariable(Math.cos(Math.toRadians(toNumber())));
    }

    /**
     * @return The tangent of this variable in numerical context.
     */
    public NumberVariable tangent() {
        return new NumberVariable(Math.tan(Math.toRadians(toNumber())));
    }

    /**
     * @return The arctangent of this variable in numerical context.
     */
    public NumberVariable atangent() {
        return new NumberVariable(Math.toDegrees(Math.atan(toNumber())));
    }

    /**
     * @return The natural log of this variable in a numerical context.
     */
    public NumberVariable log() {
        return new NumberVariable(Math.log(toNumber()));
    }

    /**
     * @param other Exponent
     * @return Power of two variables in numerical context, with this variable as the base.
     */
    public NumberVariable power(Variable other) {
        return new NumberVariable(Math.pow(toNumber(), other.toNumber()));
    }

    /**
     * @param other Variable to be compared with
     * @return TRUE if this variable is less than other, else FALSE
     */
    public Variable lessThan(Variable other) {
        return this.compareTo(other) < 0 ? Variable.TRUE : Variable.FALSE;
    }

    /**
     * @param other Variable to be compared with
     * @return TRUE if this variable is greater than other, else FALSE
     */
    public Variable greaterThan(Variable other) {
        return this.compareTo(other) > 0 ? Variable.TRUE : Variable.FALSE;
    }

    /**
     * @param other Variable to be compared with
     * @return TRUE if this variable is equal to other, else FALSE
     */
    public Variable equalTo(Variable other) {
        return this.equals(other) ? Variable.TRUE : Variable.FALSE;
    }

    /**
     * @param other Variable to be compared with
     * @return TRUE if this variable is not equal to other, else FALSE
     */
    public Variable notEqualTo(Variable other) {
        return equalTo(other).not();
    }

    /**
     * @param other Variable to be compared with
     * @return If this variable is false in boolean context, returns this variable, otherwise other.
     */
    public Variable and(Variable other) {
        return this.toBoolean() ? other : this;
    }

    /**
     * @param other Variable to be compared with
     * @return If this variable is true in boolean context, returns this variable, otherwise other.
     */
    public Variable or(Variable other) {
        return this.toBoolean() ? this : other;
    }

    /**
     * @return TRUE if this variable is true in boolean context, else FALSE
     */
    public Variable not() {
        return this.toBoolean() ? Variable.FALSE : Variable.TRUE;
    }

    /**
     * @param o Variable to be compared with
     * @return true if this variable is equal to o, else false.
     */
    public boolean equals(Variable o) {
        return this.compareTo(o) == 0;
    }

    /**
     * @param other Variable to be combined with
     * @return A list variable consisting of this variable and other.
     */
    public ListVariable list(Variable other) {
        return new ListVariable(this, other);
    }

    /**
     * {@inheritDoc}
     * Uses numerical context for both variables
     */
    @Override
    public int compareTo(Variable other) {
        try {
            return (int) ((toNumber() - other.toNumber()) * 1000000000);
        } catch (NotANumberException nan) {
            return this.toContentString().compareTo(other.toContentString());
        }
    }

    /**
     * @return Evaluates this variable in boolean context.
     */
    public boolean toBoolean() {
        return !this.equals(FALSE);
    }

    /**
     * @return Evaluates this variable in numerical context
     * @throws NotANumberException Thrown when this variable cannot be evaluated in numerical context
     */
    public abstract double toNumber() throws NotANumberException;

    /**
     * @return Evaluates this variable in String context
     */
    public String toContentString() {
        return value.toString();
    }

    /**
     * @return User friendly string representation of this variable
     */
    @Override
    public String toString() {
        return toContentString();
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
