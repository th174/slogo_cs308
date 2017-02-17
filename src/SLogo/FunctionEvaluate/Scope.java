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

    public Variable getVariableByName(String name) {
        return dictionaryVariables.get(name);
    }

    public Invokable getFunctionByName(String name) {
        return dictionaryCommands.get(name);
    }

    private Map<String, Invokable> initCommandDictionary() {
        //TODO: map names to functions
        return new HashMap<>();
    }
}
