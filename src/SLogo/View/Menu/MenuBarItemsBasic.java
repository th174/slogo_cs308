package SLogo.View.Menu;

import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class MenuBarItemsBasic implements MenuBarItems{

	MenuBar myMenuBar = new MenuBar();
	
	public MenuBarItemsBasic() {
		initializeFileMenu();
		initializeLanguageMenu();
	}

	private void initializeFileMenu() {
		Menu fileMenu = new FileMenu().getMenu();
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
