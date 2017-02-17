package SLogo.SLogoRead;

import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.Variables.Variable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by th174 on 2/17/2017.
 */
public final class Command {
    private final Invokable function;
    private final String[] flags;
    private final Variable[] args;

    public Command(Invokable function, List<String> flags, List<Variable> args) {
        this.function = function;
        this.flags = flags.toArray(new String[flags.size()]);
        this.args = args.toArray(new Variable[args.size()]);
    }

    public Variable execute() throws InvalidFunctionArgumentsException {
        try {
            return function.invoke(flags, args);
        } catch (Exception e) {
            throw new InvalidFunctionArgumentsException(toString());
        }
    }

    @Override
    public String toString() {
        return function.toString() + " -" + String.join(" -", flags) + " " + String.join(" " + Arrays.toString(args));
    }

    public class InvalidFunctionArgumentsException extends RuntimeException {
        InvalidFunctionArgumentsException(String name) {
            super("Invalid function arguments: " + name);
        }
    }
}
