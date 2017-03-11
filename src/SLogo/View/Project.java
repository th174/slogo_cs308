package SLogo.View;

import java.util.ResourceBundle;

import SLogo.Repl;
import SLogo.ReplImpl;
import SLogo.View.DisplayBar.ItemDisplay;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class Project implements SLogoGUIElement {
	
	private Repl myRepl;
	private CanvasViewImpl myCanvasView;
	private Group myRoot;
	private double myWidth;
	private double myHeight;
	private final static String RESOURCES_PATH = "resources/View/";
	private final static String PROPERTIES_FILENAME = "Project";
	private ResourceBundle myResources;
	private CommandLineView myCommandLine;
	
	public Project(double width,double height){
		myRepl = new ReplImpl();
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
    	
    	myCanvasView = new CanvasViewImpl((int)(myWidth * canvasWidthRatio),(int)(myHeight * canvasHeightRatio),myRepl.getEnvironment().getAllTurtles());
    	Node canvasViewNode = myCanvasView.getView();
    	GridPane.setConstraints(canvasViewNode, 0, 1, 1, 1, HPos.CENTER, VPos.TOP);
    	myRepl.setCanvas(myCanvasView);
    	myCommandLine = new CommandLineViewBasic(this, myWidth,myHeight*commandLineHeightRatio);
    	Node commandLineNode = myCommandLine.getView();
    	GridPane.setConstraints(commandLineNode, 0, 2, 2, 1, HPos.CENTER, VPos.TOP);

    	SLogoGUIElement itemDisplay = new ItemDisplay(this, myWidth * displayWidthRatio,myHeight * canvasHeightRatio);
    	Node itemDisplayNode = itemDisplay.getView();
    	GridPane.setConstraints(itemDisplayNode, 1, 1, 1, 1, HPos.CENTER, VPos.TOP);
    	
    	gridPane.getChildren().addAll(itemDisplayNode,commandLineNode,canvasViewNode);
        myRoot.getChildren().addAll(gridPane);
	}
	
    @Override
    public Node getView() {
    	return myRoot;
    }

	private void initializeResources() {
		myResources = ResourceBundle.getBundle(RESOURCES_PATH + PROPERTIES_FILENAME);
	}
	
	public Repl getRepl(){
		return myRepl;
	}
	
	public CommandLineView getCommandLineView() {
		return myCommandLine;
	}
	
	public CanvasViewImpl getCanvasView(){
		return myCanvasView;
	}
}