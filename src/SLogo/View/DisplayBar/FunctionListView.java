// This entire file is part of my masterpiece.
// Alex Salas

package SLogo.View.DisplayBar;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.EnvironmentImpl;
import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.View.CommandLineView;
import SLogo.View.Project;

import java.util.Map;
import java.util.Observable;

/**
 * Keeps track of functions
 *
 * @author Alex
 */
public class FunctionListView extends ItemList<TextContainer> {
    private CommandLineView myCommandLineView;
    private EnvironmentImpl myEnvironment;

    /**
     * Create function list
     *
     * @param project
     */
    public FunctionListView(Project project) {
        initializeResources();
        myCommandLineView = project.getCommandLineView();
        myEnvironment = (EnvironmentImpl) project.getRepl().getUserEnvironment();
        update(myEnvironment, null);
    }

    /**
     * Updates contents
     */
    @Override
    public void update(Observable o, Object arg) {
        Environment environment = (EnvironmentImpl) o;
        Map<String, Invokable> currentVariableMap = environment.getAllFunctions();
        getMyListView().getChildren().clear();
        for (String string : currentVariableMap.keySet()) {
            getMyListView().getChildren().add(new TextContainer(string + " = " + currentVariableMap.get(string)).getView());
        }
    }

    /**
     * Says what to do on click
     */
    @Override
    protected void onClick(TextContainer item) {
        myCommandLineView.setText(item.getCommand());
    }

    /**
     * Add item
     */
    @Override
    protected void addItem(TextContainer toAddItem) {
        getMyListView().getChildren().add(toAddItem.getView());
        toAddItem.getView().setOnMouseClicked(e -> onClick(toAddItem));
    }
}