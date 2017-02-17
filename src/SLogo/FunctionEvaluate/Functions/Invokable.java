package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Variables.Variable;

/**
 * Created by th174 on 2/16/2017.
 */
@FunctionalInterface
public interface Invokable {
    Variable invoke(String[] flags, Variable... args);

    final class UnexpectedArgumentException extends RuntimeException {
        UnexpectedArgumentException() {
            super();
        }

        protected UnexpectedArgumentException(String s) {
            super(s);
        }
    }
}
