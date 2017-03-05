package SLogo.View;

import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 * Created by th174 on 2/16/2017.
 */
public interface SLogoGUI {
    /**
     * @return View of GUI
     */
    Node getView();

	void setSize(double width, double height);
	
	
	/**
	 * Sets background of turtle canvas to given color
	 * @param color
	 */
	void setTurtleBackgroundColor(Color color);
}
