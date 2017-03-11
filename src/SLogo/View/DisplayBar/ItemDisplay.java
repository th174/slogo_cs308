package SLogo.View.DisplayBar;


import java.util.ResourceBundle;

import SLogo.FunctionEvaluate.EnvironmentImpl;
import SLogo.View.CommandLineView;
import SLogo.View.SLogoGUIElement;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ItemDisplay implements SLogoGUIElement{
	TabPane myTabPane = new TabPane();
	private final static String RESOURCES_PATH = "resources/View/";
	private final static String PROPERTIES_FILENAME = "ItemList";
	private ResourceBundle myResources;
	
	public ItemDisplay(CommandLineView commandLine, EnvironmentImpl environment, double width, double height) {
		initializeResources();
		
		ItemList<TextContainer> variableListView = new VariableListView(commandLine);
		environment.addObserver(variableListView);
    	Node variableListViewNode = variableListView.getView();
    	Tab variableListTab = new Tab();
    	variableListTab.setClosable(false);
    	variableListTab.setText(myResources.getString("VariableTab"));
    	variableListTab.setContent(variableListViewNode);
    	
    	ItemList<TextContainer> functionListView = new FunctionListView(commandLine);
    	environment.addObserver(functionListView);
    	Node functionListViewNode = functionListView.getView();
    	Tab functionListTab = new Tab();
    	functionListTab.setClosable(false);
    	functionListTab.setText(myResources.getString("FunctionTab"));
    	functionListTab.setContent(functionListViewNode);
    	
    	Node colorListViewNode = new ColorListView(commandLine).getView();
    	Tab colorListTab = new Tab();
    	colorListTab.setClosable(false);
    	colorListTab.setText(myResources.getString("ColorTab"));
    	colorListTab.setContent(colorListViewNode);
    	
    	Node imageListViewNode = new ImageListView(commandLine).getView();
    	Tab imageListTab = new Tab();
    	imageListTab.setClosable(false);
    	imageListTab.setText(myResources.getString("ImageTab"));
    	imageListTab.setContent(imageListViewNode);
    	
//    	Node settingsViewNode = new SettingsView().getView();
//    	Tab settingsTab = new Tab();
//    	settingsTab.setClosable(false);
//    	colorListTab.setText(myResources.getString("SettingsTab"));
//    	settingsTab.setContent(settingsViewNode);
    	
    	myTabPane.setPrefSize(width, height);
    	myTabPane.getTabs().addAll(variableListTab,functionListTab,colorListTab,imageListTab);
	}
	
	private void initializeResources() {
		myResources = ResourceBundle.getBundle(RESOURCES_PATH + PROPERTIES_FILENAME);
	}
	
	@Override
	public Node getView() {
		return myTabPane;
	}
}
