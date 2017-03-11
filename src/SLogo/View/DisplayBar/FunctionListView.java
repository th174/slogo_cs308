package SLogo.View.DisplayBar;

import java.util.Map;
import java.util.Observable;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.EnvironmentImpl;
import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.View.CommandLineView;

/**
 * 
 * @author Alex
 *
 */
public class FunctionListView extends ItemList<TextContainer> {
	private CommandLineView myCommandLineView;
	private EnvironmentImpl myEnvironment;
	
	public FunctionListView(EnvironmentImpl environment, CommandLineView commandLineView) {
		initializeResources();
		myEnvironment = environment;
		myCommandLineView = commandLineView;
		update(myEnvironment,null);
	}

	@Override
	public void update(Observable o, Object arg) {
		Environment environment = (EnvironmentImpl) o;
		Map<String, Invokable> currentVariableMap = environment.getAllFunctions();
		getMyListView().getChildren().clear();
		for(String string : currentVariableMap.keySet()){
			getMyListView().getChildren().add(new TextContainer(string + " = " + currentVariableMap.get(string)).getView());
		}
	}

	@Override
	protected void onClick(TextContainer item) {
		myCommandLineView.setText(item.getCommand());
	}

	@Override
	protected void addItem(TextContainer toAddItem) {
		getMyListView().getChildren().add(toAddItem.getView());
		toAddItem.getView().setOnMouseClicked(e -> onClick(toAddItem));
	}	
}