package SLogo.FileHandling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.Functions.UserFunction;
import SLogo.FunctionEvaluate.Variables.Variable;

/**
 * @author Stone Mathers
 * Created 3/10/2017
 */
public class LibraryWriter implements FileWriter{

	private Environment myEnvironment;
	private Map<String, Variable> varMap;
	private Map<String, Invokable> funcMap;
	private static final String COMMAND_BUNDLE = "resources/files/writing";
	public ResourceBundle myCommands = ResourceBundle.getBundle(COMMAND_BUNDLE);
	private static final String ERROR_BUNDLE = "resources/View/Exceptions";
	public ResourceBundle myErrors = ResourceBundle.getBundle(ERROR_BUNDLE);
	
	public LibraryWriter(Environment env) {
		myEnvironment = env;
		varMap = myEnvironment.getAllVars();
		funcMap = myEnvironment.getLocalFunctions();
	}

	/**
	 * Takes in a file and writes the current user functions and variables to it.
	 * 
	 * @param file File to which library is written.
	 */
	@Override
	public void write(File file){
		Path filePath = Paths.get(file.toURI());
		ArrayList<String> commands = new ArrayList<String>();
		commands.add(myCommands.getString("defaultUse"));
		writeVariables(commands);	
		writeFunctions(commands);
		try {
			Files.write(filePath, commands);
		} catch (IOException e) {
			throw new RuntimeException(String.format(myErrors.getString("FileWritingError"), file.getName()), e);
		}
	}
	
	private void writeVariables(List<String> commands){
		for(String varName: varMap.keySet()){
			commands.add((String.format(myCommands.getString("Make"), varName, varMap.get(varName))));
		}
	}
	
	private void writeFunctions(List<String> commands){
		for(String funcName: funcMap.keySet()){
			if(funcMap.get(funcName) instanceof UserFunction){
				commands.add((String.format(myCommands.getString("To"), funcMap.get(funcName).toString())));
			}
		}
	}
}
