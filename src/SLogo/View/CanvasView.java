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
    void setImage(File imgFile) throws InvalidImageFileException;
}
