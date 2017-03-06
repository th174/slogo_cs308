package SLogo.FunctionEvaluate;

import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Turtles.NewTurtle;
import SLogo.View.CanvasView;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Created by th174 on 2/19/2017.
 */

/**
 * @author Stone Mathers
 * @author th174
 *         Created 2/19/2017
 */
public interface Environment {

    /**
     * @return Get unmodifiable view of all defined user variables
     */
    Map<String, Variable> getLocalVars();

    /**
     * @return Get unmodifiable view all defined user functions
     */
    @Deprecated
    Map<String, Invokable> getLocalFunctions();

    /**
     * @return Get unmodifiable view all variables
     */
    Map<String, Variable> getAllVars();

    /**
     * @return Get all functions
     */
    Map<String, Invokable> getAllFunctions();

    /**
     * @param name Name of variable
     * @return Named variable
     */
    Variable getVariableByName(String name) ;

    /**
     * @param name Name of function
     * @return Named function
     */
    @Deprecated
    Invokable getFunctionByName(String name) ;

    /**
     * @return All active turtles
     */
    List<NewTurtle> getTurtles();

    /**
     * @return All turtles, including inactive turtles
     */
    List<NewTurtle> getAllTurtles();

    /**
     * @return CanvasView
     */
    CanvasView getCanvas();

    /**
     * @param name Name of variable
     * @param var  Variable
     */
    void addUserVariable(String name, Variable var);

    /**
     * @param name     Name of function
     * @param function Function
     */
    @Deprecated
    void addUserFunction(String name, Invokable function);

    /**
     * @param filter Condition that selects active turtles
     */
    void filterTurtles(Predicate<NewTurtle> filter);

    /**
     * @param canvas CanvasView to be used
     */
    void setCanvas(CanvasView canvas);

    class VariableNotFoundException extends RuntimeException {
        public VariableNotFoundException(String variableName) {
            super("Variable \'" + variableName + "\' is unbound.");
        }
    }

    class FunctionNotFoundException extends RuntimeException {
        public FunctionNotFoundException(String functionName) {
            super("Function \'" + functionName + "\' is undefined.");
        }
    }
}
