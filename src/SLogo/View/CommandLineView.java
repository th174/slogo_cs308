package SLogo.View;

import javafx.scene.Node;
/**
 * Interface for the CommandLine. 
 * @author Alex Salas
 *
 */
public interface CommandLineView {

		public Node getView();
		public void setSize(double width, double height);
}
