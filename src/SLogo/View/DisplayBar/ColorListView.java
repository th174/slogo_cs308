// This entire file is part of my masterpiece.
// Alex Salas

package SLogo.View.DisplayBar;

import SLogo.View.CanvasViewImpl;
import SLogo.View.CommandLineView;
import SLogo.View.Project;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Map;
import java.util.Observable;

/**
 * Extends ItemList for use with colors
 *
 * @author Alex
 */
public class ColorListView extends ItemList<IndexNode> {
    private CommandLineView myCommandLineView;
    private CanvasViewImpl myCanvasView;

    /**
     * Creates new ColorListView
     *
     * @param project
     */
    public ColorListView(Project project) {
        initializeResources();
        myCommandLineView = project.getCommandLineView();
        myCanvasView = project.getCanvasView();
        update(myCanvasView, null);
        getMyListView().setSpacing(Integer.parseInt(getMyResources().getString("ItemSpacing")));
        getMyListView().setPadding(new Insets(Integer.parseInt(getMyResources().getString("ItemSpacing"))));
    }

    /**
     * Used to update contents
     */
    @Override
    public void update(Observable o, Object arg) {
        Map<Integer, Color> colorMap = myCanvasView.getColorMap();
        getMyListView().getChildren().clear();
        for (Integer i : colorMap.keySet()) {
            Rectangle rectangle = new Rectangle(Integer.parseInt(getMyResources().getString("ImageSize")),
            		                            Integer.parseInt(getMyResources().getString("ImageSize")));
            rectangle.setFill(colorMap.get(i));
            addItem(new IndexNode(i, rectangle));
        }
    }

    /**
     * Say what to do on click for items
     */
    @Override
    protected void onClick(IndexNode item) {
        myCommandLineView.setText("SETPENCOLOR " + item.getCommand());
    }

    /**
     * Add items to group
     */
    @Override
    protected void addItem(IndexNode toAddItem) {
        getMyListView().getChildren().add(toAddItem.getView());
        toAddItem.getView().setOnMouseClicked(e -> onClick(toAddItem));
    }
}
