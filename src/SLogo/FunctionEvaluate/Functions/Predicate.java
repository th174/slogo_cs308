package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Variables.Variable;

/**
 * Created by th174 on 2/16/2017.
 */
public interface Predicate extends Invokable {
    int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    @Override
    default Variable invoke(String[] flags, Variable[] args) {
        if (args.length != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new UnexpectedArgumentException(EXPECTED_NUMBER_OF_ARGUMENTS, args.length);
        } else {
            return operation(args[0]);
        }
    }

    Variable operation(Variable var);
}
