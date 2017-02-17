package SLogo.SLogoRead;

import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.Variables.Variable;

import java.util.List;

/**
 * Created by th174 on 2/17/2017.
 */
public final class Command {
    private final Invokable function;
    private final List<String> flags;
    private final List<Variable> args;

    public Command(Invokable function, List<String> flags, List<Variable> args) {
        this.function = function;
        this.flags = flags;
        this.args = args;
    }

    public Variable execute() {
        return function.invoke(flags.toArray(new String[flags.size()]), args.toArray(new Variable[args.size()]));
    }
}
