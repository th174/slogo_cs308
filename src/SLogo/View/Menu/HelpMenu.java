package SLogo.View.Menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ResourceBundle;

public class HelpMenu implements SLogoMenu {

    private final static String RESOURCES_PATH = "resources/View/";
    private final static String PROPERTIES_FILENAME = "HelpMenu";
    Menu myHelpMenu = new Menu();
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
        initializeMenuItem("CommandList", true);
        initializeMenuItem("JavaDoc", true);
        initializeMenuItem("ReadMe", false);
    }

    private void initializeMenuItem(String itemName, Boolean local) {
        MenuItem helpMenuItem = new MenuItem();
        helpMenuItem.setText(myResources.getString(itemName));
        helpMenuItem.setOnAction(e -> openHTML(getURI(itemName, local)));
        myHelpMenu.getItems().add(helpMenuItem);
    }

    private URI getURI(String itemName, Boolean local) {
        if (local) {
            return new File(System.getProperty("user.dir") + myResources.getString(itemName + "Path")).toURI();
        } else {
            try {
                return new URI(myResources.getString(itemName + "Path"));
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private void openHTML(URI uri) {
        try {
            Desktop.getDesktop().browse(uri);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public Menu getMenu() {
        return myHelpMenu;
    }

}
