package SLogo.View;

import SLogo.FileHandling.FileHandler;
import SLogo.FileHandling.LibraryWriter;
import SLogo.FunctionEvaluate.Environment;
import SLogo.Repl;
import SLogo.View.Menu.MenuBarItems;
import SLogo.View.Menu.MenuBarItemsBasic;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


/**
 * Implementation of the main slogo gui
 *
 * @author Alex
 */
public class SLogoGUIImpl implements SLogoGUI {

    private final static String RESOURCES_PATH = "resources/View/";
    private final static String PROPERTIES_FILENAME = "SLogoGUI";
    TabPane myTabPane = new TabPane();
    private Group myRoot;
    private Stage myStage;
    private double myWidth;
    private double myHeight;
    private ResourceBundle myResources;
    private Map<Node, Project> myProjectMap = new HashMap<Node, Project>();

    /**
     * Constructs
     *
     * @param stage
     * @param width
     * @param height
     */
    public SLogoGUIImpl(Stage stage, double width, double height) {
        myRoot = new Group();
        myStage = stage;
        myWidth = width;
        myHeight = height;
        initializeResources();
        GridPane gridPane = new GridPane();
        double menuBarHeight = Integer.parseInt(myResources.getString("MenuBarHeight"));
        // Heights | Rows
        MenuBarItems menuBarItems = new MenuBarItemsBasic(myTabPane, myProjectMap, e -> addNewProjectTab(createNewProjectTab(menuBarHeight, myResources.getString("Project"))),
                this::saveLibrary, this::loadLibrary);
        Node menuBarItemsNode = menuBarItems.getView();
        GridPane.setConstraints(menuBarItemsNode, 0, 0, 1, 1, HPos.CENTER, VPos.TOP);

        addNewProjectTab(createNewProjectTab(menuBarHeight, myResources.getString("Project")));
        GridPane.setConstraints(myTabPane, 0, 1, 1, 1, HPos.CENTER, VPos.TOP);
        gridPane.getChildren().addAll(menuBarItemsNode, myTabPane);
        myRoot.getChildren().addAll(gridPane);
    }

    /**
     * Adds saves functionality via XML
     *
     * @param e
     */
    private void saveLibrary(ActionEvent e) {
        try {
            FileHandler fh = new FileHandler(myStage);
            LibraryWriter lw = new LibraryWriter(getCurrentEnvironment());
            lw.write(fh.saveNewFile(myResources.getString("SaveFile")));
        } catch (FileNotFoundException ex) {
            showErrorAlert(ex, myResources.getString("FileNotFoundError"));
        } catch (Exception ex) {
            showErrorAlert(ex, myResources.getString("SaveError"));
        }
    }

    /**
     * Adds load functionality via XML
     *
     * @param e
     */
    private void loadLibrary(ActionEvent e) {
        try {
            FileHandler fh = new FileHandler(myStage);
            getCurrentRepl().read(fh.getFileData(myResources.getString("LoadFile")));
        } catch (FileNotFoundException ex) {
            showErrorAlert(ex, myResources.getString("FileNotFoundError"));
        } catch (Exception ex) {
            showErrorAlert(ex, myResources.getString("LoadError"));
        }
    }

    /**
     * Returns current environment
     *
     * @return
     */
    private Environment getCurrentEnvironment() {
        return myProjectMap.get(myTabPane.getSelectionModel().getSelectedItem().getContent()).getRepl().getUserEnvironment();
    }

    /**
     * Returns current repl
     *
     * @return
     */
    private Repl getCurrentRepl() {
        return myProjectMap.get(myTabPane.getSelectionModel().getSelectedItem().getContent()).getRepl();
    }

    /**
     * Adds a tab
     *
     * @param tab
     */
    private void addNewProjectTab(Tab tab) {
        myTabPane.getTabs().add(tab);
    }

    /**
     * Creates a tab with a project in it
     *
     * @param menuBarHeight
     * @param title
     * @return
     */
    private Tab createNewProjectTab(double menuBarHeight, String title) {
        Tab defaultProject = new Tab();
        defaultProject.setText(title);
        Project newProject = new Project(myWidth, myHeight - menuBarHeight * 2);
        defaultProject.setContent(newProject.getView());
        myProjectMap.put(newProject.getView(), newProject);
        return defaultProject;
    }

    /**
     * Returns a node
     */
    @Override
    public Node getView() {
        return myRoot;
    }

    /**
     * Initializes resources
     */
    private void initializeResources() {
        myResources = ResourceBundle.getBundle(RESOURCES_PATH + PROPERTIES_FILENAME);
    }

    /**
     * Shows file error alerts
     *
     * @param e
     * @param errorType
     */
    private void showErrorAlert(Exception e, String errorType) {
        Alert commandErrorAlert = new Alert(AlertType.ERROR);
        commandErrorAlert.setTitle("Error");
        commandErrorAlert.setHeaderText(myResources.getString("FileNotFoundError"));
        commandErrorAlert.setContentText(e.getClass().getName());
        commandErrorAlert.showAndWait();
    }
}