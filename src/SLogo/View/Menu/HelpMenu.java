package SLogo.View.Menu;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.web.WebView;

public class HelpMenu implements SLogoMenu{

	Menu myHelpMenu = new Menu();
	private final static String RESOURCES_PATH = "resources/View/";
	private final static String PROPERTIES_FILENAME = "HelpMenu";
	private ResourceBundle myResources;

	public HelpMenu() {
		initializeResources();
		initializeHelpMenu();
	}
	
	private void initializeResources() {
		myResources = ResourceBundle.getBundle(RESOURCES_PATH + PROPERTIES_FILENAME);
	}
	
	private void initializeHelpMenu() {
		myHelpMenu.setText(myResources.getString("HelpMenu"));
		MenuItem helpMenuItem = new MenuItem();
		helpMenuItem.setText(myResources.getString("CommandList"));
		helpMenuItem.setOnAction(e -> helpAlert());	
		myHelpMenu.getItems().add(helpMenuItem);
	}
	
	private void helpAlert() {
		System.out.println("Hey");
		Alert commandInfoAlert = new Alert(AlertType.INFORMATION);
		commandInfoAlert.setTitle(myResources.getString("AlertTitle"));
		commandInfoAlert.setHeaderText(myResources.getString("AlertHeader"));
		commandInfoAlert.setContentText(myResources.getString("AlertContent"));
		WebView HTMLView = new WebView();
	    String HTMLContent;
		try {
			HTMLContent = new String(Files.readAllBytes(Paths.get(myResources.getString("HTMLPath"))));
		} catch (IOException e) {
			HTMLContent = "Error Reading HTML";
			e.printStackTrace();
		}
		HTMLView.getEngine().loadContent(HTMLContent);
		commandInfoAlert.setGraphic(HTMLView);
		commandInfoAlert.showAndWait();
	}

	@Override
	public Menu getMenu() {
		return myHelpMenu;
	}
	
}
