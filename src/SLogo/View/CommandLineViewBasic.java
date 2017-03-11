package SLogo.View;

import java.util.ResourceBundle;

import SLogo.Repl;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

/**
 * Commandline takes in commands and passes it to the Repl
 * @author Alex
 *
 */
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
	private TextArea myHistoryText;

/**
 * Creates commandline
 * @param project
 * @param width
 * @param height
 */
    public CommandLineViewBasic(Project project, double width, double height) {
        myRepl = project.getRepl();
        myWidth = width;
        myHeight = height;
        myCanvasView = project.getCanvasView();
		initializeResources();
        initializeGridPane();
        initializeCommandPrompt();
        initializeHistory();
        initializeRunButton();
        initializeClearButton();
        myCommandLine.getChildren().addAll(myCommandText,myHistoryText,myRunButton, myClearButton);
    }
    
/**
 * Creates resource bundle
 */
    private void initializeResources() {
    	myResources = ResourceBundle.getBundle(RESOURCES_PATH + PROPERTIES_FILENAME);
	}
    
/**
 * Creates clearall button
 */
	private void initializeClearButton() {
        myClearButton = new Button(myResources.getString("ClearButton"));
        myClearButton.setPrefSize(((87 * myWidth / 1000) - myCommandText.getPrefColumnCount()) * TEXT_WIDTH, myCommandText.getPrefRowCount() * TEXT_HEIGHT * 1);
        GridPane.setConstraints(myClearButton, 1, 1);
        myClearButton.setOnAction(e -> clearScreen());
    }

	/**
	 * clears the screen
	 */
    private void clearScreen() {
		myCanvasView.clearScreen();
	}

    /**
     * Creates the run button
     */
	private void initializeRunButton() {
		myRunButton = new Button();        
   		myRunButton.setText(myResources.getString("RunButtonText"));
   		myRunButton.setPrefSize(((87 * myWidth / 1000) - myCommandText.getPrefColumnCount()) * TEXT_WIDTH, myCommandText.getPrefRowCount() * TEXT_HEIGHT * 1);
        GridPane.setConstraints(myRunButton, 1, 0);
        myRunButton.setOnAction(e -> sendCommand());
    }

	/**
	 * Sends the command to REPL
	 */
    private void sendCommand() {
    	String historyText = "Failed Command... \n";
        try {
			myRepl.read(myCommandText.getText());
			historyText = myCommandText.getText().trim() + "\n";
        }catch (Exception e) {
            e.printStackTrace();
        	Alert commandErrorAlert = new Alert(AlertType.ERROR);
        	commandErrorAlert.setTitle(myResources.getString("AlertError"));
        	commandErrorAlert.setHeaderText(myResources.getString("CommandNotRecognized"));
        	commandErrorAlert.setContentText(e.getClass().getSimpleName()+"\n"+e.getMessage());
            commandErrorAlert.showAndWait();
        }
        myHistoryText.setText(myHistoryText.getText() + myResources.getString("CommandBreak") + historyText);
        myHistoryText.setScrollTop(myHistoryText.getScrollTop()+Integer.MAX_VALUE);
        myCommandText.clear();
    }

    /**
     * Initializes the command prompt
     */
    private void initializeCommandPrompt() {
        myCommandText = new TextArea();
        myCommandText.setPrefColumnCount((int) (myCommandText.getPrefColumnCount() * (1.74 * myWidth / 1000)));
        myCommandText.setPrefRowCount((int) (myCommandText.getPrefRowCount() * (myHeight / 400)));
        myCommandText.setPromptText("Enter a command...");
        GridPane.setConstraints(myCommandText, 0, 0, 1, 1);
    }

    /**
     * Initializes the history text
     */
    private void initializeHistory() {
        myHistoryText = new TextArea();
        myHistoryText.setPrefColumnCount((int) (myHistoryText.getPrefColumnCount() * (1.74 * myWidth / 1000)));
        myHistoryText.setPrefRowCount((int) (myHistoryText.getPrefRowCount() * (myHeight / 400)));
        myHistoryText.setEditable(false);
        GridPane.setConstraints(myCommandText, 0, 1, 1, 1);
    }
    
    /**
     * makes the gridpane
     */
    private void initializeGridPane() {
        myCommandLine = new GridPane();
    }

    /**
     * Gets the node
     */
    @Override
    public Node getView() {
        return myCommandLine;
    }
    
    /**
     * Sets the command text
     */
	@Override
	public void setText(String command) {
		myCommandText.setText(command);
		
	}
}