package SLogo.FunctionEvaluate.Variables;

/**
 * Created by th174 on 2/16/2017.
 */
public final class NumberVariable extends Variable<Number> {
    //IMMUTABLE CLASS
    //ALL VARIABLES MUST BE FINAL
    NumberVariable(Number value) {
        super(value);
    }

    NumberVariable(String value) throws NotANumberException {
        super(Double.parseDouble(value));
    }

    @Override
    public Variable sum(Variable other) {
        if (other instanceof StringVariable) {
            return new StringVariable(this.toString() + ((StringVariable) other).value());
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
            return (NumberVariable) super.random();
    }

    @Override
    public boolean toBoolean() {
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
