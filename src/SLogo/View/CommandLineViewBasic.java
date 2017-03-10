package SLogo.View;

import java.util.ResourceBundle;

import SLogo.Repl;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class CommandLineViewBasic implements CommandLineView {
    private final int TEXT_HEIGHT = 20;
    private final int TEXT_WIDTH = 11;
    private GridPane myCommandLine;
    private Button myRunButton;
    private Button myClearButton;
    private TextArea myCommandText;
    private Repl myRepl;
    private double myWidth;
    private double myHeight;
	private CanvasView myCanvasView;
	private final static String RESOURCES_PATH = "resources/View/";
	private final static String PROPERTIES_FILENAME = "CommandLine";
	private ResourceBundle myResources;


    public CommandLineViewBasic(Repl repl, CanvasView canvasView, double width, double height) {
        myRepl = repl;
        myWidth = width;
        myHeight = height;
        myCanvasView = canvasView;
		initializeResources();
        initializeGridPane();
        initializeCommandPrompt();
        initializeRunButton();
        initializeClearButton();
        myCommandLine.getChildren().addAll(myCommandText, myRunButton, myClearButton);
    }

    private void initializeResources() {
    	myResources = ResourceBundle.getBundle(RESOURCES_PATH + PROPERTIES_FILENAME);
	}

	private void initializeClearButton() {
        myClearButton = new Button(myResources.getString("ClearButton"));
        myClearButton.setPrefSize(((87 * myWidth / 1000) - myCommandText.getPrefColumnCount()) * TEXT_WIDTH, myCommandText.getPrefRowCount() * TEXT_HEIGHT * (1 - .8));
        GridPane.setConstraints(myClearButton, 1, 1);
        myClearButton.setOnAction(e -> clearScreen());
    }

    private void clearScreen() {
		myCanvasView.clearScreen();
	}

	private void initializeRunButton() {
		myRunButton = new Button();        
   		myRunButton.setText(myResources.getString("RunButtonText"));
   		myRunButton.setPrefSize(((87 * myWidth / 1000) - myCommandText.getPrefColumnCount()) * TEXT_WIDTH, myCommandText.getPrefRowCount() * TEXT_HEIGHT * .8);
        GridPane.setConstraints(myRunButton, 1, 0);
        myRunButton.setOnAction(e -> sendCommand());
    }

    private void sendCommand() {
        try {
			myRepl.read(myCommandText.getText());
        }catch (Exception e) {
        	Alert commandErrorAlert = new Alert(AlertType.ERROR);
        	commandErrorAlert.setTitle(myResources.getString("AlertError"));
        	commandErrorAlert.setHeaderText(myResources.getString("CommandNotRecognized"));
        	commandErrorAlert.setContentText(e.getClass().getName());
        	commandErrorAlert.showAndWait();
        }
        myCommandText.clear();
    }

    private void initializeCommandPrompt() {
        myCommandText = new TextArea();
        myCommandText.setPrefColumnCount((int) (myCommandText.getPrefColumnCount() * (1.72 * myWidth / 1000)));
        myCommandText.setPrefRowCount((int) (myCommandText.getPrefRowCount() * (myHeight / 200)));
        myCommandText.setPromptText("Enter a command...");
        GridPane.setConstraints(myCommandText, 0, 0, 1, 2);
    }

    private void initializeGridPane() {
        myCommandLine = new GridPane();
    }


    @Override
    public Node getView() {
        return myCommandLine;
    }

	@Override
	public void setText(String command) {
		myCommandText.setText(command);
		
	}
}
