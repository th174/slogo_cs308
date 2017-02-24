package SLogo.FunctionEvaluate;

import SLogo.FunctionEvaluate.Functions.BasicOperations;
import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.Variables.Variable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by th174 on 2/16/2017.
 */
public class EnvironmentImpl implements Environment {
    private Map<String, Variable> dictionaryVariables;
    private Map<String, Invokable> dictionaryFunctions;

    public EnvironmentImpl() {
        dictionaryVariables = new HashMap<>();
        dictionaryFunctions = initCommandDictionary();
    }

    @Override
    public Map<String, Variable> getUserVars() {
        return null;
    }

    @Override
    public Map<String, Invokable> getUserFunctions() {
        return null;
    }

    @Override
    public Map<String, Variable> getAllVars() {
        return dictionaryVariables;
    }

    @Override
    public Map<String, Invokable> getAllFunctions() {
        return dictionaryFunctions;
    }

    @Override
    public Variable getVariableByName(String name) throws VariableNotFoundException {
        if (dictionaryVariables.containsKey(name)) {
            return dictionaryVariables.get(name);
        } else {
            throw new VariableNotFoundException(name);
        }
    }

    @Override
    public Invokable getFunctionByName(String name) throws FunctionNotFoundException {
        if (dictionaryFunctions.containsKey(name)) {
            return dictionaryFunctions.get(name);
        } else {
            throw new FunctionNotFoundException(name);
        }
    }

    @Override
    public void addUserVariable(String name, Variable var) {
        dictionaryVariables.put(name, var);
    }

    @Override
    public void addUserFunction(String name, Invokable function) {
        dictionaryFunctions.put(name, function);
    }

    private Map<String, Invokable> initCommandDictionary() {
        Map<String, Invokable> commands = new HashMap<>();
        BasicOperations.getAllCommands().forEach(e -> {
            try {
                commands.put(e.getName(), (Invokable) e.get(null));
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            }
        });
        return commands;
    }
}
