package SLogo.View.DisplayBar;

import java.util.Observer;
import java.util.ResourceBundle;

import SLogo.View.SLogoGUIElement;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public abstract class ItemList<T extends VisualCommand> implements Observer, SLogoGUIElement {
	private VBox myListView = new VBox();
	private final static String RESOURCES_PATH = "resources/View/";
	private final static String PROPERTIES_FILENAME = "ItemList";
	private ResourceBundle myResources;
	
	@Override
	public Node getView() {
		return myListView;
	}
	
	protected abstract void addItem(T toAddItem);

	protected abstract void onClick(T item);
	
	protected void removeItem(T toRemoveItem){
		myListView.getChildren().remove(toRemoveItem.getView());
	}
	
	protected void initializeResources() {
		myResources = ResourceBundle.getBundle(RESOURCES_PATH + PROPERTIES_FILENAME);
	}
	
	protected ResourceBundle getMyResources() {
		return myResources;
	}
	
	protected VBox getMyListView() {
		return myListView;
	}
}