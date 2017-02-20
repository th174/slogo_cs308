package SLogo.View;

import javafx.scene.Node;
import javafx.scene.layout.Background;

/**
 * Created by th174 on 2/19/2017.
 */
public interface CanvasView {
    /**
     * @return Graphical JavaFX representation of the GUI
     */
    Node getView();

    /**
     * Sets background
     *
     * @param bg background
     */
    void setBackground(Background bg);

    /**
     * Send move command to turtle
     *
     * @param movement The type of movement
     * @param distance distance to move
     */
    void move(String movement, double distance);
}
