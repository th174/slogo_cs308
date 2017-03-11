package SLogo.View;

import SLogo.Turtles.ObservableTurtle;
import SLogo.Turtles.Turtle;
import SLogo.View.Sprite.Sprite;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This class implements CanvasView to handle all View properties and Turtle properties for SLogo
 *
 * @author Riley Nisbet
 */

public class CanvasViewImpl extends Observable implements CanvasView {
    private static final String defaultMapPropertiesFilename = "data/defaultViewMapProperties.xml";
    private ResourceBundle exceptionResources;
    private static final String RESOURCE_FILEPATH = "resources/View/";
    private Pane root;
    private int viewWidth;
    private int viewHeight;
    private int[] spriteDimensions;
    private int currentTurtleIMGIndex;
    private Map<Integer, Color> colorMap;
    private Map<Integer, Image> imageMap;
    private Rectangle backgroundNode;
    private CanvasPropertiesDisplay propDisp;
    private int penColor;
    private double penWidth;
    private int backgroundColorIndex;
    private TurtleMath tMath;

    private HashMap<Integer, Sprite> spriteMap;
    private HashMap<Integer, Boolean> turtleHiddenMap;
    private HashMap<Integer, Boolean> penDownMap;
    private HashMap<Integer, Double> currentImageIndexMap;

    public CanvasViewImpl(int aviewWidth, int aviewHeight) {
        exceptionResources = ResourceBundle.getBundle(RESOURCE_FILEPATH + "Exceptions");
        viewWidth = aviewWidth;
        viewHeight = aviewHeight;
        colorMap = new HashMap<>();
        imageMap = new HashMap<>();
        spriteMap = new HashMap<>();
        turtleHiddenMap = new HashMap<>();
        penDownMap = new HashMap<>();
        currentImageIndexMap = new HashMap<>();
        spriteDimensions = new int[2];
        tMath = new TurtleMath();
        XMLParser parser = new XMLParser();
        parser.populateMaps(spriteDimensions, colorMap, imageMap, defaultMapPropertiesFilename);
        currentTurtleIMGIndex = 0;
        backgroundColorIndex = 0;
        penColor = 1;
        penWidth = 1;
        root = new Pane();
        backgroundNode = new Rectangle(viewWidth, viewHeight, colorMap.get(0));
        propDisp = new CanvasPropertiesDisplay(this, colorMap);
        backgroundNode.setOnMouseClicked(e -> propDisp.toggleDisplay());
        root.getChildren().add(backgroundNode);
        notifyObservers();
    }

    public CanvasViewImpl(int aviewWidth, int aviewHeight, ObservableMap<Integer, Turtle> turtles) {
        this(aviewWidth, aviewHeight);
        turtles.addListener(this::onTurtleMapChange);
        for (Integer id : turtles.keySet()) {
            instantializeSprite(id, turtles.get(id));
            turtles.get(id).addObserver(this);
        }
    }

    public void update(Observable o, Object n) {
        ObservableTurtle turtle = (ObservableTurtle) o;
        @SuppressWarnings("unchecked")
		Pair<Double, Double> changeLoc = (Pair<Double, Double>) n;
        int currID = (int) turtle.id();
        Sprite currSprite = spriteMap.get(currID);
        setPen(currID, (boolean) turtle.isPenDown());
        currSprite.setDirection((int) turtle.getHeading());
        move(currID, new double[]{changeLoc.getKey(), changeLoc.getValue()});
        setHidden(currID, !turtle.isTurtleShow());
    }

    /**
     * Listener that provides a turtle that is added or removed from the back-end list of turtles
     *
     * @param turtlesChange turtle that is changed
     */
    private void onTurtleMapChange(MapChangeListener.Change<? extends Integer, ? extends Turtle> turtlesChange) {
        int id = turtlesChange.getKey();
        Turtle t = turtlesChange.getValueAdded();
        Turtle r = turtlesChange.getValueRemoved();
        if (t != null) {
            instantializeSprite(id, t);
            t.addObserver(this);
        }
        if (r != null) {
            removeSprite(id, r);
        }
    }

    /**
     * Remove references to sprite from front end
     *
     * @param id
     * @param r
     */
    private void removeSprite(int id, Turtle r) {
        Sprite toRemove = spriteMap.remove(id);
        turtleHiddenMap.remove(id);
        penDownMap.remove(id);
        currentImageIndexMap.remove(id);
        root.getChildren().remove(toRemove.getImageView());
    }

    private void setHidden(int currID, boolean hidden) {
        Sprite currSprite = spriteMap.get(currID);
        currSprite.setHidden(hidden);
        turtleHiddenMap.put(currID, hidden);
    }

    public int setPenColor(double index) {
        penColor = (int) index;
        return (int) index;
    }

    public int getPenColor() {
        for (HashMap.Entry<Integer, Color> e : colorMap.entrySet()) {
            Integer indexEntry = e.getKey();
            Color colorEntry = (Color) e.getValue();
            if (colorEntry.equals(colorMap.get(penColor))) {
                return indexEntry;
            }
        }
        throw new ErrorPrompt(exceptionResources.getString("InvalidColorIndex"));
    }
    
    public double getPenWidth() {
        return penWidth;
    }

    public int setPenSize(double index) {
        penWidth = (int) index;
        return (int) index;
    }

    public int setShape(double index) {
        currentTurtleIMGIndex = (int) index;
        return currentTurtleIMGIndex;
    }

    public int getShape() {
        return currentTurtleIMGIndex;
    }

    public int setBackground(double index) {
        backgroundColorIndex = (int) index;
        backgroundNode.setFill(colorMap.get(backgroundColorIndex));
        return (int) index;
    }

    public int getBackground() {
        return (int) backgroundColorIndex;
    }

    public int setPalette(double index, double r, double g, double b) {
        Color color = Color.rgb((int) r, (int) g, (int) b, .99);
        colorMap.put((int) index, color);
        notifyObservers();
        return (int) index;
    }

    public int[] getPalette(double index) {
        Color color = colorMap.get(index);
        return new int[]{(int) color.getRed(), (int) color.getGreen(), (int) color.getBlue()};
    }

    /**
     * Move the turtle with the associated ID along the given vector
     *
     * @param currID
     * @param vector
     */
    private void move(int currID, double[] vector) {
        Sprite currSprite = spriteMap.get(currID);
        ArrayList<double[]> linesToMake = new ArrayList<double[]>();
        tMath.addLinesToMake(viewWidth, viewHeight, currSprite.getPosition(), vector, linesToMake);
        double[] finalPosition = currSprite.getPosition();
        for (double[] coordinates : linesToMake) {
            if (penDownMap.get(currID)) {
                Line line = new Line();
                line.setStartX(coordinates[0]);
                line.setStartY(coordinates[1]);
                line.setEndX(coordinates[2]);
                line.setEndY(coordinates[3]);
                line.setStroke(colorMap.get(penColor));
                line.setStrokeWidth(penWidth);
                root.getChildren().add(line);
            }
            finalPosition = new double[]{coordinates[2], coordinates[3]};
        }
        currSprite.setPosition(finalPosition);
    }

    public void setImage(int currID, Image imgFile) {
        Sprite currSprite = spriteMap.get(currID);
        currentImageIndexMap.put(currID, (double) imageMap.size());
        imageMap.put(currentImageIndexMap.get(currID).intValue(), imgFile);
        currSprite.setImage(imgFile);
        notifyObservers();
    }

    public void addImage(File imgFile) {
        int ID = imageMap.size();
        try {
            imageMap.put(ID, new Image(new FileInputStream(imgFile)));
        } catch (FileNotFoundException e) {
            throw new ErrorPrompt(exceptionResources.getString("InvalidImageFile"));
        }
    }

    public void setPen(int currID, boolean newPen) {
        penDownMap.put(currID, newPen);
        Sprite currSprite = spriteMap.get(currID);
        currSprite.setPen(newPen);
    }

    /**
     * Creates an instance of the sprite (with given ID) on the front end with all relevent properties
     *
     * @param ID
     * @param t
     */
    private void instantializeSprite(int ID, Turtle t) {
        currentImageIndexMap.put(ID, (double) currentTurtleIMGIndex);
        Sprite newSprite = new Sprite(ID, imageMap.get(currentTurtleIMGIndex), spriteDimensions[0], spriteDimensions[1], viewWidth, viewHeight, t);
        spriteMap.put(ID, newSprite);
        root.getChildren().add(spriteMap.get(ID).getImageView());
        setPen(ID, (boolean) t.isPenDown());
        newSprite.setDirection((int) t.getHeading());
        setHidden(ID, !t.isTurtleShow());
    }

    public Node getView() {
        return root;
    }

    public double clearScreen() {
        root.getChildren().removeIf(n -> n instanceof Line);
        return 0;
    }

    public Map<Integer, Color> getColorMap() {
        return colorMap;
    }

    public Map<Integer, Image> getImageMap() {
        return imageMap;
    }
}