package SLogo.View;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import SLogo.View.Sprite.Sprite;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class CanvasViewImpl extends Observable implements CanvasView {
	private ArrayList<Observer> observers;
	private static final String defaultMapPropertiesFilename = "data/defaultViewMapProperties.xml";
	private ResourceBundle exceptionResources;
	private static final String RESOURCE_FILEPATH = "resources/View/";
	private SLogoGUI gui;
	private Group root;
	private int viewWidth;
	private int viewHeight;
	private int spriteWidth;
	private int spriteHeight;
	private File defaultTurtleFile;
	private Map<Double,Color> colorMap;
	private Map<Double,File> imageMap;
	private double penColor;
	private double penWidth;
	private int currentShapeIndex;
	
	private HashMap<Integer,Sprite> spriteMap;
	private HashMap<Integer,Boolean> turtleHiddenMap;
	private HashMap<Integer,Boolean> penDownMap;
	private HashMap<Integer,Double> currentImageIndexMap;
	
	public CanvasViewImpl(int aviewWidth, int aviewHeight){
		exceptionResources = ResourceBundle.getBundle(RESOURCE_FILEPATH + "Exceptions");
		//gui = agui;
		viewWidth = aviewWidth;
		viewHeight = aviewHeight;
		root = new Group();
		colorMap = new HashMap<Double, Color>();
		imageMap = new HashMap<Double, File>();
		XMLParser.populateMaps(colorMap, imageMap, defaultMapPropertiesFilename);
		currentShapeIndex = 0;
		defaultTurtleFile = imageMap.get(currentShapeIndex);
		penColor = 0;
		penWidth = 1;
		notifyObservers();
	}
	
	//theoretically, when Stone makes first turtle, it will create an instance using instantializeSprite
	public void update(Observable o, Object n){
		Object[] newProperties = (Object[]) n;
		int currID = (int) newProperties[5];
		if (!spriteMap.containsKey(currID)){
			instantializeSprite(currID);
		}
		Sprite currSprite = spriteMap.get(currID);
		setPen(currID, (boolean) newProperties[0]);
		setPenColor(penColor);
		setPenSize(penWidth);
		currSprite.setDirection(((Double) newProperties[1]).intValue());
		move(currID, new int[] {((Double)newProperties[2]).intValue(), ((Double) newProperties[3]).intValue()});
		setHidden(currID, (boolean)newProperties[4]);
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
	
	public int setShape(double index){
		currentShapeIndex = (int) index;
		defaultTurtleFile = imageMap.get(index);
		return currentShapeIndex;
	}
	
	public int getShape(){
		return currentShapeIndex;
	}
	
	public int setBackground(double index){
		//TODO: get gui instance and be able to change background color
		//gui.setTurtleBackgroundColor(colorMap.get(index));
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

	private void instantializeSprite(int ID){
		currentImageIndexMap.put(ID, (double) imageMap.size());
		imageMap.put(currentImageIndexMap.get(ID), defaultTurtleFile);
		spriteMap.put(ID, new Sprite(ID, defaultTurtleFile, spriteWidth, spriteHeight, viewWidth, viewHeight));
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
	public double clearScreen(){ //will this method clear the screen and create a disconnected turtle? that's a problem
		//this also needs to return the distance traveled
		root.getChildren().clear();
		instantializeSprite(0); 
		//TODO: return distance moved
		return 0;
	}

	/**
     * Add an object as a listener
     *
     * @author Riley Nisbet
     */
    public void addObserver(Observer o) {
        observers.add(o);
        notifyObservers();
    }

    /**
     * Remove a listener
     *
     * @author Riley Nisbet
     */
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    /**
     * Tell all listeners that something has changed
     *
     * @author Riley Nisbet
     */
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(this, new Object[]{colorMap, imageMap});
        }
    }
}