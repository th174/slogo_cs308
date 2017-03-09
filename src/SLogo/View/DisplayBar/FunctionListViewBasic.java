package SLogo.View.DisplayBar;

import java.util.Map;
import java.util.Observable;
import java.util.ResourceBundle;

import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.View.CommandLineView;

/**
 * 
 * @author Alex
 *
 */
public class FunctionListViewBasic extends TextItemList {

	private final static String RESOURCES_PATH = "resources/View/";
	private final static String PROPERTIES_FILENAME = "ItemList";
	private ResourceBundle myResources;
	
	public FunctionListViewBasic(CommandLineView commandLineView) {
		super(commandLineView);
		initializeResources();
		addItem(myResources.getString("FunctionTab"));
	}

	@Override
	public void update(Observable o, Object arg) {
		@SuppressWarnings("unchecked")
		Map<String, Invokable> updatedFunctions = (Map<String, Invokable>)((Object[])arg)[1];
		updateContents(updatedFunctions.keySet());
	}

	private void initializeResources() {
		myResources = ResourceBundle.getBundle(RESOURCES_PATH + PROPERTIES_FILENAME);
	}
}
