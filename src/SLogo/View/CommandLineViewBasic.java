package SLogo.View;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;



public class CommandLineViewBasic implements CommandLineView{
	private final int TEXT_HEIGHT = 20;
	private final int TEXT_WIDTH  = 11;
	private GridPane myCommandLine;
	private Button myRunButton;
	private Button myClearButton;
	private TextArea myCommandText;
	
	
	
	public CommandLineViewBasic() {
		initializeGridPane();
    	initializeCommandPrompt();
    	initializeRunButton();
    	initializeClearButton();
    	myCommandLine.getChildren().addAll(myCommandText,myRunButton,myClearButton);
	}

	private void initializeClearButton() {
		myClearButton = new Button("PLACEHOLDER CLEAR");
		myClearButton.setPrefSize((87 - myCommandText.getPrefColumnCount()) * TEXT_WIDTH, myCommandText.getPrefRowCount() * TEXT_HEIGHT * (1-.8));
    	GridPane.setConstraints(myClearButton, 1, 1);
	}
	private void initializeRunButton() {
		myRunButton = new Button("PLACEHOLDER RUN");
    	myRunButton.setPrefSize((87 - myCommandText.getPrefColumnCount()) * TEXT_WIDTH, myCommandText.getPrefRowCount() * TEXT_HEIGHT * .8);
    	GridPane.setConstraints(myRunButton, 1, 0);
	}


	private void initializeCommandPrompt() {
		myCommandText = new TextArea();
    	myCommandText.setPromptText("Enter a command...");
    	GridPane.setConstraints(myCommandText, 0, 0, 1, 2);
	}


	private void initializeGridPane() {
		myCommandLine = new GridPane();
		ColumnConstraints commandTextColumn = new ColumnConstraints();
		commandTextColumn.setPercentWidth(80);
		ColumnConstraints runButtonColumn = new ColumnConstraints();
		runButtonColumn.setPercentWidth(20);
		myCommandLine.getColumnConstraints().addAll(commandTextColumn,runButtonColumn);
	}
	
	
	@Override
	public Node getView() {
		return myCommandLine;
	}

	@Override
	public void setSize(double width, double height) {
		
	}
}
