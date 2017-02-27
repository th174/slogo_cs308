package SLogo.View;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class CommandLineView implements SLogoGUI{

	@Override
	public Node getView() {
		GridPane commandLine = new GridPane();
    	TextArea commandText = new TextArea();
    	GridPane.setConstraints(commandText, 0, 0);
    	Button runButton = new Button("PLACEHOLDER RUN");
    	GridPane.setConstraints(runButton, 1, 0);
    	return commandLine;
	}

}
