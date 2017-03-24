// This entire file is part of my masterpiece.
// Riley Nisbet

package SLogo.View.Sprite;

import java.util.Observable;
import java.util.Observer;

import SLogo.Turtles.ObservableTurtle;
import SLogo.Turtles.Turtle;
import SLogo.View.TurtleMath;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class is the Sprite object used by CanvasView to be able to keep track of Turtles (and their properties) on the front end
 * Throughout the project, this class had to change significantly to reflect the constantly-changing data path between the front-
 * end and back end in regards to the turtle's position and properties. I particularly refactored this class to implement the Observer
 * pattern so that it can be added as an observer to each turtle. By doing this, there is a indirect path between the back and front 
 * end that is more efficient than having to pass this information along individually. As a result, the class becomes more passive and 
 * requires less attention.
 *
 * @author Riley Nisbet
 */

public class Sprite implements Observer{
    private int myID;
    private Image defaultSpriteIMG;
    private ImageView spriteIV;
    private Image spriteImg;
    private int spriteWidth;
    private int spriteHeight;
    private int viewWidth;
    private int viewHeight;
    private Turtle myTurtRef;
    private int direction;
    private double[] position;
    private boolean hidden;
    private boolean penDown;
    private TurtlePropertiesDisplay propDisp;
    private TurtleMath tMath;

    public Sprite(int ID, Image adefaultSpriteIMG, int aspriteWidth, int aspriteHeight, int aviewWidth, int aviewHeight, Turtle turtRef) {
        myID = ID;
        spriteWidth = aspriteWidth;
        spriteHeight = aspriteHeight;
        viewWidth = aviewWidth;
        viewHeight = aviewHeight;
        myTurtRef = turtRef;
        defaultSpriteIMG = adefaultSpriteIMG;
        spriteIV = new ImageView();
        setDefaults();
        tMath = new TurtleMath();
        propDisp = new TurtlePropertiesDisplay(myID, tMath.absoluteToZero(viewWidth, viewHeight, getPosition()),
                getDirection(), getHidden(), penDown, myTurtRef);
        spriteIV.setOnMouseClicked(e -> propDisp.toggleDisplay());
    }

	private void setDefaults() {
		setImage(defaultSpriteIMG);
        position = new double[]{viewWidth / 2, viewHeight / 2};
        setPosition(position);
        setDirection(90);
        setHidden(false);
	}
    
    /**
     * This is the method that's called whenever ObservableTurtle is changed so that this class can update it's properties
     */
    public void update(Observable o, Object n) {
        ObservableTurtle turtle = (ObservableTurtle) o;
        setPen(turtle.isPenDown());
        setDirection((int) turtle.getHeading());
        setHidden(!turtle.isTurtleShow());
    }

    /**
     * Set the image of this sprite to the given image
     *
     * @param newSpriteIMG
     */
    public void setImage(Image newSpriteIMG) {
        spriteImg = newSpriteIMG;
        spriteIV.setImage(spriteImg);
        spriteIV.setFitWidth(spriteWidth);
        spriteIV.setFitHeight(spriteHeight);
    }

    /**
     * Set the pen to up (false) or down (true)
     *
     * @param newPen
     */
    public void setPen(boolean newPen) {
        penDown = newPen;
    }

    /**
     * @return	instance of ImageView
     */
    public ImageView getImageView() {
        return spriteIV;
    }

    /**
     * @return Array of length 2: First element is the X-coordinate, second element is the Y-coordinate. Center is (0,0)
     */
    public double[] getPosition() {
        return position;
    }
    public void setPosition(double[] newPos) {
        position = newPos;
        spriteIV.setX(position[0] - spriteWidth / 2);
        spriteIV.setY(position[1] - spriteHeight / 2);
    }

    public double getDirection() {
        return direction + 90;
    }

    public void setDirection(int degrees) {
        direction = 90 - degrees;
        spriteIV.setRotate(direction);
    }

    public boolean getHidden() {
        return hidden;
    }

    public void setHidden(boolean newHidden) {
        hidden = newHidden;
        spriteIV.setVisible(!hidden);
    }

    public int[] getViewSize() {
        return new int[]{viewWidth, viewHeight};
    }
}
