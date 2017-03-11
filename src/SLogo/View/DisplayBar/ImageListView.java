package SLogo.View.DisplayBar;

import java.util.Map;
import java.util.Observable;

import SLogo.View.CanvasViewImpl;
import SLogo.View.CommandLineView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageListView extends ItemList<IndexNode> {
	private CommandLineView myCommandLineView;
	public ImageListView(CommandLineView commandLineView) {
		initializeResources();
		myCommandLineView = commandLineView;
	}

	@Override
	public void update(Observable o, Object arg) {
		CanvasViewImpl canvasViewImpl = (CanvasViewImpl)o;
		Map<Integer, Image> imageMap = canvasViewImpl.getImageMap();
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
