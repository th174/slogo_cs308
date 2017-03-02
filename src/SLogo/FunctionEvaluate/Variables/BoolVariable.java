package SLogo.FunctionEvaluate.Variables;

import java.util.Objects;

/**
 * Created by th174 on 2/16/2017.
 */
public final class BoolVariable extends Variable<Boolean> {

    private BoolVariable(Number n) {
        super(Objects.nonNull(n) && n.intValue() != 0);
    }

    private BoolVariable(String s) {
        super(Objects.nonNull(s) && s.length() > 0);
    }

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

    @Override
    public String toString() {
        return value() ? "true" : "false";
    }
}
