package SLogo.View;

import SLogo.FunctionEvaluate.Functions.Invokable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;


/**
 * 
 * @author Alex
 *
 */
public class FunctionListView implements SLogoGUI{
	VBox myFunctionListView;
	public FunctionListView() {
		VBox vBox = new VBox();
		myFunctionListView = vBox;
	}
	/**
	 * Used by the main gui to add the node to the main
	 */
	@Override
	public Node getView() {
		return myFunctionListView;
	}
	/**
	 * Call when the function list changes
	 * @param invokable
	 */
	private void addFunction(Invokable invokable) {
		TextArea text = new TextArea("FunctionStringThing");
		text.setEditable(false);
		text.setOnMouseClicked(e -> addToCommandLine());
		//Set Size
		myFunctionListView.getChildren().add(text);
	}
	private void addToCommandLine() {
		
	}

}
