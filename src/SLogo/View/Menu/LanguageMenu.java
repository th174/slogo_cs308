package SLogo.View.Menu;

import java.util.ResourceBundle;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class LanguageMenu implements SLogoMenu{

	Menu myLanguageMenu = new Menu();
	private final static String RESOURCES_PATH = "resources/View/";
	private final static String PROPERTIES_FILENAME = "LanguageMenu";
	private ResourceBundle myResources;
	
	public LanguageMenu() {
		initializeResources();
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
			//Repl.setLanguage(englishChoice.getName());
		});
		myLanguageMenu.getItems().add(languageChoice);
	}

	@Override
	public Menu getMenu() {
		return myLanguageMenu;
	}

	
}
