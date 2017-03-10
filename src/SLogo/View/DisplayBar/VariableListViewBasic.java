package SLogo.View.DisplayBar;

import java.util.Map;
import java.util.Observable;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.View.CommandLineView;

public class VariableListViewBasic extends ItemList<TextContainer> {
	private CommandLineView myCommandLineView;
	public VariableListViewBasic(CommandLineView commandLineView) {
		initializeResources();
		myCommandLineView = commandLineView;
	}

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

	@Override
	protected void onClick(TextContainer item) {
		String [] commandInfo = item.getCommand().split(" = ");
		myCommandLineView.setText("MAKEVARIABLE " + commandInfo[0] + " " + commandInfo[1]);
	}

	@Override
	protected void addItem(TextContainer toAddItem) {
		getMyListView().getChildren().add(toAddItem.getView());
		toAddItem.getView().setOnMouseClicked(e -> onClick(toAddItem));
	}
}