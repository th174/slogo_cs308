package SLogo.View.DisplayBar;

import SLogo.View.SLogoGUIElement;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class IndexNode implements Comparable<IndexNode>, SLogoGUIElement {
	private int myIndex;
	private Node myNode;
	private GridPane myRoot = new GridPane();
	public IndexNode(int index, Node node) {
		myIndex = index;
		Text myIndexText = new Text("" + myIndex);
		GridPane.setConstraints(myIndexText, 0, 0);
		myNode = node;
		GridPane.setConstraints(myNode, 1, 0);
		myRoot.getChildren().addAll(myIndexText,myNode);
	}
	
	@Override
	public int compareTo(IndexNode o) {
		return myIndex-o.myIndex;
	}

	@Override
	public Node getView() {
		return myRoot;
	}

	@Override
	public void setSize(double width, double height) {
		// TODO Auto-generated method stub
		
	}
	
}
