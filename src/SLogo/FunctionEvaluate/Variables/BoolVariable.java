package SLogo.FunctionEvaluate.Variables;

/**
 * Created by th174 on 2/16/2017.
 */
public final class BoolVariable extends Variable<Boolean> {

    BoolVariable(Boolean value) {
        super(value);
    }

    @Override
    public Variable negate() {
        return this.equals(TRUE) ? FALSE : TRUE;
    }

    @Override
    public boolean toBoolean() {
        return value();
    }

    @Override
    public double toNumber() {
        return value() ? 1 : 0;
    }
}
