package SLogo.View.DisplayBar;

import java.util.Map;
import java.util.Observable;

import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.View.CommandLineView;

public class VariableListViewBasic extends MenuItemBox {

	public VariableListViewBasic(CommandLineView commandLineView) {
		super(commandLineView);
	}

	@Override
	public void update(Observable o, Object arg) {
		@SuppressWarnings("unchecked")
		Map<String, Invokable> updatedFunctions = (Map<String, Invokable>)((Object[])arg)[0];
		updateContents(updatedFunctions.keySet());
	}
}
