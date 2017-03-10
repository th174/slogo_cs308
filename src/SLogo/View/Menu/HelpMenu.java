package SLogo.View.Menu;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ResourceBundle;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

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
		initializeMenuItem("CommandList",true);
		initializeMenuItem("JavaDoc", true);
		initializeMenuItem("ReadMe", false);
	}

	private void initializeMenuItem(String itemName, Boolean local) {
		MenuItem helpMenuItem = new MenuItem();
		helpMenuItem.setText(myResources.getString(itemName));
		helpMenuItem.setOnAction(e -> openHTML((local ? "file://" + System.getProperty("user.dir").replace('\\', '/') : "") + myResources.getString(itemName+"Path")));
		myHelpMenu.getItems().add(helpMenuItem);
	}
	
	private void openHTML(String path) {
		try {
			Desktop.getDesktop().browse(new URI(path));
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Menu getMenu() {
		return myHelpMenu;
	}
	
}
