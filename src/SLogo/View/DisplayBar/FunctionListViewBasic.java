package SLogo.View.DisplayBar;

import java.util.Map;
import java.util.Observable;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.View.CommandLineView;

/**
 * 
 * @author Alex
 *
 */
public class FunctionListViewBasic extends ItemList<TextContainer> {
	private CommandLineView myCommandLineView;
	public FunctionListViewBasic(CommandLineView commandLineView) {
		initializeResources();
		myCommandLineView = commandLineView;
		getMyListView().getChildren().add(new TextContainer(getMyResources().getString("FunctionTab")).getView());
	}

	@Override
	public void update(Observable o, Object arg) {
		Environment environment = (Environment) o;
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
}