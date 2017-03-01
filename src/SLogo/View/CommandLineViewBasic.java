package SLogo.View;

import SLogo.Repl;
import javafx.scene.Node;
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


    public CommandLineViewBasic(Repl repl, double width, double height) {
        myRepl = repl;
        myWidth = width;
        myHeight = height;
        initializeGridPane();
        initializeCommandPrompt();
        initializeRunButton();
        initializeClearButton();
        myCommandLine.getChildren().addAll(myCommandText, myRunButton, myClearButton);
    }

    private void initializeClearButton() {
        myClearButton = new Button("PLACEHOLDER CLEAR");
        myClearButton.setPrefSize(((87 * myWidth / 1000) - myCommandText.getPrefColumnCount()) * TEXT_WIDTH, myCommandText.getPrefRowCount() * TEXT_HEIGHT * (1 - .8));
        GridPane.setConstraints(myClearButton, 1, 1);
    }

    private void initializeRunButton() {
        myRunButton = new Button("PLACEHOLDER RUN");
        myRunButton.setPrefSize(((87 * myWidth / 1000) - myCommandText.getPrefColumnCount()) * TEXT_WIDTH, myCommandText.getPrefRowCount() * TEXT_HEIGHT * .8);
        GridPane.setConstraints(myRunButton, 1, 0);
        myRunButton.setOnAction(e -> sendCommand());
    }

    private void sendCommand() {
        try {
            myRepl.read(myCommandText.getText());
        } catch (Exception e) {
            e.printStackTrace();
            // TODO Make Alert
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
    public void setSize(double width, double height) {

    }
}
