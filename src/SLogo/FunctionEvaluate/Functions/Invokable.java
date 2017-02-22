package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Variables.Variable;

/**
 * Created by th174 on 2/16/2017.
 */
@FunctionalInterface
public interface Invokable {

    default Variable invoke(Variable... args) {
        return invoke(new String[0], args);
    }

    /**
     * @param flags Optional command line flags
     * @param args  Command line arguments
     * @return Result of the command as a variable
     */
    Variable invoke(String[] flags, Variable... args);

    final class UnexpectedArgumentException extends RuntimeException {
        protected UnexpectedArgumentException(int expected, int actual) {
            super("Unexpected Number of arguments: Expected: " + expected + " Received: " + actual);
        }
    }


}
