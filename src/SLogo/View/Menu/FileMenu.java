package SLogo.View.Menu;

import java.util.ResourceBundle;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class FileMenu implements SLogoMenu{

	Menu myFileMenu = new Menu();
	private final static String RESOURCES_PATH = "resources/View/";
	private final static String PROPERTIES_FILENAME = "FileMenu";
	private ResourceBundle myResources;
	
	public FileMenu() {
		initializeResources();
		initializeFileMenu();
	}
	
	
	private void initializeResources() {
		myResources = ResourceBundle.getBundle(RESOURCES_PATH + PROPERTIES_FILENAME);
	}


	private void initializeFileMenu() {
		myFileMenu.setText(myResources.getString("FileMenu"));
		MenuItem fileChoice = new MenuItem(myResources.getString("NewProject"));
		fileChoice.setOnAction(e -> {
			//Add new tab of a blank project.
		});
		myFileMenu.getItems().add(fileChoice);
	}


	@Override
	public Menu getMenu() {
		return myFileMenu;
	}

}
