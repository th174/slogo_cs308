package SLogo.FunctionEvaluate;

import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.Variables.Variable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by th174 on 2/16/2017.
 */
public class Scope {
    public static final String DEFAULT_LANGUAGE = "English";
    private String languageSet;
    private Map<String, Variable> dictionaryVariables;
    private Map<String, Invokable> dictionaryCommands;

    public Scope() {
        this(DEFAULT_LANGUAGE);
    }

    public Scope(String languageSet) {
        this.languageSet = languageSet;
        dictionaryVariables = new HashMap<>();
        dictionaryCommands = initCommandDictionary();
    }

    public Variable getVariableByName(String name) throws VariableNotFoundException {
        if (dictionaryVariables.containsKey(name)) {
            return dictionaryVariables.get(name);
        } else {
            throw new VariableNotFoundException(name);
        }
    }

    public Invokable getFunctionByName(String name) throws FunctionNotFoundException {
        if (dictionaryCommands.containsKey(name)) {
            return dictionaryCommands.get(name);
        } else {
            throw new FunctionNotFoundException(name);
        }
    }

    private Map<String, Invokable> initCommandDictionary() {
        //TODO: map names to functions
        return new HashMap<>();
    }

    public class VariableNotFoundException extends RuntimeException {
        VariableNotFoundException(String variableName) {
            super("Variable \'" + variableName + "\' is unbound.");
        }
    }

    public class FunctionNotFoundException extends RuntimeException {
        FunctionNotFoundException(String functionName) {
            super("Function \'" + functionName + "\' is undefined.");
        }
    }
}
