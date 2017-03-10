package SLogo.View.DisplayBar;

import java.util.HashSet;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.Set;

import SLogo.View.CommandLineView;
import SLogo.View.SLogoGUIElement;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public abstract class TextItemList implements Observer, SLogoGUIElement {
	private VBox myListView = new VBox();
	private Set<String> myItems = new HashSet<String>();
	private CommandLineView myCommandLineView;
	private final static String RESOURCES_PATH = "resources/View/";
	private final static String PROPERTIES_FILENAME = "ItemList";
	private ResourceBundle myResources;
	
	public TextItemList(CommandLineView commandLineView){
		initializeResources();
		myCommandLineView = commandLineView;		
	}
	
	@Override
	public Node getView() {
		return myListView;
	}
	
	@Override
	public void setSize(double width, double height){
		//TODO: Finish
	}
	
	protected void addItem(String toAddString){
		TextArea text = new TextArea(toAddString);
		text.setEditable(false);
		text.setOnMouseClicked(e -> addToCommandLine(text.getText()));
		myListView.getChildren().add(text);
	}

	protected void removeItem(String toRemoveString){
		ObservableList<Node> nodes = myListView.getChildren();
		TextArea toRemove = null;
		for(Node n: nodes){
			TextArea textArea = (TextArea) n;
			if(n instanceof TextArea && textArea.getText().equals(toRemoveString)){
				toRemove = textArea;
			}
		}
		nodes.remove(toRemove);
	}
	
	protected void addToCommandLine(String text) {
		myCommandLineView.setText(text);
	}
	
	protected void updateContents(Set<String> updatedItems) {
		Set<String> itemsToAdd = new HashSet<String>();
		itemsToAdd.addAll(updatedItems);
		itemsToAdd.removeAll(myItems);
		for(String s: itemsToAdd){addItem(s);}
		Set<String> itemsToRemove = new HashSet<String>();
		itemsToRemove.addAll(myItems);
		itemsToRemove.removeAll(updatedItems);
		for(String s: itemsToRemove){removeItem(s);}
	}
	
	private void initializeResources() {
		myResources = ResourceBundle.getBundle(RESOURCES_PATH + PROPERTIES_FILENAME);
	}
	
	protected ResourceBundle getMyResources() {
		return myResources;
	}
}