package SLogo.View;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;



public class CommandLineViewBasic implements CommandLineView{
	private final int TEXT_HEIGHT = 20;
	private final int TEXT_WIDTH  = 11;
	private Node myCommandLine;
	
	public CommandLineViewBasic() {
		int prefColumns = (int)(87-88*.2);
		GridPane commandLine = new GridPane();
    	TextArea commandText = new TextArea();
    	commandText.setPromptText("Enter a command...");
    	commandText.setPrefColumnCount(prefColumns); //87 - 1000
    	GridPane.setConstraints(commandText, 0, 0);
    	Button runButton = new Button("PLACEHOLDER RUN");
    	GridPane.setConstraints(runButton, 1, 0);
    	commandLine.getChildren().addAll(commandText,runButton);
    	runButton.setPrefSize((87 - commandText.getPrefColumnCount()) * TEXT_WIDTH, commandText.getPrefRowCount() * TEXT_HEIGHT);
    	myCommandLine = commandLine;
	}
	
	
	@Override
	public Node getView() {
		return myCommandLine;
	}

}
