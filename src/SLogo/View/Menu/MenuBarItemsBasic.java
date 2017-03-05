package SLogo.View.Menu;

import SLogo.View.SLogoGUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class MenuBarItemsBasic implements MenuBarItems{

	MenuBar myMenuBar = new MenuBar();
	
	public MenuBarItemsBasic(EventHandler<ActionEvent> addProject) {
		initializeFileMenu(addProject);
		initializeLanguageMenu();
	}

	private void initializeFileMenu(EventHandler<ActionEvent> addProject) {
		Menu fileMenu = new FileMenu(addProject).getMenu();
		myMenuBar.getMenus().addAll(fileMenu);
	}

	private void initializeLanguageMenu() {
		Menu languageMenu = new LanguageMenu().getMenu();
		myMenuBar.getMenus().addAll(languageMenu);
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
