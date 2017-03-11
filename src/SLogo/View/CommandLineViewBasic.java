package SLogo.View;

import java.util.Arrays;
import java.util.ResourceBundle;

import SLogo.Repl;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
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
	private TextArea myHistoryText;


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

    private void initializeResources() {
    	myResources = ResourceBundle.getBundle(RESOURCES_PATH + PROPERTIES_FILENAME);
	}

	private void initializeClearButton() {
        myClearButton = new Button(myResources.getString("ClearButton"));
        myClearButton.setPrefSize(((87 * myWidth / 1000) - myCommandText.getPrefColumnCount()) * TEXT_WIDTH, myCommandText.getPrefRowCount() * TEXT_HEIGHT * 1);
        GridPane.setConstraints(myClearButton, 1, 1);
        myClearButton.setOnAction(e -> clearScreen());
    }

    private void clearScreen() {
		myCanvasView.clearScreen();
	}

	private void initializeRunButton() {
		myRunButton = new Button();        
   		myRunButton.setText(myResources.getString("RunButtonText"));
   		myRunButton.setPrefSize(((87 * myWidth / 1000) - myCommandText.getPrefColumnCount()) * TEXT_WIDTH, myCommandText.getPrefRowCount() * TEXT_HEIGHT * 1);
        GridPane.setConstraints(myRunButton, 1, 0);
        myRunButton.setOnAction(e -> sendCommand());
    }

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

    private void initializeCommandPrompt() {
        myCommandText = new TextArea();
        myCommandText.setPrefColumnCount((int) (myCommandText.getPrefColumnCount() * (1.74 * myWidth / 1000)));
        myCommandText.setPrefRowCount((int) (myCommandText.getPrefRowCount() * (myHeight / 400)));
        myCommandText.setPromptText("Enter a command...");
        GridPane.setConstraints(myCommandText, 0, 0, 1, 1);
    }


    private void initializeHistory() {
        myHistoryText = new TextArea();
        myHistoryText.setPrefColumnCount((int) (myHistoryText.getPrefColumnCount() * (1.74 * myWidth / 1000)));
        myHistoryText.setPrefRowCount((int) (myHistoryText.getPrefRowCount() * (myHeight / 400)));
        myHistoryText.setEditable(false);
        GridPane.setConstraints(myCommandText, 0, 1, 1, 1);
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