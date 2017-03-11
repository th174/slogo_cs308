package SLogo.View;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.Map;
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
     * Adds image to saved image files
     *
     * @param filename 	filename of image to set
     * @return ID	ID of the turtle trying to change
     */
    void addImage(File imgFile);

    /**
     * Sets the image of the turtle with the given ID to the given Image File (and adds the image to the saved image files)
     * @param currID
     * @param imgFile
     */
    void setImage(int currID, Image imgFile);
    
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
     * @return current index representing the background color
     */
    int getBackground();

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
     * Returns the RGB values associated with that index
     * @param index
     * @return int array of the three RGB values
     */
    int[] getPalette(double index);

    /**
     * @return turtle's current color index
     * @throws ErrorPrompt 
     */
    int getPenColor();

    /**
     * @return turtle's current shape index
     */
    int getShape();

    /**
     * @return	map of Indices to Colors
     */
    Map<Integer,Color> getColorMap();
    
    /**
     * @return	map of Indices to Image
     */
    Map<Integer,Image> getImageMap();
}