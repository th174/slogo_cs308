package SLogo.View.Menu;

import java.util.ResourceBundle;

import com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations.PrivateKeyResolver;

import SLogo.Parse.Parser;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class LanguageMenu implements SLogoMenu{

	private Menu myLanguageMenu = new Menu();
	private Parser myParser;
	private final static String RESOURCES_PATH = "resources/View/";
	private final static String PROPERTIES_FILENAME = "LanguageMenu";
	private ResourceBundle myResources;
	
	public LanguageMenu(Parser parser) {
		initializeResources();
		myParser = parser;
		initializeLanguageMenu();
	}
	
	private void initializeResources() {
		myResources = ResourceBundle.getBundle(RESOURCES_PATH + PROPERTIES_FILENAME);
	}
	
	private void initializeLanguageMenu() {
		myLanguageMenu.setText(myResources.getString("LanguageMenu"));
		String [] languages = myResources.getString("Languages").split(" ");
		for(String string : languages){
			addLanguage(string);
		}
	}
	private void addLanguage(String language) {
		MenuItem languageChoice = new MenuItem(language);
		languageChoice.setOnAction(e -> {
			myParser.setLocale(languageChoice.getText());
		});
		myLanguageMenu.getItems().add(languageChoice);
	}

	@Override
	public Menu getMenu() {
		return myLanguageMenu;
	}

	
}
