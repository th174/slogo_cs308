package SLogo.View;

import java.util.Observer;
import javafx.scene.Node;
/**
 * Interface for the variable list.
 * @author Alex
 *
 */
public interface VariableListView extends Observer{

	public Node getView();

	public void setSize(double width, double height);
}
