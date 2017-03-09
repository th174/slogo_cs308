package SLogo.View.DisplayBar;

import java.util.Map;
import java.util.Observable;

import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.View.CommandLineView;

/**
 * 
 * @author Alex
 *
 */
public class FunctionListViewBasic extends MenuItemBox {
	
	public FunctionListViewBasic(CommandLineView commandLineView) {
		super(commandLineView);
	}

	@Override
	public void update(Observable o, Object arg) {
		@SuppressWarnings("unchecked")
		Map<String, Invokable> updatedFunctions = (Map<String, Invokable>)((Object[])arg)[1];
		updateContents(updatedFunctions.keySet());
	}

}
