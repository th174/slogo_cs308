package SLogo.View.DisplayBar;

import java.util.Observable;

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
//		@SuppressWarnings("unchecked")
//		Map<String, Invokable> updatedFunctions = (Map<String, Invokable>)((Object[])arg)[0];
//		updateContents(updatedFunctions.keySet());
	}

	@Override
	protected void onClick(TextContainer item) {
		myCommandLineView.setText(item.getCommand());
	}
}