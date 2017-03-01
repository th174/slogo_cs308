package SLogo.FunctionEvaluate;

import SLogo.FunctionEvaluate.Functions.CommandList;
import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Turtles.Turtle;
import SLogo.View.CanvasView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by th174 on 2/16/2017.
 */
public class EnvironmentImpl implements Environment {
    private Environment outer;
    private Map<String, Variable> dictionaryVariables;
    private Map<String, Variable> userVariables;
    private Map<String, Invokable> dictionaryFunctions;
    private Map<String, Invokable> userFunctions;
    private Turtle myTurtle;
    private CanvasView myCanvas;

    public EnvironmentImpl() {
        dictionaryVariables = new HashMap<>();
        userVariables = new HashMap<>();
        dictionaryFunctions = initCommandDictionary();
        userFunctions = new HashMap<>();
    }

    public EnvironmentImpl(Environment outer, List<String> params, Expression[] expr) throws Expression.EvaluationTargetException {
        this();
        this.outer = outer;
        for (int i = 0; i < expr.length; i++) {
            userVariables.put(params.get(i), expr[i].eval(outer));
        }
    }

    @Override
    public Map<String, Variable> getUserVars() {
        return userVariables;
    }

    @Override
    public Map<String, Invokable> getUserFunctions() {
        return userFunctions;
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
        if (userVariables.containsKey(name)) {
            return userVariables.get(name);
        } else if (dictionaryVariables.containsKey(name)) {
            return dictionaryVariables.get(name);
        } else if (Objects.isNull(outer)) {
            throw new VariableNotFoundException(name);
        } else {
            return outer.getVariableByName(name);
        }
    }

    @Override
    public Invokable getFunctionByName(String name) throws FunctionNotFoundException {
        if (userFunctions.containsKey(name)) {
            return userFunctions.get(name);
        } else if (dictionaryFunctions.containsKey(name)) {
            return dictionaryFunctions.get(name);
        } else if (Objects.isNull(outer)) {
            throw new FunctionNotFoundException(name);
        } else {
            return outer.getFunctionByName(name);
        }
    }

    @Override
    public Turtle getTurtle() {
        return Objects.isNull(myTurtle) ? outer.getTurtle() : myTurtle;
    }

    @Override
    public CanvasView getCanvas() {
        return myCanvas;
    }

    @Override
    public void setTurtle(Turtle turt) {
        myTurtle = turt;

    }

    @Override
    public void setCanvas(CanvasView canvas) {
        myCanvas = canvas;
    }

    @Override
    public void addUserVariable(String name, Variable var) {
        dictionaryVariables.put(name, var);
        userVariables.put(name, var);
    }

    @Override
    public void addUserFunction(String name, Invokable function) {
        dictionaryFunctions.put(name, function);
        userFunctions.put(name, function);
    }

    private Map<String, Invokable> initCommandDictionary() {
        Map<String, Invokable> commands = new HashMap<>();
        CommandList.getAllCommands().forEach(e -> {
            try {
                commands.put(e.getName(), (Invokable) e.get(null));
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            }
        });
        return commands;
    }
}
