package SLogo.FunctionEvaluate.Variables;

/**
 * This class models boolean values in SLogo's dynamic type system
 *
 * @author Created by th174 on 2/16/2017.
 */
public final class BoolVariable extends Variable<Boolean> {

    BoolVariable(Boolean value) {
        super(value);
    }

    /**
     * Negates this variable.
     *
     * @see Variable#not()
     */
    @Override
    public Variable negate() {
        return this.not();
    }

    /**
     * {@inheritDoc}
     *
     * @return 1 if TRUE, or 0 if FALSE
     */
    @Override
    public double numericalContext() {
        return value() ? 1 : 0;
    }
}
