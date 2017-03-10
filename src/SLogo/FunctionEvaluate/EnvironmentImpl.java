package SLogo.FunctionEvaluate;

import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Turtles.ObservableTurtle;
import SLogo.Turtles.Turtle;
import SLogo.View.CanvasView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by th174 on 2/16/2017.
 */
public class EnvironmentImpl extends Observable implements Environment {
    public static final Environment GLOBAL_ENVIRONMENT = new EnvironmentImpl();
    private Environment outer;
    private Map<String, Variable> scopeVariables;
    private Map<String, Invokable> scopeFunctions;
    private ObservableMap<Integer, Turtle> myTurtles;
    private List<Turtle> myActiveTurtles;
    private CanvasView myCanvas;

    private EnvironmentImpl() {
        scopeVariables = initVariableDictonary();
        scopeFunctions = initCommandDictionary();
        myTurtles = FXCollections.observableHashMap();
        outer = null;
    }

    private EnvironmentImpl(Environment outer) {
        this.outer = outer;
        scopeFunctions = new HashMap<>();
        scopeVariables = new HashMap<>();
        myTurtles = null;
        notifyObservers();
        this.myActiveTurtles = new ArrayList<>(outer.getAllTurtles().values());
    }

    public EnvironmentImpl(Environment outer, Predicate<Turtle> turtleFilter) {
        this(outer);
        filterTurtles(turtleFilter);
    }

    public EnvironmentImpl(Environment outer, List<Integer> turtleIDs) {
        this(outer);
        selectTurtles(turtleIDs);
    }

    public EnvironmentImpl(Environment outer, List<String> params, Expression... expr) {
        this(outer);
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
            HashMap<String, Variable> vars = new HashMap<>(outer.getLocalVars());
            vars.putAll(getLocalVars());
            return Collections.unmodifiableMap(vars);
        }
    }

    @Override
    public Map<String, Invokable> getAllFunctions() {
        if (Objects.isNull(outer)) {
            return Collections.unmodifiableMap(getLocalFunctions());
        } else {
            HashMap<String, Invokable> funcs = new HashMap<>(outer.getLocalFunctions());
            funcs.putAll(getLocalFunctions());
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
    public List<Turtle> getActiveTurtleList() {
        return Collections.unmodifiableList(myActiveTurtles);
    }

    @Override
    public ObservableMap<Integer, Turtle> getAllTurtles() {
        return Objects.nonNull(myTurtles) ? myTurtles : outer.getAllTurtles();
    }

    @Override
    public CanvasView getCanvas() {
        return myCanvas;
    }

    @Override
    public void filterTurtles(Predicate<Turtle> filter) {
        myActiveTurtles = getAllTurtles().values().stream().sorted(Comparator.comparingInt(Turtle::id)).filter(filter).collect(Collectors.toList());
    }

    @Override
    public void selectTurtles(List<Integer> turtleIDs) {
        myActiveTurtles = turtleIDs.stream().map(id -> {
            if (!getAllTurtles().containsKey(id)) {
                getAllTurtles().put(id, new ObservableTurtle(id));
            }
            return getAllTurtles().get(id);
        }).collect(Collectors.toList());
    }

    @Override
    public void setCanvas(CanvasView canvas) {
        myCanvas = canvas;
    }

    @Override
    public void addUserVariable(String name, Variable var) {
        scopeVariables.put(name, var);
        notifyObservers();
    }

    @Override
    public void addUserFunction(String name, Invokable function) {
        scopeFunctions.put(name, function);
        scopeFunctions.put(name, function);
        notifyObservers();
    }

    private Map<String, Invokable> initCommandDictionary() {
        try {
            return PredefinedCommandList.getAllCommands();
        } catch (IllegalAccessException i) {
            throw new NullPointerException();
        }
    }

    private Map<String, Variable> initVariableDictonary() {
        return Variable.getPredefinedVariables();
    }
    
    @Override
    public void notifyObservers() {
        setChanged();
        super.notifyObservers();
    }
}
