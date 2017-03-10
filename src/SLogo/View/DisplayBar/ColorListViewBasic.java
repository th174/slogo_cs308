package SLogo.View.DisplayBar;

import java.util.Observable;

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
//		@SuppressWarnings("unchecked")
//		Map<String, Invokable> updatedFunctions = (Map<String, Invokable>)((Object[])arg)[0];
//		updateContents(updatedFunctions.keySet());
	}

	@Override
	protected void onClick(IndexNode item) {
		myCommandLineView.setText(item.getCommand());
	}
}
