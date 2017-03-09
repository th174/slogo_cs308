package SLogo.View;

import javafx.scene.Node;

public interface SLogoGUIElement {
	/**
     * @return View of GUI
     */
    Node getView();

	void setSize(double width, double height);
}
