package SLogo.FunctionEvaluate.Variables;

/**
 * This class models a numerical value in SLogo's dynamic type system
 *
 * @author Created by th174 on 2/16/2017.
 */
public final class NumberVariable extends Variable<Number> {

    NumberVariable(Number value) {
        super(value);
    }

    NumberVariable(String value) throws NotANumberException {
        super(Double.parseDouble(value));
    }

    /**
     * {@inheritDoc}
     * If other is a string, concatenates the two variables in string context
     */
    @Override
    public Variable sum(Variable other) {
        if (other instanceof StringVariable) {
            return new StringVariable(this.toContentString() + other.toContentString());
        } else {
            return super.sum(other);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return false if this variable is 0, else true
     */
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
