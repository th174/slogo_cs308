// This entire file is part of my masterpiece.
// Alex Salas

package SLogo.View.DisplayBar;

import SLogo.View.CanvasViewImpl;
import SLogo.View.CommandLineView;
import SLogo.View.Project;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Map;
import java.util.Observable;

/**
 * Keeps track of Turtle Images
 *
 * @author Alex
 */
public class ImageListView extends ItemList<IndexNode> {
    private CommandLineView myCommandLineView;
    private CanvasViewImpl myCanvasView;

    /**
     * Creates new Image list
     *
     * @param project
     */
    public ImageListView(Project project) {
        initializeResources();
        myCommandLineView = project.getCommandLineView();
        myCanvasView = project.getCanvasView();
        update(myCanvasView, null);
        getMyListView().setSpacing(Integer.parseInt(getMyResources().getString("ItemSpacing")));
        getMyListView().setPadding(new Insets(Integer.parseInt(getMyResources().getString("ItemSpacing"))));
    }

    /**
     * Updates content
     */
    @Override
    public void update(Observable o, Object arg) {
        Map<Integer, Image> imageMap = myCanvasView.getImageMap();
        getMyListView().getChildren().clear();
        for (Integer i : imageMap.keySet()) {
            ImageView imageView = new ImageView(imageMap.get(i));
            imageView.setFitWidth(Integer.parseInt(getMyResources().getString("ImageSize")));
            imageView.setFitHeight(Integer.parseInt(getMyResources().getString("ImageSize")));
            addItem(new IndexNode(i, imageView));
        }
    }

    /**
     * Says what to do on click
     */
    @Override
    protected void onClick(IndexNode item) {
        myCommandLineView.setText("SETSHAPE " + item.getCommand());
    }

    /**
     * Add item
     */
    @Override
    protected void addItem(IndexNode toAddItem) {
        getMyListView().getChildren().add(toAddItem.getView());
        toAddItem.getView().setOnMouseClicked(e -> onClick(toAddItem));
    }
}
