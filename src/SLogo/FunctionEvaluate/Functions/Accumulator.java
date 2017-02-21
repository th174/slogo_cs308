package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;

/**
 * Created by th174 on 2/16/2017.
 */
@FunctionalInterface
public interface Accumulator extends Invokable {

    @Override
    default Variable invoke(String[] flags, Variable[] args) {
        if (args.length == 0) {
            return new NumberVariable(0);
        }
        Variable total = args[0];
        for (int i = 1; i < args.length; i++) {
            total = accumulate(args[i - 1], args[i]);
        }
        return total;
    }

    Variable accumulate(Variable var1, Variable var2);
}
