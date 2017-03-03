package SLogo.View;

import java.util.Observer;
import javafx.scene.Node;

public interface FunctionListView extends Observer{
	
	Node getView();
	
	void setSize(double width, double height);
}
