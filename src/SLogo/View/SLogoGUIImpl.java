package SLogo.View;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 * Created by th174 on 2/17/2017.
 */
public class SLogoGUIImpl implements SLogoGUI {
    @Override
    public Node getView() {
    	GridPane gridPane = new GridPane();
    	
    	//CanvasView canvasView = new CanvasViewImpl();
    	
    	SLogoGUI commandLine = new CommandLineView();
    	Node commandLineNode = commandLine.getView();
    	GridPane.setConstraints(commandLineNode, 0, 1);
    	
    	SLogoGUI variableView = new VariableView();
    	Node variableViewNode = variableView.getView();
    	GridPane.setConstraints(variableViewNode, 1, 0);
    	
    	SLogoGUI functionView = new FunctionView();
    	Node functionViewNode = functionView.getView();
    	GridPane.setConstraints(functionViewNode, 1, 1);
    	
    	gridPane.getChildren().addAll(commandLineNode,variableViewNode,functionViewNode);
        return gridPane;
    }
}
