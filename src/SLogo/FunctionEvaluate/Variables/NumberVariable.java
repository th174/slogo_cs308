package SLogo.FunctionEvaluate.Variables;

/**
 * Created by th174 on 2/16/2017.
 */
public final class NumberVariable extends Variable<Number> {
    //IMMUTABLE CLASS
    //ALL VARIABLES MUST BE FINAL
    public static final NumberVariable PI = new NumberVariable(Math.PI);

    public NumberVariable(Number value) {
        super(value);
    }

    public NumberVariable(String value) throws NumberFormatException {
        super(Double.parseDouble(value));
    }

    @Override
    public Variable sum(Variable other) {
        if (other instanceof StringVariable) {
            return new StringVariable(this.toString() + other.toString());
        } else {
            return super.sum(other);
        }
    }

    @Override
    public NumberVariable negate() {
        return new NumberVariable(-toNumber());
    }

    @Override
    public NumberVariable random() {
        if (toNumber() > 0) {
            return (NumberVariable) super.random();
        } else {
            throw new UndefinedOperationException(toString());
        }
    }

    @Override
    boolean toBoolean() {
        return value().intValue() != 0;
    }

    @Override
    public double toNumber() {
        return value().doubleValue();
    }

    @Override
    public String toString() {
        return value().doubleValue() % 1 == 0 ? value().intValue() + "" : super.toString();
    }
}
