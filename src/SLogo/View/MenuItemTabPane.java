package SLogo.View;


import SLogo.FunctionEvaluate.EnvironmentImpl;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MenuItemTabPane implements SLogoGUI{
	TabPane myTabPane = new TabPane();
	
	public MenuItemTabPane(CommandLineView commandLine, EnvironmentImpl environment, double width, double height) {
		MenuItemBox variableListView = new VariableListViewBasic(commandLine);
		environment.addObserver(variableListView);
    	Node variableListViewNode = variableListView.getView();
    	Tab variableListTab = new Tab();
    	variableListTab.setClosable(false);
    	variableListTab.setText("Variables");
    	variableListTab.setContent(variableListViewNode);
    	
    	MenuItemBox functionListView = new FunctionListViewBasic(commandLine);
    	environment.addObserver(functionListView);
    	Node functionListViewNode = functionListView.getView();
    	Rectangle rectangleFunctionView = new Rectangle(width,height);
    	rectangleFunctionView.setFill(Color.BLANCHEDALMOND);
    	Tab functionListTab = new Tab();
    	functionListTab.setClosable(false);
    	functionListTab.setText("Functions");
    	functionListTab.setContent(functionListViewNode);
    	
    	//myTabPane.setPrefSize(width, height);
    	myTabPane.getTabs().addAll(variableListTab,functionListTab);
    	
	}
	
	@Override
	public Node getView() {
		return myTabPane;
	}

	@Override
	public void setSize(double width, double height) {
		// TODO Auto-generated method stub
		
	}

}
