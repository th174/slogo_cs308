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

    @Override
    public Variable sum(Variable other) {
        if (other instanceof NumberVariable) {
            return super.sum(other);
        } else {
            try {
                return super.sum(other);
            } catch (Exception e) {
                return new StringVariable(this.toString() + other.toString());
            }
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
            throw new UndefinedOperationException();
        }
    }

    @Override
    boolean toBoolean() {
        return value().intValue() != 0;
    }

    @Override
    double toNumber() {
        return value().doubleValue();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
