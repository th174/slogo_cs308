package SLogo.View.DisplayBar;

import java.util.Map;
import java.util.Observable;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.View.CommandLineView;

public class ColorListViewBasic extends ItemList<IndexNode> {
	private CommandLineView myCommandLineView;
	public ColorListViewBasic(CommandLineView commandLineView) {
		initializeResources();
		myCommandLineView = commandLineView;
		getMyListView().getChildren().add(new TextContainer(getMyResources().getString("ColorTab")).getView());
	}

	@Override
	public void update(Observable o, Object arg) {
//		Environment environment = (Environment) o;
//		@SuppressWarnings("rawtypes")
//		Map<String, Variable> currentVariableMap = environment.getAllVars();
//		getMyListView().getChildren().clear();
//		for(String string : currentVariableMap.keySet()){
//			getMyListView().getChildren().add(new TextContainer(string + " = " + currentVariableMap.get(string)).getView());
//		}
	}

	@Override
	protected void onClick(IndexNode item) {
		myCommandLineView.setText(item.getCommand());
	}
}
