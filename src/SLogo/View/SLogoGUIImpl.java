package SLogo.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import SLogo.Repl;
import SLogo.FileHandling.FileHandler;
import SLogo.FileHandling.LibraryWriter;
import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.EnvironmentImpl;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SLogoGUIImpl implements SLogoGUI {
	
	TabPane myTabPane = new TabPane();
	private Group myRoot;
	private Stage myStage;
	private double myWidth;
	private double myHeight;
	private final static String RESOURCES_PATH = "resources/View/";
	private final static String PROPERTIES_FILENAME = "SLogoGUI";
	private ResourceBundle myResources;
	private Map<Node,Project> myProjectMap = new HashMap<Node,Project>();
	
	
	public SLogoGUIImpl(Stage stage,double width,double height){
		myRoot = new Group();
		myStage = stage;
		myWidth = width;
		myHeight = height;
		initializeResources();
		GridPane gridPane = new GridPane();
		double menuBarHeight = Integer.parseInt(myResources.getString("MenuBarHeight"));
    	// Heights | Rows
    	MenuBarItems menuBarItems = new MenuBarItemsBasic(myTabPane, myProjectMap, e->addNewProjectTab(createNewProjectTab(menuBarHeight,myResources.getString("Project"))),
    													  this::saveLibrary,this::loadLibrary);
    	Node menuBarItemsNode = menuBarItems.getView();
    	GridPane.setConstraints(menuBarItemsNode, 0, 0, 1, 1, HPos.CENTER, VPos.TOP);
    	
    	addNewProjectTab(createNewProjectTab(menuBarHeight,myResources.getString("Project")));
    	GridPane.setConstraints(myTabPane, 0, 1, 1, 1, HPos.CENTER, VPos.TOP);
    	gridPane.getChildren().addAll(menuBarItemsNode,myTabPane);
        myRoot.getChildren().addAll(gridPane);
	}

	private void saveLibrary(ActionEvent e) {
		try {
			FileHandler fh = new FileHandler(myStage);
			LibraryWriter lw = new LibraryWriter(getCurrentEnvironment());
			lw.write(fh.getFile(myResources.getString("SaveFile")));
		}catch(FileNotFoundException ex) {
        	showErrorAlert(ex, myResources.getString("FileNotFoundError"));
		}catch(Exception ex){
			showErrorAlert(ex, myResources.getString("SaveError"));
		}
	}

	private void loadLibrary(ActionEvent e) {
		try {
			FileHandler fh = new FileHandler(myStage);
			getCurrentRepl().read(fh.getFileData(myResources.getString("LoadFile")));
		}catch(FileNotFoundException ex) {
			showErrorAlert(ex, myResources.getString("FileNotFoundError"));
		}catch(Exception ex){
			showErrorAlert(ex, myResources.getString("LoadError"));
		}
	}
	
	private Environment getCurrentEnvironment(){
		return myProjectMap.get(myTabPane.getSelectionModel().getSelectedItem().getContent()).getRepl().getEnvironment();
	}
	
	private Repl getCurrentRepl(){
		return myProjectMap.get(myTabPane.getSelectionModel().getSelectedItem().getContent()).getRepl();
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
	
	private void showErrorAlert(Exception e, String errorType){
		Alert commandErrorAlert = new Alert(AlertType.ERROR);
    	commandErrorAlert.setTitle("Error");
    	commandErrorAlert.setHeaderText(myResources.getString("FileNotFoundError"));
    	commandErrorAlert.setContentText(e.getClass().getName());
    	commandErrorAlert.showAndWait();
	}
}