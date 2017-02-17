package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Variables.Variable;

/**
 * Created by th174 on 2/16/2017.
 */
public interface Predicate extends Invokable {

    @Override
    default Variable invoke(String[] flags, Variable[] args) {
        if (args.length != 1) {
            throw new UnexpectedArgumentException();
        } else {
            return operation(args[0]);
        }
    }

    Variable operation(Variable var);
}
