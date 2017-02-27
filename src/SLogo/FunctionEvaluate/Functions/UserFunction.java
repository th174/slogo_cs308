package SLogo.FunctionEvaluate.Functions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import SLogo.FunctionEvaluate.Functions.Invokable.UnexpectedArgumentException;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.RecursiveExpression;

/**
 * Holds and executes a user-generated function.
 * This is a read-only class.
 * 
 * @author Stone Mathers
 * Created 2/27/2017
 */
public class UserFunction implements Invokable {

	private String myName;
	private List<Variable> myVariableNames;
	private List<RecursiveExpression> myCommands;
	private int ExpectedNumberOfArguments;
	
	public UserFunction(String name) {
		this(name, new ArrayList<Variable>(), new ArrayList<RecursiveExpression>());
	}
	
	public UserFunction(String name, List<Variable> variables, List<RecursiveExpression> commands){
		myName = name;
		myVariableNames = variables;
		myCommands = commands;
		ExpectedNumberOfArguments = myVariableNames.size();
	}

	@Override
	public Variable invoke(String[] flags, Variable[] args, RecursiveExpression[] expr, RecursiveExpression[] alt) {
		if (args.length != ExpectedNumberOfArguments) {
            throw new UnexpectedArgumentException(ExpectedNumberOfArguments, args.length);
        } else {
            return operation(args);
        }
	}
	
	private Variable operation(Variable[] args){
		HashMap<String, Variable> values = new HashMap<String, Variable>();
		fillMap(values, args);
		Variable finalValue = new NumberVariable(0);
		
		for(RecursiveExpression expr: myCommands){
			//TODO Take into account variables
		}
		
		return finalValue;
	}
	
	/**
	 * @return Function name
	 */
	public String getName(){
		return myName;
	}
	
	/**
	 * @return List of variable names
	 */
	public List<Variable> getVariableNames(){
		return myVariableNames;
	}
	
	/**
	 * @return List of commands
	 */
	public List<RecursiveExpression> getCommands(){
		return myCommands;
	}
	
	private void fillMap(Map<String, Variable> map, Variable[] args){
		for(int i = 0; i < ExpectedNumberOfArguments; i++){
			map.put(myVariableNames.get(i).toString(), args[i]);
		}
	}

}
