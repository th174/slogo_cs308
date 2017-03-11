package SLogo.View.Menu;

import java.util.Map;

import SLogo.View.Project;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;

public class MenuBarItemsBasic implements MenuBarItems{

	private MenuBar myMenuBar = new MenuBar();
	private TabPane myTabPane;
	private Map<Node, Project> myProjectMap;
	
	public MenuBarItemsBasic(TabPane tabPane, Map<Node, Project> projectMap, EventHandler<ActionEvent> addProject,EventHandler<ActionEvent> saveProject,EventHandler<ActionEvent> loadProject) {
		myTabPane = tabPane;
		myProjectMap = projectMap;
		initializeFileMenu(addProject,saveProject,loadProject);
		initializeLanguageMenu();
		initializeHelpMenu();
	}

	private void initializeFileMenu(EventHandler<ActionEvent> addProject,EventHandler<ActionEvent> saveProject,EventHandler<ActionEvent> loadProject) {
		Menu fileMenu = new FileMenu(addProject,saveProject,loadProject).getMenu();
		myMenuBar.getMenus().add(fileMenu);
	}

	private void initializeLanguageMenu() {
		Menu languageMenu = new LanguageMenu(myTabPane,myProjectMap).getMenu();
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
	
}
