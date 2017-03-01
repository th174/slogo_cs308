package SLogo.View;

import SLogo.Repl;
import SLogo.FunctionEvaluate.EnvironmentImpl;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by th174 on 2/17/2017.
 */
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
	}
	
    @Override
    public Node getView() {
    	GridPane gridPane = new GridPane(); 
    	int SIZE = 900;
    	System.out.println("1");

    	System.out.println("1");
		myRoot = new Group();
		myWidth = width;
		myHeight = height;
    	GridPane gridPane = new GridPane();
    	double variableListHeightWeight = 4;
    	double functionListHeightWeight = 4;
    	double canvasHeightWeight = 8;
    	double commandLineHeightWeight = 2;
    	// Heights | Rows
    	double canvasHeightRatio = canvasHeightWeight/(canvasHeightWeight + commandLineHeightWeight);
    	double commandLineHeightRatio = commandLineHeightWeight/(canvasHeightWeight + commandLineHeightWeight);
    	double variableListHeightRatio = canvasHeightRatio * variableListHeightWeight/(variableListHeightWeight + functionListHeightWeight);
    	double functionListHeightRatio = canvasHeightRatio * functionListHeightWeight/(variableListHeightWeight + functionListHeightWeight);
    	// Widths | Columns
    	double canvasWidthWeight = 8;
    	double displayWidthWeight = 2;
    	double canvasWidthRatio = canvasWidthWeight/(canvasWidthWeight + displayWidthWeight);
    	double displayWidthRatio = displayWidthWeight/(canvasWidthWeight + displayWidthWeight);
    	setGridConstraints(gridPane, commandLineHeightRatio, variableListHeightRatio, functionListHeightRatio,
				canvasWidthRatio, displayWidthRatio);
    	System.out.println((int)(myWidth * canvasWidthRatio));
    	myCanvasView = new CanvasViewImpl((int)(myWidth * canvasWidthRatio),(int)(myHeight * canvasHeightRatio));

    	myRepl.setCanvas(myCanvasView);
    	Node canvasViewNode = myCanvasView.getView();
    	
    	Rectangle rectangleCanvasView = new Rectangle(myWidth * canvasWidthRatio,myHeight * canvasHeightRatio);
    	GridPane.setConstraints(rectangleCanvasView, 0, 0, 1, 2);
    	rectangleCanvasView.setFill(Color.AQUAMARINE);
    	
    	CommandLineView commandLine = new CommandLineViewBasic(myRepl,myWidth,myHeight*.2);
    	Node commandLineNode = commandLine.getView();
    	GridPane.setConstraints(commandLineNode, 0, 2, 2, 1);
    	
    	VariableListView variableListView = new VariableListViewBasic();
    	myEnv.addObserver(variableListView);
    	Node variableListViewNode = variableListView.getView();
    	GridPane.setConstraints(variableListViewNode, 1, 0);
    	Rectangle rectangleVariableView = new Rectangle(myWidth * displayWidthRatio,myHeight * variableListHeightRatio);
    	GridPane.setConstraints(rectangleVariableView, 1, 0);
    	rectangleVariableView.setFill(Color.GREENYELLOW);
    	
    	FunctionListView functionListView = new FunctionListViewBasic();
    	myEnv.addObserver(functionListView);
    	Node functionListViewNode = functionListView.getView();
    	GridPane.setConstraints(functionListViewNode, 1, 1);
    	Rectangle rectangleFunctionView = new Rectangle(myWidth * displayWidthRatio,myHeight * functionListHeightRatio);
    	GridPane.setConstraints(rectangleFunctionView, 1, 1);
    	rectangleFunctionView.setFill(Color.BLANCHEDALMOND);
    	
    	gridPane.getChildren().addAll(rectangleCanvasView,commandLineNode,
    			variableListViewNode,rectangleVariableView,functionListViewNode,rectangleFunctionView,canvasViewNode);
        myRoot.getChildren().addAll(gridPane,canvasViewNode);
        
	}
	
    @Override
    public Node getView() {
    	return myRoot;
    }

	private void setGridConstraints(GridPane gridPane, double commandLineHeightRatio, double variableListHeightRatio,
			double functionListHeightRatio, double canvasWidthRatio, double displayWidthRatio) {
		RowConstraints variableRow = new RowConstraints();
    	variableRow.setPercentHeight(variableListHeightRatio);
    	RowConstraints functionRow = new RowConstraints();
    	functionRow.setPercentHeight(functionListHeightRatio);
    	RowConstraints commandLineRow = new RowConstraints();
    	commandLineRow.setPercentHeight(commandLineHeightRatio);
    	gridPane.getRowConstraints().addAll(variableRow,functionRow,commandLineRow);
    	
    	ColumnConstraints canvasColumn = new ColumnConstraints();
    	canvasColumn.setPercentWidth(canvasWidthRatio);
    	ColumnConstraints displayColumn = new ColumnConstraints();
    	displayColumn.setPercentWidth(displayWidthRatio);
    	gridPane.getColumnConstraints().addAll(canvasColumn,displayColumn);
	}
}
