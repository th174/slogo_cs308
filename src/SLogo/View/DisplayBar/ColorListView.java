package SLogo.View.DisplayBar;

import java.util.Observable;

import SLogo.View.CommandLineView;

public class ColorListView extends ItemList<IndexNode> {
	private CommandLineView myCommandLineView;
	public ColorListView(CommandLineView commandLineView) {
		initializeResources();
		myCommandLineView = commandLineView;
	}

	@Override
	public void update(Observable o, Object arg) {
	}

	@Override
	protected void onClick(IndexNode item) {
		myCommandLineView.setText(item.getCommand());
	}

	@Override
	protected void addItem(IndexNode toAddItem) {
		getMyListView().getChildren().add(toAddItem.getView());
		toAddItem.getView().setOnMouseClicked(e -> onClick(toAddItem));
	}
}
