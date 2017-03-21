package SLogo.View.DisplayBar;

import SLogo.View.SLogoGUIElement;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Generalized list of items, extended easily
 *
 * @param <T>
 * @author Alex
 */
public abstract class ItemList<T extends VisualCommand> implements Observer, SLogoGUIElement {
    private final static String RESOURCES_PATH = "resources/View/";
    private final static String PROPERTIES_FILENAME = "ItemList";
    private VBox myListView = new VBox();
    private ResourceBundle myResources;

    /**
     * Gets node
     */
    @Override
    public Node getView() {
        return myListView;
    }

    /**
     * Adds an item to the list
     *
     * @param toAddItem
     */
    protected abstract void addItem(T toAddItem);

    /**
     * Sets onClick action
     *
     * @param item
     */
    protected abstract void onClick(T item);

    /**
     * Removes item
     *
     * @param toRemoveItem
     */
    protected void removeItem(T toRemoveItem) {
        myListView.getChildren().remove(toRemoveItem.getView());
    }

    /**
     *
     */
    protected void initializeResources() {
        myResources = ResourceBundle.getBundle(RESOURCES_PATH + PROPERTIES_FILENAME);
    }

    /**
     * Gets resourcebundle
     *
     * @return
     */
    protected ResourceBundle getMyResources() {
        return myResources;
    }

    /**
     * Gets main root
     *
     * @return
     */
    protected VBox getMyListView() {
        return myListView;
    }
}