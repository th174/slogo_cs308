package SLogo.View;

import java.io.File;

import javafx.scene.Node;

/**
 * Created by th174 on 2/19/2017.
 */
public interface CanvasView {
    /**
     * @return Graphical JavaFX representation of the GUI
     */
    Node getView();

    /**
     * Sets Image
     *
     * @param bg background
     * @throws InvalidImageFileException 
     */
    void setImage(File imgFile);
    /**
     * Clears all lines from screen and instantiates a new Turtle.
     * @throws InvalidImageFileException 
     */
    void clearScreen();
    /**
	 * Return the absolute position of the CanvasView's Sprite.
	 * The position is represented as a 2d-array, with the
	 * x-coordinate being the first element and the
	 * y-coordinate being the second element.
	 * 
	 * @return	Sprite's absolute location
	 */
	public int[] getSpritePosition();
}