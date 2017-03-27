package SLogo.FunctionEvaluate.Variables;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


/**
 * <strong>This entire file is part of my masterpiece.</strong>
 * It's responsible for SLogo's Javascript-inspired dynamic typing, which currently supports Booleans, Numbers, Strings, and Lists.
 * SLogo's dynamic type system was something that I had designed from the start, and the very first thing I implemented.
 * It was only after it was complete that I realized SLogo's specifications only supported numerical variable types, so this is one of the most unique things about our SLogo language implementation.
 * Additionally, this class heavily uses the discouraged instanceof keyword to emulate the behavior of dynamic dispatch on overloaded methods.
 * I asked on Piazza about strategies to avoid this, but most are either excessively verbose or have other major downsides that I'd like to discuss with a TA before choosing one to use.
 * <p>
 * Timmy Huang
 * <p>
 * This class models a single immutable value in SLogo's weakly-typed dynamic type system, and supports boolean, numerical, and string scalar contexts
 * This particular implementation was based off of Javascript's dynamic type system.
 * See <a href="CommandList.html">CommandList.html</a> for more details.
 *
 * @author Created by th174 on 2/16/2017.
 */
public abstract class Variable<T> implements Comparable<Variable> {
    public static final Variable PI = Variable.newInstance(Math.PI);
    public static final Variable E = Variable.newInstance(Math.E);
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
            return Variable.newInstance(Double.parseDouble(s));
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
        return Variable.newInstance((other instanceof StringVariable) ? this.stringContext() + other.stringContext() : this.numericalContext() + other.numericalContext());
    }

    /**
     * @param other Subtrahend
     * @return Difference of two variables in numerical context, with this variable as the minuend.
     */
    public Variable difference(Variable other) {
        return Variable.newInstance(this.numericalContext() - other.numericalContext());
    }

    /**
     * @param other Multiplier
     * @return Product of two variables in numerical context, with this variable as the multiplicand.
     */
    public Variable product(Variable other) {
        return Variable.newInstance(numericalContext() * other.numericalContext());
    }

    /**
     * @param other Divisor
     * @return Quotient of two variables in numerical context, with this variable as the dividend.
     */
    public Variable quotient(Variable other) {
        if (other.numericalContext() == 0) {
            throw new NotANumberException(this.toString() + "/" + other.toString());
        }
        return Variable.newInstance(numericalContext() / other.numericalContext());
    }

    /**
     * @param other Divisor
     * @return Remainder of two variables in numerical context, with this variable as the dividend.
     */
    public Variable remainder(Variable other) {
        return Variable.newInstance(numericalContext() % other.numericalContext());
    }

    /**
     * @return The arithmetic inverse of this variable in numerical context.
     */
    public Variable negate() {
        return Variable.newInstance(-1 * numericalContext());
    }

    /**
     * @return A random number between 0 and this variable in numerical context.
     */
    public Variable random() {
        return Variable.newInstance(Math.random() * numericalContext());
    }

    /**
     * @return The sine of this variable in numerical context.
     */
    public Variable sine() {
        return Variable.newInstance(Math.sin(Math.toRadians(numericalContext())));
    }

    /**
     * @return The cosine of this variable in numerical context.
     */
    public Variable cosine() {
        return Variable.newInstance(Math.cos(Math.toRadians(numericalContext())));
    }

    /**
     * @return The tangent of this variable in numerical context.
     */
    public Variable tangent() {
        return Variable.newInstance(Math.tan(Math.toRadians(numericalContext())));
    }

    /**
     * @return The arctangent of this variable in numerical context.
     */
    public Variable atangent() {
        return Variable.newInstance(Math.toDegrees(Math.atan(numericalContext())));
    }

    /**
     * @return The natural log of this variable in a numerical context.
     */
    public Variable log() {
        return Variable.newInstance(Math.log(numericalContext()));
    }

    /**
     * @param other Exponent
     * @return Power of two variables in numerical context, with this variable as the base.
     */
    public Variable power(Variable other) {
        return Variable.newInstance(Math.pow(numericalContext(), other.numericalContext()));
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
        return this.booleanContext() ? other : this;
    }

    /**
     * @param other Variable to be compared with
     * @return If this variable is true in boolean context, returns this variable, otherwise other.
     */
    public Variable or(Variable other) {
        return this.booleanContext() ? this : other;
    }

    /**
     * @return TRUE if this variable is true in boolean context, else FALSE
     */
    public Variable not() {
        return this.booleanContext() ? Variable.FALSE : Variable.TRUE;
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
    public Variable list(Variable other) {
        return new ListVariable(this, other);
    }

    /**
     * {@inheritDoc}
     * Uses numerical context for both variables
     */
    @Override
    public int compareTo(Variable other) {
        try {
            return (int) ((numericalContext() - other.numericalContext()) * 1000000000);
        } catch (NotANumberException nan) {
            return this.stringContext().compareTo(other.stringContext());
        }
    }

    /**
     * @return Evaluates this variable in boolean context.
     */
    public boolean booleanContext() {
        return !this.equals(FALSE);
    }

    /**
     * @return Evaluates this variable in numerical context
     * @throws NotANumberException Thrown when this variable cannot be evaluated in numerical context
     */
    public double numericalContext() throws NotANumberException {
        throw new NotANumberException(this.toString());
    }

    /**
     * @return Evaluates this variable in String context
     */
    public String stringContext() {
        return value.toString();
    }

    /**
     * @return User friendly string representation of this variable
     */
    @Override
    public String toString() {
        return stringContext();
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
