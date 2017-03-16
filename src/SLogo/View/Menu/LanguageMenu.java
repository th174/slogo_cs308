package SLogo.View.Menu;

import SLogo.View.Project;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.Map;
import java.util.ResourceBundle;

public class LanguageMenu implements SLogoMenu {

    private final static String RESOURCES_PATH = "resources/View/";
    private final static String PROPERTIES_FILENAME = "LanguageMenu";
    private Menu myLanguageMenu = new Menu();
    private TabPane myTabPane;
    private Tab currentTab;
    private ResourceBundle myResources;
    private Map<Node, Project> myProjectMap;

    public LanguageMenu(TabPane tabPane, Map<Node, Project> projectMap) {
        initializeResources();
        myTabPane = tabPane;
        myProjectMap = projectMap;
        myTabPane.getSelectionModel().selectedItemProperty().addListener((listener, oldVal, newVal) -> updateProject(newVal));
        initializeLanguageMenu();
    }

    private void updateProject(Tab newTab) {
        currentTab = newTab;
    }

    private void initializeResources() {
        myResources = ResourceBundle.getBundle(RESOURCES_PATH + PROPERTIES_FILENAME);
    }

    private void initializeLanguageMenu() {
        myLanguageMenu.setText(myResources.getString("LanguageMenu"));
        String[] languages = myResources.getString("Languages").split(" ");
        for (String string : languages) {
            addLanguage(string);
        }
    }

    private void addLanguage(String language) {
        MenuItem languageChoice = new MenuItem(language);
        languageChoice.setOnAction(e -> {
            myProjectMap.get(currentTab.getContent()).getRepl().getParser().setLocale(languageChoice.getText());
        });
        myLanguageMenu.getItems().add(languageChoice);
    }

    @Override
    public Menu getMenu() {
        return myLanguageMenu;
    }


}
