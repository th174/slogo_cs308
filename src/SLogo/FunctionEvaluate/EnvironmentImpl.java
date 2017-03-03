package SLogo.FunctionEvaluate;

import SLogo.FunctionEvaluate.Functions.CommandList;
import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Turtles.NewTurtle;
import SLogo.Turtles.Turtle;
import SLogo.View.CanvasView;

import java.util.*;

/**
 * Created by th174 on 2/16/2017.
 */
public class EnvironmentImpl extends Observable implements Environment {
    public static final Environment GLOBAL_ENVIRONMENT = new EnvironmentImpl();

    private Collection<Observer> observers;
    private Environment outer;
    private Map<String, Variable> userVariables;
    private Map<String, Invokable> userFunctions;
    private NewTurtle myTurtle;
    private CanvasView myCanvas;

    private EnvironmentImpl() {
        userVariables = initVariableDictonary();
        userFunctions = initCommandDictionary();
        outer = null;
        observers = new ArrayList<>();
    }

    public EnvironmentImpl(Environment outer, NewTurtle myTurtle) {
        this.outer = outer;
        userFunctions = new HashMap<>();
        userVariables = new HashMap<>();
        observers = new ArrayList<>();
        this.myTurtle = myTurtle;
    }

    public EnvironmentImpl(Environment outer, List<String> params, Expression... expr) throws Expression.EvaluationTargetException {
        this(outer, outer.getTurtle());
        for (int i = 0; i < expr.length; i++) {
            userVariables.put(params.get(i), expr[i].eval(outer));
        }
    }

    @Override
    public Environment outer() {
        return outer;
    }

    @Override
    public Map<String, Variable> getLocalVars() {
        return Collections.unmodifiableMap(userVariables);
    }

    @Override
    public Map<String, Invokable> getLocalFunctions() {
        return Collections.unmodifiableMap(userFunctions);
    }

    @Override
    public Map<String, Variable> getAllVars() {
        if (Objects.isNull(outer)) {
            return Collections.unmodifiableMap(getLocalVars());
        } else {
            HashMap<String, Variable> vars = new HashMap<>(getLocalVars());
            vars.putAll(outer.getLocalVars());
            return Collections.unmodifiableMap(vars);
        }
    }

    @Override
    public Map<String, Invokable> getAllFunctions() {
        if (Objects.isNull(outer)) {
            return Collections.unmodifiableMap(getLocalFunctions());
        } else {
            HashMap<String, Invokable> funcs = new HashMap<>(getLocalFunctions());
            funcs.putAll(outer.getLocalFunctions());
            return Collections.unmodifiableMap(funcs);
        }
    }

    @Override
    public Variable getVariableByName(String name) throws VariableNotFoundException {
        if (userVariables.containsKey(name)) {
            return userVariables.get(name);
        } else if (userVariables.containsKey(name)) {
            return userVariables.get(name);
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
        } else if (userFunctions.containsKey(name)) {
            return userFunctions.get(name);
        } else if (Objects.isNull(outer)) {
            throw new FunctionNotFoundException(name);
        } else {
            return outer.getFunctionByName(name);
        }
    }

    @Override
    public NewTurtle getTurtle() {
        return Objects.isNull(myTurtle) ? outer.getTurtle() : myTurtle;
    }

    @Override
    public CanvasView getCanvas() {
        return myCanvas;
    }

    @Override
    public void setTurtle(NewTurtle turtle) {
        myTurtle = turtle;
    }

    @Override
    public void setCanvas(CanvasView canvas) {
        myCanvas = canvas;
    }

    @Override
    public void addUserVariable(String name, Variable var) {
        userVariables.put(name, var);
        userVariables.put(name, var);
        notifyObservers();
    }

    @Override
    public void addUserFunction(String name, Invokable function) {
        userFunctions.put(name, function);
        userFunctions.put(name, function);
        notifyObservers();
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

    private Map<String, Variable> initVariableDictonary() {
        Map<String, Variable> variables = new HashMap<>();
        Variable.getPredefinedVariables().forEach(e -> {
            try {
                variables.put(e.getName(), (Variable) e.get(null));
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            }
        });
        return variables;
    }

    /**
     * Add an object as a listener
     *
     * @author Riley Nisbet
     */
    public void addObserver(Observer o) {
        observers.add(o);
    }

    /**
     * Remove a listener
     *
     * @author Riley Nisbet
     */
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    /**
     * Tell all listeners that something has changed
     *
     * @author Riley Nisbet
     */
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(this, new Object[]{userVariables, userFunctions});
        }
    }
}
