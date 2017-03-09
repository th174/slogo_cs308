package SLogo.View;

import SLogo.Turtles.Turtle;
import SLogo.Turtles.ObservableTurtle;
import SLogo.View.Sprite.Sprite;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.io.File;
import java.util.*;

public class CanvasViewImpl extends Observable implements CanvasView {
	private static final String defaultMapPropertiesFilename = "data/defaultViewMapProperties.xml";
	private ResourceBundle exceptionResources;
	private static final String RESOURCE_FILEPATH = "resources/View/";
	private SLogoGUI gui;
	private Group root;
	private int viewWidth;
	private int viewHeight;
	private int spriteWidth;
	private int spriteHeight;
	private int defaultTurtleFile;
	private Map<Double, Color> colorMap;
	private Map<Double, File> imageMap;
	private Rectangle backgroundNode;
	private double backgroundColor;
	private double penColor;
	private double penWidth;
	private int currentShapeIndex;

	private HashMap<Integer, Sprite> spriteMap;
	private HashMap<Integer, Boolean> turtleHiddenMap;
	private HashMap<Integer, Boolean> penDownMap;
	private HashMap<Integer, Double> currentImageIndexMap;

	public CanvasViewImpl(int aviewWidth, int aviewHeight) {
		//TODO: get SlogoGUI instance to change background
		//gui = SLogoGUI;
		exceptionResources = ResourceBundle.getBundle(RESOURCE_FILEPATH + "Exceptions");
		viewWidth = aviewWidth;
		viewHeight = aviewHeight;
		colorMap = new HashMap<Double, Color>();
		imageMap = new HashMap<Double, File>();
		XMLParser.populateMaps(colorMap, imageMap, defaultMapPropertiesFilename);
		currentShapeIndex = 0;
		defaultTurtleFile = currentShapeIndex;
		backgroundColor = 0;
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
	public void update(Observable o, Object n){
		ObservableTurtle turtle = (ObservableTurtle) o;
		Pair<Integer, Integer> changeLoc = (Pair<Integer, Integer>) n;
		int currID = (int) turtle.id();
		Sprite currSprite = spriteMap.get(currID);
		setPen(currID, (boolean) turtle.isPenDown());
		currSprite.setDirection(((Double) turtle.getHeading()).intValue());
		move(currID, new int[] {changeLoc.getKey(), changeLoc.getValue()});
		setHidden(currID, !turtle.isTurtleShow());
		//TODO: need to set pen Color at some point
	}
	
	private void onTurtleMapChange(MapChangeListener.Change<? extends Integer, ? extends Turtle> turtlesChange) {
		System.out.println("newTurtle");
        int id = turtlesChange.getKey();
        Turtle t = turtlesChange.getValueAdded();
        Turtle r = turtlesChange.getValueRemoved();
        if (t != null){
        	instantializeSprite(id, t);
        }
        if (r != null){
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

	public int getPenColor(){
		for (HashMap.Entry<Double, Color> e : colorMap.entrySet()) {
			Double indexEntry = (Double) e.getKey();
			Color colorEntry = (Color) e.getValue();
			if (colorEntry.equals(colorMap.get(penColor))){
				return indexEntry.intValue();
			}
		}
		//TODO: Learn how to throw exceptions
		throw new ErrorPrompt(exceptionResources.getString("InvalidColorIndex"));
	}

	public int setPenSize(double index){
		penWidth = (int) index;
		return (int) index;
	}

	public int setShape(int currID, double index){
		Sprite currSprite = spriteMap.get(currID);
		currSprite.setImage(imageMap.get(index));
		currentImageIndexMap.put(currID, index);
		return (int) index;
	}

	public int setShape(double index){
		currentShapeIndex = (int) index;
		defaultTurtleFile = (int) index;
		return currentShapeIndex;
	}

	public int getShape(){
		return currentShapeIndex;
	}

	public int setBackground(double index){
		backgroundColor = index;
		backgroundNode.setFill(colorMap.get(index));
		return (int) index;
	}

	public int setPalette(double index, double r, double g, double b){
		Color color = Color.rgb((int) r, (int) g, (int) b, .99);
		colorMap.put(index, color);
		notifyObservers();
		return (int) index;
	}

	public int[] getPalette(double index){
		Color color = colorMap.get(index);
		return new int[] { (int) color.getRed(), (int) color.getGreen(),  (int) color.getBlue()};
	}

	private void move(int currID, int[] vector){
		Sprite currSprite = spriteMap.get(currID);
		vector[0] = Math.round(vector[0]);
		vector[1] = Math.round(vector[1]);
		ArrayList<int[]> linesToMake = new ArrayList<int[]>();
		TurtleMath.addLinesToMake(viewWidth, viewHeight, currSprite.getPosition(), vector, linesToMake);
		int[] finalPosition = currSprite.getPosition();
		for (int[] coordinates : linesToMake){
			if (penDownMap.get(currID)){
				Line line = new Line();
				line.setStartX(coordinates[0]);
				line.setStartY(coordinates[1]);
				line.setEndX(coordinates[2]);
				line.setEndY(coordinates[3]);
				line.setFill(colorMap.get(penColor));
				line.setStrokeWidth(penWidth);
				root.getChildren().add(line);
			}
			finalPosition = new int[] {coordinates[2], coordinates[3]};
		}
		currSprite.setPosition(finalPosition);
	}

	public void setImage(int currID, File imgFile){
		Sprite currSprite = spriteMap.get(currID);
		currentImageIndexMap.put(currID, (double) imageMap.size());
		imageMap.put((double) currentImageIndexMap.get(currID), imgFile);
		currSprite.setImage(imgFile);
		notifyObservers();
	}

	public void addImage(File imgFile){
		int ID = imageMap.size();
		imageMap.put((double) ID, imgFile);
		notifyObservers();
	}

	public void setPen(int currID, boolean newPen){
		penDownMap.put(currID, newPen);
		Sprite currSprite = spriteMap.get(currID);
		currSprite.setPen(newPen);
	}

	private void instantializeSprite(int ID, Turtle t){
		currentImageIndexMap.put(ID, (double) defaultTurtleFile);
		spriteMap.put(ID, new Sprite(ID, imageMap.get(defaultTurtleFile), spriteWidth, spriteHeight, viewWidth, viewHeight));
		root.getChildren().add(spriteMap.get(ID).getImageView());
		notifyObservers();
	}

	public Node getView(){
		return root;
	}

	/**
	 * 
	 * @return	Sprite's absolute location
	 */
	public int[] getSpritePosition(int currID){
		return TurtleMath.absoluteToZero(viewWidth, viewHeight, spriteMap.get(currID).getPosition());
	}

	@Override
	public double clearScreen(){
		for (Node n : root.getChildren()){
			if (n.getClass().isInstance(new Rectangle())){
				
			}
		}
		return 0;
	}
}