package SLogo.FileHandling;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.FunctionEvaluate.Functions.UserFunction;
import SLogo.FunctionEvaluate.Variables.Variable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Library writer takes in an Environment at instantiation and writes
 * its user-generated functions and variables to a file. This is done
 * by creating a standard text file containing the commands necessary
 * to create these functions and variables. Thus, when a file of this
 * format is loaded into a project, it must simply be run to recreate
 * all functions and variables.
 *
 * @author Stone Mathers
 *         Created 3/10/2017
 */
public class LibraryWriter implements FileWriter {

    private static final String COMMAND_BUNDLE = "resources/files/writing";
    private static final String ERROR_BUNDLE = "resources/View/Exceptions";
    public ResourceBundle myCommands;
    public ResourceBundle myErrors;
    private Environment myEnvironment;
    private Map<String, Variable> varMap;
    private Map<String, Invokable> funcMap;

    /**
     * @param env containing the variables and functions that are desired to be written.
     */
    public LibraryWriter(Environment env) {
        myEnvironment = env;
        varMap = myEnvironment.getLocalVars();
        funcMap = myEnvironment.getLocalFunctions();
        initializeResources();
    }

    private void initializeResources() {
        myCommands = ResourceBundle.getBundle(COMMAND_BUNDLE);
        myErrors = ResourceBundle.getBundle(ERROR_BUNDLE);
    }

    /**
     * Takes in a file and writes to it the commands necessary to regenerate
     * all current user functions and variables.
     *
     * @param file File to which library is written.
     */
    @Override
    public void write(File file) {
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

    private void writeVariables(List<String> commands) {
        for (String varName : varMap.keySet()) {
            commands.add((String.format(myCommands.getString("Make"), varName, varMap.get(varName))));
        }
    }

    private void writeFunctions(List<String> commands) {
        for (String funcName : funcMap.keySet()) {
            if (funcMap.get(funcName) instanceof UserFunction) {
                commands.add((String.format(myCommands.getString("To"), funcMap.get(funcName).toString())));
            }
        }
    }
}
