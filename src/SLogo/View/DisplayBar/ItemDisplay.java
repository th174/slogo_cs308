package SLogo.View.DisplayBar;

import SLogo.FunctionEvaluate.EnvironmentImpl;
import SLogo.View.Project;
import SLogo.View.SLogoGUIElement;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.ResourceBundle;

/**
 * Creates right side display with variables, functions, colors, and images
 *
 * @author Alex
 */
public class ItemDisplay implements SLogoGUIElement {
    private final static String RESOURCES_PATH = "resources/View/";
    private final static String PROPERTIES_FILENAME = "ItemList";
    TabPane myTabPane = new TabPane();
    private ResourceBundle myResources;

    /**
     * Creates Item display with specified height
     *
     * @param project
     * @param width
     * @param height
     */
    public ItemDisplay(Project project, double width, double height) {
        initializeResources();
        ItemList<TextContainer> variableListView = new VariableListView(project);
        ((EnvironmentImpl) project.getRepl().getUserEnvironment()).addObserver(variableListView);
        Node variableListViewNode = variableListView.getView();
        Tab variableListTab = new Tab();
        variableListTab.setClosable(false);
        variableListTab.setText(myResources.getString("VariableTab"));
        variableListTab.setContent(variableListViewNode);

        ItemList<TextContainer> functionListView = new FunctionListView(project);
        ((EnvironmentImpl) project.getRepl().getUserEnvironment()).addObserver(functionListView);
        Node functionListViewNode = functionListView.getView();
        Tab functionListTab = new Tab();
        functionListTab.setClosable(false);
        functionListTab.setText(myResources.getString("FunctionTab"));
        functionListTab.setContent(functionListViewNode);

        ItemList<IndexNode> colorListView = new ColorListView(project);
        project.getCanvasView().addObserver(colorListView);
        Node colorListViewNode = colorListView.getView();
        Tab colorListTab = new Tab();
        colorListTab.setClosable(false);
        colorListTab.setText(myResources.getString("ColorTab"));
        colorListTab.setContent(colorListViewNode);

        ItemList<IndexNode> imageListView = new ImageListView(project);
        project.getCanvasView().addObserver(imageListView);
        Node imageListViewNode = imageListView.getView();
        Tab imageListTab = new Tab();
        imageListTab.setClosable(false);
        imageListTab.setText(myResources.getString("ImageTab"));
        imageListTab.setContent(imageListViewNode);

        myTabPane.setPrefSize(width, height);
        myTabPane.getTabs().addAll(variableListTab, functionListTab, colorListTab, imageListTab);
    }

    /**
     * Creates new resources
     */
    private void initializeResources() {
        myResources = ResourceBundle.getBundle(RESOURCES_PATH + PROPERTIES_FILENAME);
    }

    /**
     * Gets node
     */
    @Override
    public Node getView() {
        return myTabPane;
    }
}