package SLogo.View.Menu;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class FileMenu implements SLogoMenu{

	Menu myFileMenu = new Menu();
	private final static String RESOURCES_PATH = "resources/View/";
	private final static String PROPERTIES_FILENAME = "FileMenu";
	private ResourceBundle myResources;
	
	public FileMenu(EventHandler<ActionEvent> addProject) {
		initializeResources();
		initializeFileMenu(addProject);
	}
	
	
	private void initializeResources() {
		myResources = ResourceBundle.getBundle(RESOURCES_PATH + PROPERTIES_FILENAME);
	}


	private void initializeFileMenu(EventHandler<ActionEvent> addProject) {
		myFileMenu.setText(myResources.getString("FileMenu"));
		MenuItem fileChoice = new MenuItem(myResources.getString("NewProject"));
		fileChoice.setOnAction(addProject);
		myFileMenu.getItems().add(fileChoice);
	}


	@Override
	public Menu getMenu() {
		return myFileMenu;
	}

}
