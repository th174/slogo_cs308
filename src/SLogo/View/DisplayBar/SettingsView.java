package SLogo.View.DisplayBar;

import java.util.ResourceBundle;

import SLogo.View.CanvasViewImpl;
import SLogo.View.SLogoGUIElement;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SettingsView implements SLogoGUIElement{
	private VBox mySettings = new VBox();
	private final static String RESOURCES_PATH = "resources/View/";
	private final static String PROPERTIES_FILENAME = "SettingsView";
	private ResourceBundle myResources;
	private CanvasViewImpl myCanvasView;
	
	public SettingsView(CanvasViewImpl canvasView) {
		initializeResources();
		myCanvasView = canvasView;
		mySettings.getChildren().add(new Label(myResources.getString("BackgroundColor")));
		
		
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
