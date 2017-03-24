package SLogo.FunctionEvaluate;

import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Turtles.Turtle;
import javafx.collections.ObservableMap;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * The environment provides dynamic scoping for the SLogo language, and holds all pre-defined and user-defined functions, methods, and turtles.
 *
 * @author Created by th174 on 2/19/2017
 */
public interface Environment {

    /**
     * @return Outer environment enclosing this
     */
    Environment outer();

    /**
     * @return Get all defined variables in current most local scope
     */
    Map<String, Variable> getLocalVars();

    /**
     * @return Get all defined functions in current most local scope
     */
    Map<String, Invokable> getLocalFunctions();

    /**
     * @return Get all variables currently in scope
     */
    Map<String, Variable> getAllVars();

    /**
     * @return Get all defined functions currently in scope, except pre-defined functions
     */
    Map<String, Invokable> getAllFunctions();

    /**
     * @param name Name of variable
     * @return Named variable, or null if variable not found
     */
    Variable getVariableByName(String name);

    /**
     * @param name Name of function
     * @return Named function, or null if function not found
     */
    Invokable getFunctionByName(String name);

    /**
     * @return All current active turtles
     */
    List<Turtle> getActiveTurtleList();

    /**
     * @return All defined turtles, including inactive turtles
     */
    ObservableMap<Integer, Turtle> getAllTurtles();

    /**
     * Adds a variable to the local scope by name
     *
     * @param name Name of variable
     * @param var  Variable
     */
    void addUserVariable(String name, Variable var);

    /**
     * Adds a function to the local scope by name
     *
     * @param name     Name of function
     * @param function Function
     */
    void addUserFunction(String name, Invokable function);

    /**
     * Allows filtering active turtles by predicate.
     *
     * @param filter Condition that selects active turtles
     */
    void filterTurtles(Predicate<Turtle> filter);

    /**
     * Allows filtering active turtle by ID. If a turtle with the ID does not exist, it will be created
     *
     * @param turtleIDs Ids of turtles to be set active
     */
    void selectTurtles(List<Integer> turtleIDs);

    /**
     * Clears and resets all but 1 turtle
     * @return distance traveled by the 1 turtle that wasn't cleared
     */
    double clearTurtles();
}
