package SLogo.FunctionEvaluate.Variables;

import java.util.Objects;

/**
 * Created by th174 on 2/16/2017.
 */
public final class BoolVariable extends Variable<Boolean> {
    public static final BoolVariable TRUE = new BoolVariable(true);
    public static final BoolVariable FALSE = new BoolVariable(false);

    private BoolVariable(Number n) {
        super(Objects.nonNull(n) && n.intValue() != 0);
    }

    private BoolVariable(String s) {
        super(Objects.nonNull(s) && s.length() > 0);
    }

    private BoolVariable(Boolean value) {
        super(value);
    }

    @Override
    public BoolVariable negate() {
        return this.equals(TRUE) ? FALSE : TRUE;
    }

    @Override
    boolean toBoolean() {
        return value();
    }

    @Override
    double toNumber() {
        return value() ? 1 : 0;
    }

    @Override
    public String toString() {
        return value() ? "true" : "false";
    }
}
