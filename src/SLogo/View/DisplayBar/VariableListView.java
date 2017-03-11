package SLogo.View.DisplayBar;

import java.util.Map;
import java.util.Observable;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.EnvironmentImpl;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.View.CommandLineView;
import SLogo.View.Project;

/**
 * Keeps track of variables
 * @author Alex
 *
 */
public class VariableListView extends ItemList<TextContainer> {
	private CommandLineView myCommandLineView;
	private EnvironmentImpl myEnvironment;
	
	/**
	 * Creates new variableListView
	 * @param project
	 */
	public VariableListView(Project project) {
		initializeResources();
		myCommandLineView = project.getCommandLineView();
		myEnvironment = (EnvironmentImpl) project.getRepl().getEnvironment();
		update(myEnvironment,null);
	}

	/**
	 * Updates content
	 */
	@Override
	public void update(Observable o, Object arg) {
		Environment environment = (Environment) o;
		@SuppressWarnings("rawtypes")
		Map<String, Variable> currentVariableMap = environment.getAllVars();
		getMyListView().getChildren().clear();
		for(String string : currentVariableMap.keySet()){
			addItem(new TextContainer(string + " = " + currentVariableMap.get(string)));
		}
	}

	/**
	 * Sets onClickAction
	 */
	@Override
	protected void onClick(TextContainer item) {
		String [] commandInfo = item.getCommand().split(" = ");
		myCommandLineView.setText("MAKEVARIABLE " + commandInfo[0] + " " + commandInfo[1]);
	}

	/**
	 * Adds item
	 */
	@Override
	protected void addItem(TextContainer toAddItem) {
		getMyListView().getChildren().add(toAddItem.getView());
		toAddItem.getView().setOnMouseClicked(e -> onClick(toAddItem));
	}
}