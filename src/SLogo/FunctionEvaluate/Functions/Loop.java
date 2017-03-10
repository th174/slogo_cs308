package SLogo.FunctionEvaluate.Functions;

/**
 * Created by th174 on 3/4/2017.
 */
@FunctionalInterface
public interface Loop extends Invokable {
    @Override
    default int minimumArity() {
        return 2;
    }
}
