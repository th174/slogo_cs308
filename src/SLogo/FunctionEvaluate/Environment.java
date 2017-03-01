package SLogo.FunctionEvaluate;

import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Turtles.Turtle;
import SLogo.View.CanvasView;

import java.util.Map;
import java.util.Observer;
import java.util.Observable;

/**
 * Created by th174 on 2/19/2017.
 */
/**
 * @author Stone Mathers
 * @author th174
 * Created 2/19/2017
 *
 */
public interface Environment{
    /**
     * @return Get all defined user variables
     */
    Map<String, Variable> getUserVars();

    /**
     * @return Get all defined user functions
     */
    Map<String, Invokable> getUserFunctions();

    /**
     * @return Get all variables
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
    Variable getVariableByName(String name) throws VariableNotFoundException;

    /**
     * @param name Name of function
     * @return Named function
     */
    Invokable getFunctionByName(String name) throws FunctionNotFoundException;

    
    /**
     * @return Turtle
     */
    Turtle getTurtle();
    
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
    void addUserFunction(String name, Invokable function);
    
    /**
     * @param turt Turtle to be used
     */
    void setTurtle(Turtle turt);
    
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
