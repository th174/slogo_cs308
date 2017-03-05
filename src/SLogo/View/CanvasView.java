package SLogo.View;

import javafx.scene.Node;

import java.io.File;
import java.util.Observer;

/**
 * Created by th174 on 2/19/2017.
 */
public interface CanvasView extends Observer {
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
     *
     * @return distance moved
     */
    double clearScreen();

    /**
     * @param index sets background color of screen to that represented by index
     * @return given index
     */
    int setBackground(double index);

    /**
     * @param index sets color of the pen to that represented by index
     * @return given index
     */
    int setPenColor(double index);

    /**
     * @param index sets size of the pen to be pixels thickness
     * @return given pixels
     */
    int setPenSize(double index);

    /**
     * @param index sets shape of turtle to that represented by index
     * @return given index
     */
    int setShape(double index);

    /**
     * sets color corresponding at given index to given r g b color values
     * note, color component values are nonnegative integers less than 256 specifying an amount of red, green, and blue
     *
     * @param index index
     * @param r     red
     * @param g     green
     * @param b     blue
     * @return given index
     */
    int setPalette(double index, double r, double g, double b);

    /**
     * @return turtle's current color index
     */
    int getPenColor();

    /**
     * @return turtle's current shape index
     */
    int getShape();
}