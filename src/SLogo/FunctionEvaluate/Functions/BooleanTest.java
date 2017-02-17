package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Variables.BoolVariable;
import SLogo.FunctionEvaluate.Variables.Variable;

/**
 * Created by th174 on 2/16/2017.
 */
public interface BooleanTest extends Invokable {

    @Override
    default BoolVariable invoke(String[] flags, Variable[] args){
        if (args.length != 2) {
            throw new UnexpectedArgumentException();
        } else {
            return operation(args[0],args[1]);
        }
    }

    BoolVariable operation(Variable var1, Variable var2);
}
