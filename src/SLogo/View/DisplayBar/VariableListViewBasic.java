package SLogo.View.DisplayBar;

import java.util.Observable;

import SLogo.View.CommandLineView;

public class VariableListViewBasic extends ItemList<TextContainer> {
	private CommandLineView myCommandLineView;
	public VariableListViewBasic(CommandLineView commandLineView) {
		initializeResources();
		myCommandLineView = commandLineView;
		getMyListView().getChildren().add(new TextContainer(getMyResources().getString("VariableTab")).getView());
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