package SLogo.View.DisplayBar;

import java.util.ResourceBundle;

import SLogo.View.SLogoGUIElement;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SettingsView implements SLogoGUIElement{
	private VBox mySettings = new VBox();
	private final static String RESOURCES_PATH = "resources/View/";
	private final static String PROPERTIES_FILENAME = "SettingsView";
	private ResourceBundle myResources;
	
	public SettingsView() {
		initializeResources();
		mySettings.getChildren().add(new Label(myResources.getString("Title")));

		mySettings.getChildren().add(new Label(myResources.getString("BackgroundColor")));
		mySettings.getChildren().add(new Label(myResources.getString("Title")));
		
		
//		set a background color for the turtle's display area
//		set an image to use for the turtle
//		set a color to use for the pen

		//Pen size
		//
	}

	private void initializeResources() {
		myResources = ResourceBundle.getBundle(RESOURCES_PATH + PROPERTIES_FILENAME);
	}
	
	@Override
	public Node getView() {
		return mySettings;
	}
	
}
