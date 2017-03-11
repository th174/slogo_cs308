package SLogo.View.DisplayBar;

import java.util.Map;
import java.util.Observable;

import SLogo.View.CanvasViewImpl;
import SLogo.View.CommandLineView;
import SLogo.View.Project;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColorListView extends ItemList<IndexNode> {
	private CommandLineView myCommandLineView;
	private CanvasViewImpl myCanvasView;
	
	public ColorListView(Project project) {
		initializeResources();
		myCommandLineView = project.getCommandLineView();
		myCanvasView = project.getCanvasView();
		update(myCanvasView, null);
		getMyListView().setSpacing(Integer.parseInt(getMyResources().getString("ItemSpacing")));
		getMyListView().setPadding(new Insets(Integer.parseInt(getMyResources().getString("ItemSpacing"))));
	}

	@Override
	public void update(Observable o, Object arg) {
		Map<Integer, Color> colorMap = myCanvasView.getColorMap();
		getMyListView().getChildren().clear();
		for(Integer i: colorMap.keySet()){
			Rectangle rectangle = new Rectangle(20,20);
			rectangle.setFill(colorMap.get(i));
			addItem(new IndexNode(i, rectangle));
		}
	}

	@Override
	protected void onClick(IndexNode item) {
		myCommandLineView.setText("SETPENCOLOR " + item.getCommand());
	}

	@Override
	protected void addItem(IndexNode toAddItem) {
		getMyListView().getChildren().add(toAddItem.getView());
		toAddItem.getView().setOnMouseClicked(e -> onClick(toAddItem));
	}
}
