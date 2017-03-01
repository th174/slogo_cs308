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
public class FunctionListViewBasic implements FunctionListView{
	VBox myFunctionListView;
	public FunctionListViewBasic() {
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
	@Override
	public void setSize(double width, double height) {
		// TODO Auto-generated method stub
		
	}

}
