package SLogo.View;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class VariableListViewBasic implements VariableListView {

	@Override
	public Node getView()  {
		VBox vBox = new VBox();
		return vBox;
	}

	@Override
	public void setSize(double width, double height) {
		// TODO Auto-generated method stub
		
	}

}
