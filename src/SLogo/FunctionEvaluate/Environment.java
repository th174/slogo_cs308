package SLogo.FunctionEvaluate;

import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Turtles.Turtle;

import java.util.Map;

/**
 * Created by th174 on 2/19/2017.
 */
/**
 * @author Stone Mathers
 *
 */
public interface Environment {
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
    Variable getVariableByName(String name);

    /**
     * @param name Name of function
     * @return Named function
     */
    Invokable getFunctionByName(String name);
    
    /**
     * @return Turtle
     */
    public Turtle getTurtle();


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
    public void setTurtle(Turtle turt);
}
