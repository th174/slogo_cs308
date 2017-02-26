package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.RecursiveExpression;

import java.util.Arrays;

/**
 * Created by th174 on 2/16/2017.
 */
@FunctionalInterface
public interface Accumulator extends Invokable {

    @Override
    default Variable invoke(String[] flags, Variable[] args, RecursiveExpression[] expr, RecursiveExpression[] alt) {
        if (args.length == 0) {
            return new NumberVariable(0);
        }
        Variable total = args[0];
        if (args.length == 1) {
            return total;
        } else {
            return accumulate(total, invoke(flags, Arrays.copyOfRange(args, 1, args.length)));
        }
    }

    Variable accumulate(Variable var1, Variable var2);
}
