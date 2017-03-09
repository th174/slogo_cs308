package SLogo.View.Menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class MenuBarItemsBasic implements MenuBarItems{

	MenuBar myMenuBar = new MenuBar();
	
	public MenuBarItemsBasic(EventHandler<ActionEvent> addProject,EventHandler<ActionEvent> saveProject,EventHandler<ActionEvent> loadProject) {
		initializeFileMenu(addProject,saveProject,loadProject);
		initializeLanguageMenu();
		initializeHelpMenu();
	}

	private void initializeFileMenu(EventHandler<ActionEvent> addProject,EventHandler<ActionEvent> saveProject,EventHandler<ActionEvent> loadProject) {
		Menu fileMenu = new FileMenu(addProject,saveProject,loadProject).getMenu();
		myMenuBar.getMenus().add(fileMenu);
	}

	private void initializeLanguageMenu() {
		Menu languageMenu = new LanguageMenu().getMenu();
		myMenuBar.getMenus().add(languageMenu);
	}

	private void initializeHelpMenu() {
		Menu helpMenu = new HelpMenu().getMenu();
		myMenuBar.getMenus().add(helpMenu);
	}
	
	@Override
	public Node getView() {
		return myMenuBar;
	}

	@Override
	public void setSize(double width, double height) {
		// TODO Auto-generated method stub
		
	}
	
}
