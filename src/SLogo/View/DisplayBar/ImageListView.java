package SLogo.View.DisplayBar;

import java.util.Map;
import java.util.Observable;

import SLogo.View.CanvasViewImpl;
import SLogo.View.CommandLineView;
import SLogo.View.Project;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageListView extends ItemList<IndexNode> {
	private CommandLineView myCommandLineView;
	private CanvasViewImpl myCanvasView;
	
	public ImageListView(Project project) {
		initializeResources();
		myCommandLineView = project.getCommandLineView();
		myCanvasView = project.getCanvasView();
		update(myCanvasView,null);
		getMyListView().setSpacing(Integer.parseInt(getMyResources().getString("ItemSpacing")));
		getMyListView().setPadding(new Insets(Integer.parseInt(getMyResources().getString("ItemSpacing"))));
	}

	@Override
	public void update(Observable o, Object arg) {
		Map<Integer, Image> imageMap = myCanvasView.getImageMap();
		getMyListView().getChildren().clear();
		for(Integer i: imageMap.keySet()){
			ImageView imageView = new ImageView(imageMap.get(i));
			imageView.setFitWidth(20);
			imageView.setFitHeight(20);
			addItem(new IndexNode(i, imageView));
		}
	}

	@Override
	protected void onClick(IndexNode item) {
		myCommandLineView.setText("SETSHAPE " + item.getCommand());
	}

	@Override
	protected void addItem(IndexNode toAddItem) {
		getMyListView().getChildren().add(toAddItem.getView());
		toAddItem.getView().setOnMouseClicked(e -> onClick(toAddItem));
	}
}
