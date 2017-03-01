package SLogo.View;

import java.util.Observer;
import javafx.scene.Node;

public interface FunctionListView extends Observer{
	
	public Node getView();
	
	public void setSize(double width, double height);
}
