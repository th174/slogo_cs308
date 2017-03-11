package SLogo.FunctionEvaluate.Variables;

/**
 * Created by th174 on 2/16/2017.
 */
public final class NumberVariable extends Variable<Number> {
    NumberVariable(Number value) {
        super(value);
    }

    NumberVariable(String value) throws NotANumberException {
        super(Double.parseDouble(value));
    }

    @Override
    public Variable sum(Variable other) {
        if (other instanceof StringVariable) {
            return new StringVariable(this.toContentString() +  other.toContentString());
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
    public String toContentString() {
        return value().doubleValue() % 1 == 0 ? value().intValue() + "" : super.toContentString();
    }
}
