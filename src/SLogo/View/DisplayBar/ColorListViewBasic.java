package SLogo.View.DisplayBar;

import java.util.Map;
import java.util.Observable;

import SLogo.View.CanvasViewImpl;
import SLogo.View.CommandLineView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColorListViewBasic extends ItemList<IndexNode> {
	private CommandLineView myCommandLineView;
	public ColorListViewBasic(CommandLineView commandLineView) {
		initializeResources();
		myCommandLineView = commandLineView;
		getMyListView().getChildren().add(new TextContainer(getMyResources().getString("ColorTab")).getView());
	}

	@Override
	public void update(Observable o, Object arg) {
		CanvasViewImpl canvasViewImpl = (CanvasViewImpl)o;
		Map<Integer, Color> colorMap = canvasViewImpl.getColorMap();
		getMyListView().getChildren().clear();
		for(Integer i: colorMap.keySet()){
			Rectangle rectangle = new Rectangle(20,20);
			addItem(new IndexNode(i, rectangle));
		}
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
