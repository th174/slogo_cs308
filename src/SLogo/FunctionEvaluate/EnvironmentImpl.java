package SLogo.FunctionEvaluate;

import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Parser;
import SLogo.Turtles.ObservableTurtle;
import SLogo.Turtles.Turtle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 * An implementation of an Environment
 *
 * @author Created by th174 on 2/16/2017.
 */
public class EnvironmentImpl extends Observable implements Environment {
	private static final int DEFAULT_TURTLE_ID = 1;
	private final Environment outer;
	private final Map<String, Variable> inScopeVariables;
	private final Map<String, Invokable> inScopeFunctions;
	private final ObservableMap<Integer, Turtle> myTurtles;
	private List<Turtle> myActiveTurtles;

	/**
	 * A constructor for a top level environment, using default values
	 */
	public EnvironmentImpl() {
		this(initCommandDictionary(), initVariableDictonary());
		selectTurtles(Collections.singletonList(DEFAULT_TURTLE_ID));
	}

	/**
	 * A constructor for a top level environment
	 *
	 * @param functions Initial pre-defined commands
	 * @param variables Initial pre-defined variables
	 */
	public EnvironmentImpl(Map<String, Invokable> functions, Map<String, Variable> variables) {
		inScopeFunctions = functions;
		inScopeVariables = variables;
		this.outer = null;
		myTurtles = FXCollections.observableHashMap();
	}

	/**
	 * Constructor for a sub-environment that inherits its parent's turtles
	 *
	 * @param outer Parent environment
	 */
	public EnvironmentImpl(Environment outer) {
		this.outer = outer;
		inScopeFunctions = new HashMap<>();
		inScopeVariables = new HashMap<>();
		myTurtles = null;
		notifyObservers();
		this.myActiveTurtles = new ArrayList<>(outer.getActiveTurtleList());
	}

	/**
	 * A constructor for a sub-environment that allows selecting turtles by ID
	 *
	 * @param outer     Parent Environment
	 * @param turtleIDs Active turtle IDs
	 */
	public EnvironmentImpl(Environment outer, List<Integer> turtleIDs) {
		this(outer);
		selectTurtles(turtleIDs);
	}

	@Override
	public Environment outer() {
		return outer;
	}

	@Override
	public Map<String, Variable> getLocalVars() {
		return Collections.unmodifiableMap(inScopeVariables.entrySet().stream().filter(e -> !e.getKey().matches(Parser.REGEX.getString("Hidden"))).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
	}

	@Override
	public Map<String, Invokable> getLocalFunctions() {
		return Collections.unmodifiableMap(inScopeFunctions.entrySet().stream().filter(e -> !e.getKey().matches(Parser.REGEX.getString("Hidden"))).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
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
		if (Objects.isNull(outer.outer())) {
			return Collections.unmodifiableMap(getLocalFunctions());
		} else {
			HashMap<String, Invokable> funcs = new HashMap<>(outer.getLocalFunctions());
			funcs.putAll(getLocalFunctions());
			return Collections.unmodifiableMap(funcs);
		}
	}

	@Override
	public Variable getVariableByName(String name) {
		if (inScopeVariables.containsKey(name)) {
			return inScopeVariables.get(name);
		} else if (inScopeVariables.containsKey(name)) {
			return inScopeVariables.get(name);
		} else if (Objects.isNull(outer)) {
			return null;
		} else {
			return outer.getVariableByName(name);
		}
	}

	@Override
	public Invokable getFunctionByName(String name) {
		if (inScopeFunctions.containsKey(name)) {
			return inScopeFunctions.get(name);
		} else if (inScopeFunctions.containsKey(name)) {
			return inScopeFunctions.get(name);
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
	public double clearTurtles() {
		getAllTurtles().keySet().retainAll(Collections.singleton(DEFAULT_TURTLE_ID));
		return getAllTurtles().get(DEFAULT_TURTLE_ID).reset();
	}

	@Override
	public void addUserVariable(String name, Variable var) {
		inScopeVariables.put(name, var);
		notifyObservers();
	}

	@Override
	public void addUserFunction(String name, Invokable function) {
		inScopeFunctions.put(name, function);
		notifyObservers();
	}

	private static Map<String, Invokable> initCommandDictionary() {
		Map<String, Invokable> commands = new HashMap<>(PredefinedCommandList.getAllCommands());
		commands.putAll(ResourceBundle.getBundle("resources/AdditionalFunctions").keySet().stream().collect(Collectors.toMap(String::toUpperCase, e -> {
			try {
				return (Invokable) Class.forName("SLogo.FunctionEvaluate.Functions.DefinedFunctions." + e).newInstance();
			} catch (ClassCastException ignored) {
				return null;
			} catch (InstantiationException | ClassNotFoundException | IllegalAccessException e1) {
				throw new Error(e1);
			}
		})));
		return commands;
	}

	private static Map<String, Variable> initVariableDictonary() {
		return Variable.getPredefinedVariables();
	}

	@Override
	public void notifyObservers() {
		setChanged();
		super.notifyObservers();
	}
}
