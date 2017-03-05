package SLogo.View;

import SLogo.Repl;
import SLogo.FunctionEvaluate.EnvironmentImpl;
import SLogo.View.Menu.MenuBarItems;
import SLogo.View.Menu.MenuBarItemsBasic;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SLogoGUIImpl implements SLogoGUI {
	
	private Repl myRepl;
	private CanvasView myCanvasView;
	private EnvironmentImpl myEnv;
	private Group myRoot;
	private double myWidth;
	private double myHeight;
	
	public SLogoGUIImpl(Repl repl,double width,double height){
		myRepl = repl;
		myEnv = (EnvironmentImpl) myRepl.getEnvironment();
		myRoot = new Group();
		myWidth = width;
		myHeight = height;

		GridPane gridPane = new GridPane();
    	double canvasHeightWeight = 8;
    	double commandLineHeightWeight = 2;
    	// Heights | Rows
    	double canvasHeightRatio = canvasHeightWeight/(canvasHeightWeight + commandLineHeightWeight);
    	double commandLineHeightRatio = commandLineHeightWeight/(canvasHeightWeight + commandLineHeightWeight);
    	// Widths | Columns
    	double canvasWidthWeight = 8;
    	double displayWidthWeight = 2;
    	double canvasWidthRatio = canvasWidthWeight/(canvasWidthWeight + displayWidthWeight);
    	double displayWidthRatio = displayWidthWeight/(canvasWidthWeight + displayWidthWeight);
    	setGridConstraints(gridPane, canvasHeightRatio, commandLineHeightRatio, canvasWidthRatio, displayWidthRatio);
    	
    	MenuBarItems menuBarItems = new MenuBarItemsBasic();
    	Node menuBarItemsNode = menuBarItems.getView();
    	
    	myCanvasView = new CanvasViewImpl((int)(myWidth * canvasWidthRatio),(int)(myHeight * canvasHeightRatio));
    	myRepl.setCanvas(myCanvasView);
    	Node canvasViewNode = myCanvasView.getView();
    	
    	Rectangle rectangleCanvasView = new Rectangle(myWidth * canvasWidthRatio,myHeight * canvasHeightRatio);
    	GridPane.setConstraints(rectangleCanvasView, 0, 0, 1, 1);
    	rectangleCanvasView.setFill(Color.AQUAMARINE);
    	
    	CommandLineView commandLine = new CommandLineViewBasic(myRepl,myWidth,myHeight*.2);
    	Node commandLineNode = commandLine.getView();
    	GridPane.setConstraints(commandLineNode, 0, 1, 2, 1);
    	
    	Rectangle rectangleMenuView = new Rectangle(width,height);
    	rectangleMenuView.setFill(Color.GREENYELLOW);
    	GridPane.setConstraints(rectangleMenuView, 1, 0);
    	
    	SLogoGUI menuItemTabPane = new MenuItemTabPane(commandLine, myEnv, myWidth * displayWidthRatio,myHeight * canvasHeightRatio);
    	Node menuItemTabPaneNode = menuItemTabPane.getView();
    	GridPane.setConstraints(menuItemTabPaneNode, 1, 0);
    	
    	gridPane.getChildren().addAll(rectangleCanvasView,menuItemTabPaneNode,commandLineNode);
        myRoot.getChildren().addAll(gridPane,canvasViewNode,menuBarItemsNode);
	}
	
    @Override
    public Node getView() {
    	return myRoot;
    }

	private void setGridConstraints(GridPane gridPane, double canvasHeightRatio, double commandLineHeightRatio, double canvasWidthRatio, double displayWidthRatio) {
		RowConstraints canvasRow = new RowConstraints();
		canvasRow.setPercentHeight(canvasHeightRatio);
    	RowConstraints commandLineRow = new RowConstraints();
    	commandLineRow.setPercentHeight(commandLineHeightRatio);
    	gridPane.getRowConstraints().addAll(canvasRow,commandLineRow);
    	
    	ColumnConstraints canvasColumn = new ColumnConstraints();
    	canvasColumn.setPercentWidth(canvasWidthRatio);
    	ColumnConstraints displayColumn = new ColumnConstraints();
    	displayColumn.setPercentWidth(displayWidthRatio);
    	gridPane.getColumnConstraints().addAll(canvasColumn,displayColumn);
	}

	@Override
	public void setSize(double width, double height) {
		// TODO Auto-generated method stub
		
	}
}
