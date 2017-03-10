package SLogo.View;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import SLogo.View.Menu.MenuBarItems;
import SLogo.View.Menu.MenuBarItemsBasic;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;

public class SLogoGUIImpl implements SLogoGUI {
	
	TabPane myTabPane = new TabPane();
	private Group myRoot;
	private double myWidth;
	private double myHeight;
	private final static String RESOURCES_PATH = "resources/View/";
	private final static String PROPERTIES_FILENAME = "SLogoGUI";
	private ResourceBundle myResources;
	private ReaderWriter<Node> myProjectReaderWriter;
	private Map<Node,Project> myProjectMap = new HashMap<Node,Project>();
	
	
	public SLogoGUIImpl(double width,double height){
		myRoot = new Group();
		myWidth = width;
		myHeight = height;
		myProjectReaderWriter = new ReaderWriter<Node>();
		initializeResources();
		GridPane gridPane = new GridPane();
		double menuBarHeight = Integer.parseInt(myResources.getString("MenuBarHeight"));
    	// Heights | Rows
    	MenuBarItems menuBarItems = new MenuBarItemsBasic(myTabPane, myProjectMap, e->addNewProjectTab(createNewProjectTab(menuBarHeight,myResources.getString("Project"))),
    													  this::saveFile,this::loadFile);
    	Node menuBarItemsNode = menuBarItems.getView();
    	GridPane.setConstraints(menuBarItemsNode, 0, 0, 1, 1, HPos.CENTER, VPos.TOP);
    	
    	addNewProjectTab(createNewProjectTab(menuBarHeight,myResources.getString("Project")));
    	GridPane.setConstraints(myTabPane, 0, 1, 1, 1, HPos.CENTER, VPos.TOP);
    	gridPane.getChildren().addAll(menuBarItemsNode,myTabPane);
        myRoot.getChildren().addAll(gridPane);
	}

	private void saveFile(ActionEvent e) {
		Node saveProject = myTabPane.getSelectionModel().getSelectedItem().getContent();
		try {
			myProjectReaderWriter.writeObject(saveProject);
		}catch(FileNotFoundException ex) {
        	Alert commandErrorAlert = new Alert(AlertType.ERROR);
        	commandErrorAlert.setTitle("Error");
        	commandErrorAlert.setHeaderText(myResources.getString("FileNotFoundError"));
        	commandErrorAlert.setContentText(e.getClass().getName());
        	commandErrorAlert.showAndWait();
		}catch(Exception ex){
        	Alert commandErrorAlert = new Alert(AlertType.ERROR);
        	commandErrorAlert.setTitle("Error");
        	commandErrorAlert.setHeaderText(myResources.getString("SaveError"));
        	commandErrorAlert.setContentText(e.getClass().getName());
        	commandErrorAlert.showAndWait();
		}
	}

	private void loadFile(ActionEvent e) {
		Node loadedProject;
		try {
			loadedProject = myProjectReaderWriter.readObject();
			Tab loadedTab = new Tab(myResources.getString("LoadedTab"), loadedProject);
			myTabPane.getTabs().add(loadedTab);
		}catch(FileNotFoundException ex) {
			loadedProject = null;
        	Alert commandErrorAlert = new Alert(AlertType.ERROR);
        	commandErrorAlert.setTitle("Error");
        	commandErrorAlert.setHeaderText(myResources.getString("FileNotFoundError"));
        	commandErrorAlert.setContentText(e.getClass().getName());
        	commandErrorAlert.showAndWait();
		}catch(Exception ex){
			loadedProject = null;
        	Alert commandErrorAlert = new Alert(AlertType.ERROR);
        	commandErrorAlert.setTitle("Error");
        	commandErrorAlert.setHeaderText(myResources.getString("LoadError"));
        	commandErrorAlert.setContentText(e.getClass().getName());
        	commandErrorAlert.showAndWait();
		}
	}

	private void addNewProjectTab(Tab tab) {
    	myTabPane.getTabs().add(tab);
	}
	
	private Tab createNewProjectTab(double menuBarHeight,String title) {
		Tab defaultProject = new Tab();
		defaultProject.setText(title);
		Project newProject = new Project(myWidth,myHeight-menuBarHeight*2);
    	defaultProject.setContent(newProject.getView());
    	myProjectMap.put(newProject.getView(), newProject);
    	return defaultProject;
	}
	
    @Override
    public Node getView() {
    	return myRoot;
    }

	private void initializeResources() {
		myResources = ResourceBundle.getBundle(RESOURCES_PATH + PROPERTIES_FILENAME);
	}
}