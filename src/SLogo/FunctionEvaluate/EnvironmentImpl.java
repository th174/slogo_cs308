package SLogo.FunctionEvaluate;

import SLogo.FunctionEvaluate.Functions.CommandList;
import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Turtles.Turtle;
import SLogo.View.CanvasView;
import SLogo.View.Sprite.Sprite;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by th174 on 2/16/2017.
 */
public class EnvironmentImpl implements Environment {
    private Map<String, Variable> dictionaryVariables;
    private Map<String, Variable> userVariables;
    private Map<String, Invokable> dictionaryFunctions;
    private Map<String, Invokable> userFunctions;
    private Turtle myTurtle;
    private Sprite mySprite;
    private CanvasView myCanvas;

    public EnvironmentImpl() {
        dictionaryVariables = new HashMap<>();
        userVariables = new HashMap<>();
        dictionaryFunctions = initCommandDictionary();
        userFunctions = new HashMap<>();
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
        if (dictionaryVariables.containsKey(name)) {
            return dictionaryVariables.get(name);
        } else {
            throw new VariableNotFoundException(name);
        }
    }

    @Override
    public Invokable getFunctionByName(String name) throws FunctionNotFoundException {
        if (dictionaryFunctions.containsKey(name)) {
            return dictionaryFunctions.get(name);
        } else {
            throw new FunctionNotFoundException(name);
        }
    }
    
	@Override
	public Turtle getTurtle() {
		return myTurtle;
	}
	
	@Override
	public Sprite getSprite() {
		return mySprite;
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
	public void setSprite(Sprite sprite) {
		mySprite = sprite;		
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