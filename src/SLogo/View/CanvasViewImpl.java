package SLogo.View;

import SLogo.Turtles.ObservableTurtle;
import SLogo.Turtles.Turtle;
import SLogo.View.Sprite.Sprite;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class CanvasViewImpl extends Observable implements CanvasView {
    private static final String defaultMapPropertiesFilename = "data/defaultViewMapProperties.xml";
    private ResourceBundle exceptionResources;
    private static final String RESOURCE_FILEPATH = "resources/View/";
    private Group root;
    private int viewWidth;
    private int viewHeight;
    private int[] spriteDimensions;
    private int currentTurtleIMGIndex;
    private Map<Integer, Color> colorMap;
    private Map<Integer, Image> imageMap;
    private Rectangle backgroundNode;
    private double penColor;
    private double penWidth;
    private double backgroundColorIndex;

    private HashMap<Integer, Sprite> spriteMap;
    private HashMap<Integer, Boolean> turtleHiddenMap;
    private HashMap<Integer, Boolean> penDownMap;
    private HashMap<Integer, Double> currentImageIndexMap;

    public CanvasViewImpl(int aviewWidth, int aviewHeight) {
        exceptionResources = ResourceBundle.getBundle(RESOURCE_FILEPATH + "Exceptions");
        viewWidth = aviewWidth;
        viewHeight = aviewHeight;
        colorMap = new HashMap<Integer, Color>();
        imageMap = new HashMap<Integer, Image>();
        spriteMap = new HashMap<Integer, Sprite>();
        turtleHiddenMap = new HashMap<Integer, Boolean>();
        penDownMap = new HashMap<Integer, Boolean>();
        currentImageIndexMap = new HashMap<Integer, Double>();
        spriteDimensions = new int[2];
        XMLParser parser = new XMLParser();
        parser.populateMaps(spriteDimensions, colorMap, imageMap, defaultMapPropertiesFilename);
        currentTurtleIMGIndex = 0;
        backgroundColorIndex = 0;
        penColor = 1;
        penWidth = 1;
        root = new Group();
        backgroundNode = new Rectangle(viewWidth, viewHeight, colorMap.get(0));
        root.getChildren().add(backgroundNode);
        //TODO: notify GUI of maps
        //notifyObservers();
    }

    public CanvasViewImpl(int aviewWidth, int aviewHeight, ObservableMap<Integer, Turtle> turtles) {
        this(aviewWidth, aviewHeight);
        turtles.addListener(this::onTurtleMapChange);
    }

    //theoretically, when Stone makes first turtle, it will create an instance using instantializeSprite
    public void update(Observable o, Object n) {
        ObservableTurtle turtle = (ObservableTurtle) o;
        Pair<Double, Double> changeLoc = (Pair<Double, Double>) n;
        int currID = (int) turtle.id();
        Sprite currSprite = spriteMap.get(currID);
        setPen(currID, (boolean) turtle.isPenDown());
        currSprite.setDirection(((Double) turtle.getHeading()).intValue());
        move(currID, new double[]{changeLoc.getKey(), changeLoc.getValue()});
        setHidden(currID, !turtle.isTurtleShow());
        //TODO: need to set pen Color/Width at some point
    }

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
        penColor = index;
        return (int) index;
    }

    public int getPenColor() {
        for (HashMap.Entry<Integer, Color> e : colorMap.entrySet()) {
            Integer indexEntry = e.getKey();
            Color colorEntry = (Color) e.getValue();
            if (colorEntry.equals(colorMap.get(penColor))) {
                return indexEntry.intValue();
            }
        }
        //TODO: Learn how to throw exceptions
        throw new ErrorPrompt(exceptionResources.getString("InvalidColorIndex"));
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
        backgroundColorIndex = index;
        backgroundNode.setFill(colorMap.get(index));
        return (int) index;
    }

    public int setPalette(double index, double r, double g, double b) {
        Color color = Color.rgb((int) r, (int) g, (int) b, .99);
        colorMap.put((int) index, color);
        //notifyObservers();
        return (int) index;
    }

    public int[] getPalette(double index) {
        Color color = colorMap.get(index);
        return new int[]{(int) color.getRed(), (int) color.getGreen(), (int) color.getBlue()};
    }

    private void move(int currID, double[] vector) {
        Sprite currSprite = spriteMap.get(currID);
        vector[0] = Math.round(vector[0]);
        vector[1] = Math.round(vector[1]);
        ArrayList<double[]> linesToMake = new ArrayList<double[]>();
        TurtleMath.addLinesToMake(viewWidth, viewHeight, currSprite.getPosition(), vector, linesToMake);
        double[] finalPosition = currSprite.getPosition();
        for (double[] coordinates : linesToMake) {
            if (penDownMap.get(currID)) {
                Line line = new Line();
                line.setStartX(coordinates[0]);
                line.setStartY(coordinates[1]);
                line.setEndX(coordinates[2]);
                line.setEndY(coordinates[3]);
                line.setFill(colorMap.get(penColor));
                line.setStrokeWidth(penWidth);
                root.getChildren().add(line);
            }
            finalPosition = new double[]{coordinates[2], coordinates[3]};
        }
        currSprite.setPosition(finalPosition);
    }

    public void setImage(int currID, Image imgFile) throws FileNotFoundException {
        Sprite currSprite = spriteMap.get(currID);
        currentImageIndexMap.put(currID, (double) imageMap.size());
        imageMap.put(currentImageIndexMap.get(currID).intValue(), imgFile);
        currSprite.setImage(imgFile);
        //notifyObservers();
    }

    public void addImage(File imgFile) {
        int ID = imageMap.size();
        try {
            imageMap.put(ID, new Image(new FileInputStream(imgFile)));
        } catch (FileNotFoundException e) {
            throw new ErrorPrompt(exceptionResources.getString("InvalidImageFile"));
        }
        //notifyObservers();
    }

    public void setPen(int currID, boolean newPen) {
        penDownMap.put(currID, newPen);
        Sprite currSprite = spriteMap.get(currID);
        currSprite.setPen(newPen);
    }

    private void instantializeSprite(int ID, Turtle t) {
        currentImageIndexMap.put(ID, (double) currentTurtleIMGIndex);
        System.out.println(spriteDimensions[0]);
        spriteMap.put(ID, new Sprite(ID, imageMap.get(currentTurtleIMGIndex), spriteDimensions[0], spriteDimensions[1], viewWidth, viewHeight));
        root.getChildren().add(spriteMap.get(ID).getImageView());
        //notifyObservers();
    }

    public Node getView() {
        return root;
    }

    /**
     * @return Sprite's absolute location
     */
    public double[] getSpritePosition(int currID) {
        return TurtleMath.absoluteToZero(viewWidth, viewHeight, spriteMap.get(currID).getPosition());
    }

    @Override
    public double clearScreen() {
        for (Node n : root.getChildren()) {
            if (n.getClass().isInstance(new Rectangle())) {

            }
        }
        return 0;
    }
}