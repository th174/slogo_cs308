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
	
	public FileMenu(EventHandler<ActionEvent> addProject, EventHandler<ActionEvent> saveProject, EventHandler<ActionEvent> loadProject) {
		initializeResources();
		initializeFileMenu(addProject,saveProject,loadProject);
	}
	
	private void initializeResources() {
		myResources = ResourceBundle.getBundle(RESOURCES_PATH + PROPERTIES_FILENAME);
	}

	private void initializeFileMenu(EventHandler<ActionEvent> addProject, EventHandler<ActionEvent> saveProject, EventHandler<ActionEvent> loadProject) {
		myFileMenu.setText(myResources.getString("FileMenu"));
		initializeNewProjectChoice(addProject);
		initializeSaveChoice(saveProject);
		initializeLoadChoice(loadProject);
		
	}

	private void initializeNewProjectChoice(EventHandler<ActionEvent> addProject) {
		MenuItem newProjectChoice = new MenuItem(myResources.getString("NewProject"));
		newProjectChoice.setOnAction(addProject);
		myFileMenu.getItems().add(newProjectChoice);
	}

	private void initializeSaveChoice(EventHandler<ActionEvent> saveProject) {
		MenuItem saveChoice = new MenuItem(myResources.getString("SaveProject"));
		saveChoice.setOnAction(saveProject);
		myFileMenu.getItems().add(saveChoice);
	}

	private void initializeLoadChoice(EventHandler<ActionEvent> loadProject) {
		MenuItem loadChoice = new MenuItem(myResources.getString("LoadProject"));
		loadChoice.setOnAction(loadProject);
		myFileMenu.getItems().add(loadChoice);
	}

	@Override
	public Menu getMenu() {
		return myFileMenu;
	}
}
