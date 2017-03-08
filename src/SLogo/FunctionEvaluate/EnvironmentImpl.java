package SLogo.FunctionEvaluate;

import SLogo.FunctionEvaluate.Functions.CommandList;
import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Turtles.NewTurtle;
import SLogo.View.CanvasView;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by th174 on 2/16/2017.
 */
public class EnvironmentImpl extends Observable implements Environment {
    public static final Environment GLOBAL_ENVIRONMENT = new EnvironmentImpl();
    private Collection<Observer> observers;
    private Environment outer;
    private Map<String, Variable> scopeVariables;
    private Map<String, Invokable> scopeFunctions;
    private Map<Integer, NewTurtle> myTurtles;
    private List<NewTurtle> myActiveTurtles;
    private CanvasView myCanvas;

    private EnvironmentImpl() {
        scopeVariables = initVariableDictonary();
        scopeFunctions = initCommandDictionary();
        outer = null;
        observers = new ArrayList<>();
    }

    public EnvironmentImpl(Environment outer, Collection<NewTurtle> myTurtles) {
        this.outer = outer;
        scopeFunctions = new HashMap<>();
        scopeVariables = new HashMap<>();
        observers = new ArrayList<>();
        this.myTurtles = myTurtles.stream().collect(Collectors.toMap(NewTurtle::id, t -> t));
        this.myActiveTurtles = new ArrayList<>(this.myTurtles.values());
    }

    public EnvironmentImpl(Environment outer, Predicate<NewTurtle> turtleFilter) {
        this(outer, outer.getAllTurtles());
        filterTurtles(turtleFilter);
    }

    public EnvironmentImpl(Environment outer, List<Integer> turtleIDs) {
        this(outer, outer.getAllTurtles());
        selectTurtles(turtleIDs);
    }

    public EnvironmentImpl(Environment outer, List<String> params, Expression... expr) throws Expression.EvaluationTargetException {
        this(outer, outer.getTurtles());
        for (int i = 0; i < expr.length; i++) {
            scopeVariables.put(params.get(i), expr[i].eval(outer));
        }
    }

    @Override
    public Map<String, Variable> getLocalVars() {
        return Collections.unmodifiableMap(scopeVariables);
    }

    @Override
    public Map<String, Invokable> getLocalFunctions() {
        return Collections.unmodifiableMap(scopeFunctions);
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
    public Variable getVariableByName(String name) {
        if (scopeVariables.containsKey(name)) {
            return scopeVariables.get(name);
        } else if (scopeVariables.containsKey(name)) {
            return scopeVariables.get(name);
        } else if (Objects.isNull(outer)) {
            return Variable.fromString(name);
        } else {
            return outer.getVariableByName(name);
        }
    }

    @Override
    public Invokable getFunctionByName(String name) {
        if (scopeFunctions.containsKey(name)) {
            return scopeFunctions.get(name);
        } else if (scopeFunctions.containsKey(name)) {
            return scopeFunctions.get(name);
        } else if (Objects.isNull(outer)) {
            return null;
        } else {
            return outer.getFunctionByName(name);
        }
    }

    @Override
    public List<NewTurtle> getTurtles() {
        return Collections.unmodifiableList(myActiveTurtles);
    }

    @Override
    public Collection<NewTurtle> getAllTurtles() {
        return myTurtles.values();
    }

    @Override
    public CanvasView getCanvas() {
        return myCanvas;
    }

    @Override
    public void filterTurtles(Predicate<NewTurtle> filter) {
        myActiveTurtles = myTurtles.values().stream().sorted(Comparator.comparingInt(NewTurtle::id)).filter(filter).collect(Collectors.toList());
    }

    @Override
    public void selectTurtles(List<Integer> turtleIDs) {
        myActiveTurtles = turtleIDs.stream().map(id -> {
            if (!myTurtles.containsKey(id)){
                my
            }
            return myTurtles.get(id);
        }).collect(Collectors.toList());
    }

    @Override
    public void setCanvas(CanvasView canvas) {
        myCanvas = canvas;
    }

    @Override
    public void addUserVariable(String name, Variable var) {
        scopeVariables.put(name, var);
        scopeVariables.put(name, var);
        notifyObservers();
    }

    @Deprecated
    @Override
    public void addUserFunction(String name, Invokable function) {
        scopeFunctions.put(name, function);
        scopeFunctions.put(name, function);
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
        observers.forEach(o -> o.update(this, new Object[]{scopeVariables, scopeFunctions}));
    }
}
