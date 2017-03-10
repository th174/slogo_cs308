package SLogo.View.DisplayBar;

import java.util.HashSet;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.Set;

import SLogo.View.CommandLineView;
import SLogo.View.SLogoGUIElement;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public abstract class ItemList<T extends VisualCommand> implements Observer, SLogoGUIElement {
	private VBox myListView = new VBox();
	private Set<T> myItems = new HashSet<T>();
	private CommandLineView myCommandLineView;
	private final static String RESOURCES_PATH = "resources/View/";
	private final static String PROPERTIES_FILENAME = "ItemList";
	private ResourceBundle myResources;
	
	@Override
	public Node getView() {
		return myListView;
	}
	
	protected void addItem(T toAddItem){
		if(myItems.add(toAddItem)){ 
			myListView.getChildren().add(toAddItem.getView());
		}
		toAddItem.getView().setOnMouseClicked(e -> onClick(toAddItem));
	}

	protected abstract void onClick(T item);
	
	protected void removeItem(T toRemoveItem){
		ObservableList<Node> nodes = myListView.getChildren();
		nodes.remove(toRemoveItem.getView());
		myItems.remove(toRemoveItem);
	}
	
	protected void addToCommandLine(String text) {
		myCommandLineView.setText(text);
	}
	
	protected void updateContents(Set<T> updatedItems) {
		Set<T> itemsToAdd = new HashSet<T>();
		itemsToAdd.addAll(updatedItems);
		itemsToAdd.removeAll(myItems);
		for(T item: itemsToAdd){addItem(item);}
		Set<T> itemsToRemove = new HashSet<T>();
		itemsToRemove.addAll(myItems);
		itemsToRemove.removeAll(updatedItems);
		for(T item: itemsToRemove){removeItem(item);}
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