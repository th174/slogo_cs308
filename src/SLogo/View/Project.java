package SLogo.View;

import java.util.ResourceBundle;

import SLogo.Repl;
import SLogo.FunctionEvaluate.EnvironmentImpl;
import SLogo.View.Menu.MenuBarItems;
import SLogo.View.Menu.MenuBarItemsBasic;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Project implements SLogoGUI {
	
	private Repl myRepl;
	private CanvasView myCanvasView;
	private EnvironmentImpl myEnv;
	private Group myRoot;
	private double myWidth;
	private double myHeight;
	private final static String RESOURCES_PATH = "resources/View/";
	private final static String PROPERTIES_FILENAME = "Project";
	private ResourceBundle myResources;
	
	public Project(Repl repl,double width,double height){
		myRepl = repl;
		myEnv = (EnvironmentImpl) myRepl.getEnvironment();
		myRoot = new Group();
		myWidth = width;
		myHeight = height;
		initializeResources();
		GridPane gridPane = new GridPane();
		// Heights | Rows
    	double canvasHeightWeight = Integer.parseInt(myResources.getString("CanvasHeightWeight"));
    	double commandLineHeightWeight = Integer.parseInt(myResources.getString("CommandLineHeightWeight"));
    	double canvasHeightRatio = canvasHeightWeight / (canvasHeightWeight + commandLineHeightWeight);
    	double commandLineHeightRatio = commandLineHeightWeight / (canvasHeightWeight + commandLineHeightWeight);
    	// Widths | Columns
    	double canvasWidthWeight = Integer.parseInt(myResources.getString("CanvasWidthWeight"));
    	double displayWidthWeight = Integer.parseInt(myResources.getString("DisplayWidthWeight"));
    	double canvasWidthRatio = canvasWidthWeight/(canvasWidthWeight + displayWidthWeight);
    	double displayWidthRatio = displayWidthWeight/(canvasWidthWeight + displayWidthWeight);
    	setGridConstraints(gridPane, canvasHeightRatio, commandLineHeightRatio, canvasWidthRatio, displayWidthRatio);
    	
    	myCanvasView = new CanvasViewImpl((int)(myWidth * canvasWidthRatio),(int)(myHeight * canvasHeightRatio));
    	myRepl.setCanvas(myCanvasView);
    	Node canvasViewNode = myCanvasView.getView();
    	
    	Rectangle rectangleCanvasView = new Rectangle(myWidth * canvasWidthRatio,myHeight * canvasHeightRatio);
    	GridPane.setConstraints(rectangleCanvasView, 0, 1, 1, 1, HPos.CENTER, VPos.TOP);
    	rectangleCanvasView.setFill(Color.AQUAMARINE);
    	
    	CommandLineView commandLine = new CommandLineViewBasic(myRepl,myCanvasView, myWidth,myHeight*.2);
    	Node commandLineNode = commandLine.getView();
    	GridPane.setConstraints(commandLineNode, 0, 2, 2, 1, HPos.CENTER, VPos.TOP);
//    	
//    	Rectangle rectangleMenuView = new Rectangle(myWidth * displayWidthRatio,myHeight * canvasHeightRatio);
//    	rectangleMenuView.setFill(Color.GREENYELLOW);
//    	GridPane.setConstraints(rectangleMenuView, 1, 1, 1, 1, HPos.CENTER, VPos.TOP);
    	
    	SLogoGUI menuItemTabPane = new MenuItemTabPane(commandLine, myEnv, myWidth * displayWidthRatio,myHeight * canvasHeightRatio);
    	Node menuItemTabPaneNode = menuItemTabPane.getView();
    	GridPane.setConstraints(menuItemTabPaneNode, 1, 1, 1, 1, HPos.CENTER, VPos.TOP);
    	
    	gridPane.getChildren().addAll(rectangleCanvasView,menuItemTabPaneNode,commandLineNode);
        myRoot.getChildren().addAll(gridPane,canvasViewNode);
	}
	
    @Override
    public Node getView() {
    	return myRoot;
    }

	private void initializeResources() {
		myResources = ResourceBundle.getBundle(RESOURCES_PATH + PROPERTIES_FILENAME);
	}
	
	private void setGridConstraints(GridPane gridPane, double canvasHeightRatio, double commandLineHeightRatio, double canvasWidthRatio, double displayWidthRatio) {
//		RowConstraints canvasRow = new RowConstraints();
//		canvasRow.setPercentHeight(canvasHeightRatio);
//    	RowConstraints commandLineRow = new RowConstraints();
//    	commandLineRow.setPercentHeight(commandLineHeightRatio);
//    	gridPane.getRowConstraints().addAll(canvasRow,commandLineRow);
//    	
//    	ColumnConstraints canvasColumn = new ColumnConstraints();
//    	canvasColumn.setPercentWidth(canvasWidthRatio+.01);
//    	ColumnConstraints displayColumn = new ColumnConstraints();
//    	displayColumn.setPercentWidth(displayWidthRatio);
//    	gridPane.getColumnConstraints().addAll(canvasColumn,displayColumn);
	}

	@Override
	public void setSize(double width, double height) {
		// TODO Auto-generated method stub
		
	}
}
