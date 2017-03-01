package SLogo.View;

import SLogo.Repl;
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
	
	public SLogoGUIImpl(Repl repl){
		myRepl = repl;
	}
	
    @Override
    public Node getView() {
    	GridPane gridPane = new GridPane(); 
    	int SIZE = 900;
    	System.out.println("1");

    	System.out.println("1");
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
    	
    	
    	myCanvasView = new CanvasViewImpl((int)(SIZE * canvasWidthRatio),(int)(SIZE * canvasHeightRatio));

    	myRepl.setCanvas(myCanvasView);
    	Node canvasViewNode = myCanvasView.getView();
    	GridPane.setConstraints(canvasViewNode, 0, 0);
    	
    	
    	
    	Rectangle rectangleCanvasView = new Rectangle(SIZE * canvasWidthRatio,SIZE * canvasHeightRatio);
    	GridPane.setConstraints(rectangleCanvasView, 0, 0, 1, 2);
    	rectangleCanvasView.setFill(Color.AQUAMARINE);
    	
    	CommandLineView commandLine = new CommandLineViewBasic(myRepl);
    	Node commandLineNode = commandLine.getView();
    	GridPane.setConstraints(commandLineNode, 0, 2, 2, 1);
    	
    	VariableListView variableListView = new VariableListViewBasic();
    	Node variableListViewNode = variableListView.getView();
    	GridPane.setConstraints(variableListViewNode, 1, 0);
    	Rectangle rectangleVariableView = new Rectangle(SIZE * displayWidthRatio,SIZE * variableListHeightRatio);
    	GridPane.setConstraints(rectangleVariableView, 1, 0);
    	rectangleVariableView.setFill(Color.GREENYELLOW);
    	
    	FunctionListView functionListView = new FunctionListViewBasic();
    	Node functionListViewNode = functionListView.getView();
    	GridPane.setConstraints(functionListViewNode, 1, 1);
    	Rectangle rectangleFunctionView = new Rectangle(SIZE * displayWidthRatio,SIZE * functionListHeightRatio);
    	GridPane.setConstraints(rectangleFunctionView, 1, 1);
    	rectangleFunctionView.setFill(Color.BLANCHEDALMOND);
    	
    	gridPane.getChildren().addAll(rectangleCanvasView,commandLineNode,
    			variableListViewNode,rectangleVariableView,functionListViewNode,rectangleFunctionView,canvasViewNode);
        return gridPane;
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
