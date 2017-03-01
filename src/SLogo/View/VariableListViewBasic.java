package SLogo.View;

import java.util.Observable;
import java.util.Observer;

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

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
